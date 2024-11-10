//Raajih Roland
//ProgrammingProject5
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Stack;

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

    /**
     * Finds number of absent children.
     * @return number of absent children.
     */
    public int absentChildren ()
    {
        return absentChildren(getRoot());
    }

    /**
     * Recursive helper method to find number of absent children.
     * @param parent current node to find absent children for.
     * @return number of absent children for current node.
     */
    public int absentChildren(Node parent)
    {
        int absentCount = 0;
        if(parent == null)
            return 0;

        if(parent.left == null)
            absentCount++;

        if(parent.right == null)
            absentCount++;

        absentCount = absentCount + absentChildren(parent.left);
        absentCount = absentCount + absentChildren(parent.right);

        return absentCount;

    }

    /**
     * Test to see if user's input is a valid path sum.
     * @param targetSum target to compare path sums to.
     * @return true if targetSum is a valid pathSum, false if not.
     */
    public boolean hasPathSum(int targetSum)
    {
        return hasPathSum(getRoot(), targetSum, 0);
    }
    
    /**
     * Helper to test to see if user's input is a valid path sum.
     * @param parent current node.
     * @param targetSum target to compare path sums to.
     * @param currentSum current sum of path.
     * @return true if targetSum is a valid pathSum, false if not.
     */
    public boolean hasPathSum(Node parent, int targetSum, int currentSum) {
        if (parent == null) {
            return false; // If the node is null, no path exists
        }

        // Add the current node's data to the running sum
        currentSum += (int) parent.data;

        // Check if we've reached a leaf node (both left and right are null)
        if (parent.left == null && parent.right == null) 
        {
            // If we are at a leaf, check if the sum matches the target
            return currentSum == targetSum;
        }

        //Check the left and right subtrees
        boolean leftHasPath = hasPathSum(parent.left, targetSum, currentSum);
        boolean rightHasPath = hasPathSum(parent.right, targetSum, currentSum);

        //Return true if either left or right subtree has a valid path sum.
        return leftHasPath || rightHasPath;
    }

     //Return an iterator for the tree.
    public Iterator<Integer> iterator() 
    {
        return new TreeIterator();
    }
    
    //Implements Iterator.
    public class TreeIterator implements Iterator<Integer> 
    {
        private Stack<Node> stack;
        
        public TreeIterator() {
            stack = new Stack<>();
            if (getRoot() != null) {
                stack.push(getRoot());
            }
        }

       

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() 
        {
            if (!hasNext()) 
            {
                throw new NoSuchElementException("No more elements in the tree.");
            }
            // Pop the node from the stack.
            Node node = stack.pop();

            // Push right child first so that left child is done first.
            if (node.right != null) 
            {
            stack.push(node.right);
            }
            if (node.left != null) 
            {
            stack.push(node.left);
            }

                return (int) node.data;
        }
    }

    

}
