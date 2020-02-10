package xml;

/**
 * @author Robert Chen
 * Purpose: Throw XML exceptions
 * Assumptions: None
 * Dependencies: None
 * Example: simply create a new XMLException given a Throwable
 */
public class XMLException extends RuntimeException {
    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public XMLException (Throwable cause) {
        super(cause);
    }
}
