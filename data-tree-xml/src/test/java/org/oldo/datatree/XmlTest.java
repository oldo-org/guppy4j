package org.oldo.datatree;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;

/**
 * Tests XmlMessage
 */
public class XmlTest {

    @Test
    public void testXmlFromResource() {
        final Xml xml = new Xml(new InputSource(getClass().getResourceAsStream("test-sample-1.xml")));

        final Iterable<Tree> nodes = xml.nodes("/test/nodes/node");
        int i = 1;
        for (Tree node : nodes) {
            final String x = node.get(".");
            assertEquals(String.valueOf(i++), x);
        }
    }

    @Test
    public void testAsString() throws IOException, SAXException {
        final String payload =
                "<ns:a xmlns:ns=\"http://ns.org\">" +
                        "<b>text</b>" +
                        "</ns:a>";
        final Xml xml = new Xml(payload);
        assertXMLEqual(payload, xml.toString());
    }

    @Test
    public void testGetByXpath() {
        check("text", "<ns:a><b>text</b></ns:a>", "/a/b");
        check("text", "<a><b>text</b></a>", "/a/b");
    }

    @Test
    public void testNodesByXpath() {
        final Xml xml = new Xml("<a><b><c>1</c></b><b><c>2</c></b><b><c>3</c></b></a>");
        final Iterable<Tree> nodes = xml.nodes("a/b");
        int i = 1;
        for (Tree node : nodes) {
            final String x = node.get("c");
            assertEquals(String.valueOf(i++), x);
        }
    }

    @Test
    public void testTextNodes() {
        final Xml xml = new Xml("<a><b><c>1</c></b><b><c>2</c></b><b><c>3</c></b></a>");
        final Iterable<Tree> nodes = xml.nodes("a/b/c");
        int i = 1;
        for (Tree node : nodes) {
            final String x = node.get(".");
            assertEquals(String.valueOf(i++), x);
        }
    }

    @Test
    public void testSetValueWithSpecialChars() throws Exception {
        final Xml xml = new Xml("<a></a>");
        final String valueWithSpecialChars = "<&>\"bread\" & 'butter'";
        xml.set("/a", valueWithSpecialChars);
        assertEquals(xml.get("/a"), valueWithSpecialChars);

        final String s = xml.toString();

        final Xml xml2 = new Xml(s);

        assertXMLEqual(s, xml2.toString());
    }

    @Test(expected = XmlException.class)
    public void testFailure() {
        check("text", "<ns:a><b>text</b></ns:a>", "\\/ns:a/b");
    }

    private void check(String text, String content, String xpathExpression) {
        final String value = new Xml(content).get(xpathExpression);
        assertEquals(text, value);
    }
}
