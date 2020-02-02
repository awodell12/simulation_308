package xml;

public class XMLException extends RuntimeException {
    /**
     * Create an exception based on an issue in our code.
     */
    public XMLException (String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLException (Throwable cause) {
        super(cause);
    }
}
