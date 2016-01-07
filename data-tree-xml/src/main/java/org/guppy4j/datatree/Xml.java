package org.guppy4j.datatree;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Allows getting and setting of xpath values
 */
public class Xml implements Tree {

    private final TransformerFactory tf = TransformerFactory.newInstance();
    private final XPath xpath = XPathFactory.newInstance().newXPath();
    private final QNames qNames = new QNames();

    private final Node root;

    public Xml(CharSequence payload) {
        this(new InputSource(new StringReader(payload.toString())));
    }

    public Xml(Path path) {
        this(new InputSource(path.toFile().toURI().toASCIIString()));
    }

    public Xml(InputStream stream) {
        this(new InputSource(stream));
    }

    public Xml(InputSource inputSource) {
        this(parse(inputSource));
    }

    private Xml(Node root) {
        this.root = root;
    }

    private static Document parse(InputSource inputSource) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(inputSource);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XmlException(e);
        }
    }

    @Override
    public Iterable<Tree> nodes(String xpath) {
        final NodeList list = eval(xpath, NodeList.class);
        final int nodeCount = list.getLength();
        final Collection<Tree> result = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            result.add(new Xml(list.item(i)));
        }
        return result;
    }

    @Override
    public String get(String xpath) {
        return eval(xpath, String.class);
    }

    @Override
    public boolean set(String xpath, String value) {
        final Node node = eval(xpath, Node.class);
        if (node != null) {
            node.setTextContent(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        final StringWriter writer = new StringWriter();
        try {
            tf.newTransformer().transform(new DOMSource(root), new StreamResult(writer));
        } catch (TransformerException e) {
            throw new XmlException(e);
        }
        return writer.getBuffer().toString();
    }

    private <T> T eval(String xpathExpression, Class<T> resultType) {
        try {
            final XPathExpression expr = xpath.compile(xpathExpression);
            return resultType.cast(expr.evaluate(root, qNames.get(resultType)));
        } catch (XPathExpressionException e) {
            throw new XmlException(e);
        }
    }
}