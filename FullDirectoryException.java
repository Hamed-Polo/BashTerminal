
public class FullDirectoryException extends Exception{
    /**
     * Creates an exception stating that the directory is full
     */
    public FullDirectoryException(){
        super("ERROR: Present directory is full.");
    }
}