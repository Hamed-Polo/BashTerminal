
public class NotADirectoryException extends Exception{
    /**
     * Creates an exception stating that a node is not a directory
     * @param message - argument used
     */
    public NotADirectoryException(String message){
        super(message);
    }
}