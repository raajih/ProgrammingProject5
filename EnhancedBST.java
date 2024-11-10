//Raajih Roland
//ProgrammingProject5
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EnhancedBST extends BinarySearchTree {
    /**
     * Default constructor that calls the constructor of BinarySearchTree class.
     */
    public EnhancedBST()
    {
        super();
    }

    /**
     * Reads ints from a file to add into binary tree.
     * @param fileName the name of the file to read ints from.
     */
    public void readFromFile(String fileName)
    {
        try {
            File numFile = new File(fileName);
            Scanner fileScnr = new Scanner(numFile);

            //Loop through file and add number into binarysearchtree.
            while(fileScnr.hasNextInt())
            {
                int currNum = fileScnr.nextInt();
                add(currNum);
            }


            fileScnr.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
    }

    /**
     * Prints the tree in preorder.
     */
    public void preorderPrint()
    {
        preorderPrint(this.getRoot());
    }

    /**
     * Helper method to print tree in preorder.
     * @param parent node of parent.
     */
    public void preorderPrint(Node parent)
    {
        if (parent == null)
            return;

        System.out.print(parent.data + " ");
        preorderPrint(parent.left);
        preorderPrint(parent.right);
    }

    /**
     * @Override
     */
    public void print()
    {
        System.out.print("Inorder: ");
        super.print();//Call superclass' print method for the inorder search.
        System.out.print("Preorder: ");//Output tree in preorder.
        preorderPrint();
    }
    

    

}
