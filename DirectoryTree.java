
public class DirectoryTree {
    /**
     * root - the root of the tree
     * cursor - cursor of the tree
     */
    DirectoryNode root;
    DirectoryNode cursor;

    /**
     * DirectoryTree Constructor
     * Initializes a DirectoryTree object with a single DirectoryNode named
     * "root".
     * The tree contains a single DirectoryNode named "root", and both cursor
     * and root reference this node.
     */
    public DirectoryTree(){
        cursor = root = new DirectoryNode("root");
    }

    /**
     * Moves the cursor to the root node of the tree.
     */
    public void resetCursor(){
        cursor = root;
    }

    /**
     * Moves the cursor to the directory with the name indicated by name.
     * @param name - argument used
     * @throws NotADirectoryException - Thrown if the node with the indicated
     * name is a file, as files cannot be selected by the cursor, or cannot be
     * found.
     */
    public void changeDirectory(String name) throws NotADirectoryException {
        if (cursor.getLeft() != null) {
            if (cursor.getLeft().getName().equalsIgnoreCase(name)) {
                if (cursor.getLeft().isFile()) {
                    throw new NotADirectoryException("Cannot change " +
                            "directory into a file.");
                }
                else {
                    cursor = cursor.getLeft();
                }
            }
        }
        if (cursor.getMiddle() != null) {
            if (cursor.getMiddle().getName().equalsIgnoreCase(name)) {
                if (cursor.getMiddle().isFile()) {
                    throw new NotADirectoryException("Cannot change " +
                            "directory into a file.");
                }
                else {
                    cursor = cursor.getMiddle();
                }
            }
        }
        if (cursor.getRight() != null) {
            if (cursor.getRight().getName().equalsIgnoreCase(name)) {
                if (cursor.getRight().isFile()) {
                    throw new NotADirectoryException("Cannot change " +
                            "directory into a file.");
                }
                else {
                    cursor = cursor.getRight();
                }
            }
        }
    }

    /**
     * @return a String containing the path of directory names from the root
     * node of the tree to the cursor, with each name separated by a forward
     * slash "/".
     * e.g. root/home/user/Documents if the cursor is at Documents in the
     * example above.
     */
    public String presentWorkingDirectory(){
        String name = root.getName();
        return root.findNode(name, cursor);
    }

    /**
     * @return a String containing a space-separated list of names of all
     * the child directories or files of the cursor.
     * e.g. dev home bin if the cursor is at root in the example above.
     */
    public String listDirectory(){
        String name = "";
        if (cursor.getLeft() != null){
            name = cursor.getLeft().getName();
        }
        if (cursor.getMiddle() != null){
            name = name + " " + cursor.getMiddle().getName();
        }
        if (cursor.getRight() != null){
            name = name + " " + cursor.getRight().getName();
        }
        return name;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the
     * directory tree, starting from the cursor.
     */
    public void printDirectoryTree(){
        printDirectoryTree(cursor, 1);
    }

    /**
     * printDirectoryTree Helper Method
     * @param node - node used for cursor
     * @param indents - number of indents
     */
    public void printDirectoryTree(DirectoryNode node, int indents){
        if (node == null){
            return;
        }
        StringBuilder s = new StringBuilder();

        for (int h = 1; h < indents; h++){
            s.append("\t");
        }
        if (!node.isFile()) {
            System.out.println(s + "|- " + node.getName());
        }
        else{
            System.out.println(s + "- " + node.getName());
        }
        if (node.getLeft() != null){
            printDirectoryTree(node.getLeft(), indents + 1);
        }
        if (node.getMiddle() != null){
            printDirectoryTree(node.getMiddle(), indents + 1);
        }
        if (node.getRight() != null){
            printDirectoryTree(node.getRight(), indents + 1);
        }
    }

    /**
     * Creates a directory with the indicated name and adds it to the children
     * of the cursor node. Remember that children of a node are added in
     * left-to-right order.
     * @param name - The name of the directory to add.
     * @throws IllegalArgumentException - Thrown if the 'name' argument is
     * invalid.
     * @throws FullDirectoryException - Thrown if all child references of this
     * directory are occupied.
     */
    public void makeDirectory(String name) throws IllegalArgumentException,
            FullDirectoryException{
        if (name.contains(" ") || name.contains("/")){
            throw new IllegalArgumentException("Not a valid name");
        }
        DirectoryNode temp = new DirectoryNode(name);
        if (cursor.getLeft() == null) {
            cursor.setLeft(temp);
            cursor.getLeft().isFile = false;
        }
        else if (cursor.getMiddle() == null) {
            cursor.setMiddle(temp);
            cursor.getMiddle().isFile = false;
        }
        else if (cursor.getRight() == null){
            cursor.setRight(temp);
            cursor.getRight().isFile = false;
        }
        else{
            throw new FullDirectoryException();
        }
    }

    /**
     * Creates a file with the indicated name and adds it to the children of
     * the cursor node. Remember that children of a node are added in
     * left-to-right order.
     * @param name - The name of the file to add.
     * @throws IllegalArgumentException - Thrown if the 'name' argument is
     * invalid.
     * @throws FullDirectoryException -  Thrown if all child references of this
     * directory are occupied.
     */
    public void makeFile(String name) throws IllegalArgumentException,
            FullDirectoryException{
        if (name.contains(" ") || name.contains("/")){
            throw new IllegalArgumentException("Not a valid name");
        }
        DirectoryNode temp = new DirectoryNode(name);
        if (cursor.getLeft() == null) {
            cursor.setLeft(temp);
            cursor.getLeft().isFile = true;
        }
        else if (cursor.getMiddle() == null) {
            cursor.setMiddle(temp);
            cursor.getMiddle().isFile = true;
        }
        else if (cursor.getRight() == null){
            cursor.setRight(temp);
            cursor.getRight().isFile = true;
        }
        else{
            throw new FullDirectoryException();
        }
    }
}