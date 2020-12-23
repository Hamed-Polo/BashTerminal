public class DirectoryNode {
    /**
     * left - left node
     * middle - middle node
     * right - right node
     * name - name of the node
     * isFile - whether the node is a file or not, if it's a file then it's not
     * a directory and can't have any children.
     */
    private DirectoryNode left;
    private DirectoryNode middle;
    private DirectoryNode right;
    private String name;
    boolean isFile;

    /**
     * DirectoryNode Constructor
     * @param name - set the name of the node
     */
    public DirectoryNode(String name){
        left = null;
        middle = null;
        right = null;
        this.name = name;
    }

    /**
     * Setting the left node to an argument
     * @param left - argument used
     */
    public void setLeft(DirectoryNode left){
        this.left = left;
    }

    /**
     * @return the left node
     */
    public DirectoryNode getLeft(){
        return left;
    }

    /**
     * setting the middle node to the argument
     * @param middle - argument used
     */
    public void setMiddle(DirectoryNode middle){
        this.middle = middle;
    }

    /**
     * @return the middle node
     */
    public DirectoryNode getMiddle(){
        return middle;
    }

    /**
     * setting the right node to the argument
     * @param right - argument used
     */
    public void setRight(DirectoryNode right){
        this.right = right;
    }

    /**
     * @return the right node
     */
    public DirectoryNode getRight(){
        return right;
    }

    /**
     * setting the name to the argument
     * @param name - argument used
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return the name of the node
     */
    public String getName(){
        return name;
    }

    /**
     * setting the boolean to the argument
     * @param isFile - argument used
     */
    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }

    /**
     * @return whether it's a file or not(true or false)
     */
    public boolean isFile() {
        return isFile;
    }

    /**
     * Adds newChild to any of the open child positions of this node
     * (left, middle, or right).
     * NOTE: Children should be added to this node in left-to-right order,
     * i.e. left is filled first, middle is filled second, and right is
     * filled last
     * @param newChild - argument used
     * @throws FullDirectoryException - Thrown if all child references of this
     * directory are occupied.
     * @throws NotADirectoryException - Thrown if the current node is a file,
     * as files cannot contain DirectoryNode references
     * (i.e. all files are leaves).
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException,
            NotADirectoryException{
        if (left.isFile || middle.isFile || right.isFile || isFile){
            throw new NotADirectoryException("Not a directory");
        }
        if (newChild.left == null){
            left = newChild;
        }
        else if (newChild.middle == null){
            middle = newChild;
        }
        else if (newChild.right == null){
            right = newChild;
        }
        else{
            throw new FullDirectoryException();
        }
    }

    /**
     * Helps us find the node assigned by the cursor
     * @param prev - argument for the string
     * @param cursor - the cursor node
     * @return - returns a string of the present working directory
     */
    public String findNode(String prev, DirectoryNode cursor){
        String name = "";
        if (this.equals(cursor)){
            return prev;
        }
        else{
            if (left != null){
                name = left.findNode(prev + "/" + left.getName(), cursor);
                if (!name.equalsIgnoreCase("")){
                    return name;
                }
            }
            if (middle != null){
                name = middle.findNode(prev + "/" + middle.getName(),
                        cursor);
                if (!name.equalsIgnoreCase("")){
                    return name;
                }
            }
            if (right != null){
                name = right.findNode(prev + "/" + right.getName(),
                        cursor);
                if (!name.equalsIgnoreCase("")){
                    return name;
                }
            }
        }
        return name;
    }
}