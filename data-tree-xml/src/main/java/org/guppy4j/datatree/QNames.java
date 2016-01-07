package org.guppy4j.datatree;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

import static javax.xml.xpath.XPathConstants.NODE;
import static javax.xml.xpath.XPathConstants.NODESET;
import static javax.xml.xpath.XPathConstants.STRING;

/**
 * Mapping from Java type to QName
 */
public class QNames {

    private final Map<Class<?>, QName> qNames = new HashMap<>();

    {
        qNames.put(String.class, STRING);
        qNames.put(Node.class, NODE);
        qNames.put(NodeList.class, NODESET);
    }

    public QName get(Class<?> resultType) {
        return qNames.get(resultType);
    }
}
