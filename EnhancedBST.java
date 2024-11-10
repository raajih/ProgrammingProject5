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
     * Prints the tree in postorder.
     */
    public void postorderPrint()
    {
        postorderPrint(this.getRoot());
    }

    /**
     * Helper method to print tree in postorder.
     * @param parent current node.
     */
    public void postorderPrint(Node parent)
    {
        if (parent == null)
            return;
        postorderPrint(parent.left);
        postorderPrint(parent.right);

        System.out.print(parent.data + " ");
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
        System.out.print("\nPostorder: ");//Output tree in postorder.
        postorderPrint();
    }

    /**
     * Finds height of tree.
     */
    public int height()
    {
        return height(getRoot());
    }

    /**
     * Helper method to find height of tree.
     * @param parent
     */
    public int height(Node parent)
    {
        if (parent == null)//Base case.
            return 0;
        else
        {
            return 1 + Math.max(height(parent.left), height(parent.right));
        }
    }

    /**
     * Finds the internal path length.
     * @return internal path length.
     */
    public int internalPath()
    {
        return internalPath(getRoot(), 0);
    }

    /**
     * Helper method to find internal path length.
     * @param parent parent node.
     * @param level current level.
     * @return the length of internal path from current node.
     */
    public int internalPath(Node parent, int level)
    {
        if (parent == null)//Base case.
            return 0;

        int leftPath = internalPath(parent.left, level + 1);
        int rightPath = internalPath(parent.right, level + 1);

        return leftPath + rightPath + level;
    }
    

    

}
