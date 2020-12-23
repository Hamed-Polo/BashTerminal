import java.util.Scanner;

public class BashTerminal {
    /**
     * Runs a program which takes user input and builds a DirectoryTree using
     * the commands
     */
    public static void main(String[] args) {
        Scanner h = new Scanner(System.in);
        String input = "";
        DirectoryTree directoryTree = new DirectoryTree();
        System.out.println("Starting bash terminal.");
        // if input doesn't equal 'exit'
        while (!input.equalsIgnoreCase("exit")) {
            try {
                System.out.print("[hdrame@BlackJesus]: $ ");
                input = h.nextLine().toLowerCase();

                if (input.split(" ")[0].
                        equalsIgnoreCase("cd")) {
                    if (input.split(" ")[1].
                            equalsIgnoreCase("/")) {
                        directoryTree.resetCursor();
                    }
                    else {
                        directoryTree.changeDirectory(input.split(" ")
                                [1]);
                    }
                }
                else if (input.toLowerCase().equalsIgnoreCase
                        ("pwd")) {
                    System.out.print(directoryTree.presentWorkingDirectory() +
                            "\n");
                }
                else if (input.equalsIgnoreCase("ls")) {
                    System.out.print(directoryTree.listDirectory() + "\n");
                }
                else if (input.toLowerCase().
                        equalsIgnoreCase("ls -r")) {
                    directoryTree.printDirectoryTree();
                }
                else if (input.split(" ")[0].
                        equalsIgnoreCase("mkdir")) {
                    directoryTree.makeDirectory(input.split(" ")[1]);
                }
                else if (input.split(" ")[0].
                        equalsIgnoreCase("touch")) {
                    directoryTree.makeFile(input.split(" ")[1]);
                }
            }
            catch (Exception e) {
                System.out.print(e.getMessage() + "\n");
            }
        }
        // if it does equal exit, print this message
        System.out.print("Program terminating normally");
    }
}