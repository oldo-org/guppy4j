package org.oldo.datatree;

/**
 * An XML exception
 */
public class XmlException extends PayloadException {

    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlException(Throwable cause) {
        super(cause);
    }
}
