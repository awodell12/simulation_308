package xml;

public class XMLException extends RuntimeException {
    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLException (Throwable cause) {
        super(cause);
    }
}
