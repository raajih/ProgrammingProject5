//Raajih Roland
//ProgrammingProject5
//Used to output the menu and get user's choice.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UserInterface 
{
    private int choice;

    /**
     * Default constructor for UserInterface object.
     */
    public UserInterface() 
    {
        choice = 0;
    }

    public void go() throws FileNotFoundException
    {
        Scanner scnr = new Scanner(System.in);

        EnhancedBST tree = new EnhancedBST();//Create EnhancedBST object.

        do {
                System.out.println("\nWelcome to Enhanced BST Tester.\n");

                //Output menu options
                System.out.println("Here's the menu of choices - \n");
                System.out.print("\t0)Quit\n" + 
                "\t1)Build a BST from a text file\n" +
                "\t2)Print the tree\n" +
                "\t3)Add data\n" +
                "\t4)Remove data\n" +
                "\t5)Show tree height\n" +
                "\t6)Show internal path length\n" +
                "\t7)Count absent children\n" +
                "\t8)Find a path sum\n" +
                "\t9)Export a BST to a text file\n" +
                "Enter your choice: ");

                //Get user's choice.
                choice = scnr.nextInt();
                scnr.nextLine();

                //Switch statement for menu option.
                switch(choice)
                {
                    case 0:
                    break;
                    case 1:
                    //Read in name of file.
                    String fileName;
                    System.out.print("Enter name of text file: ");
                    fileName = scnr.nextLine();

                    tree.readFromFile(fileName);
                    break;
                    
                    case 2:
                    if (tree.getRoot() == null)//If there is nothing in tree.
                        System.out.println("Tree is empty");
                    tree.print();
                    break;

                    case 3://Add user's number to a tree.
                    System.out.print("Enter number to be added: ");
                    int addNum = scnr.nextInt();
                    scnr.nextLine();
                    tree.add(addNum);
                    break;

                    case 4://Remove user's number from tree.
                    System.out.print("Enter number to be removed: ");
                    int remNum = scnr.nextInt();
                    scnr.nextLine();
                    tree.remove(remNum);
                    break;

                    case 5://Find height.
                    System.out.print("Height: " + tree.height());
                    break;

                    case 6://Find internal path length.
                    System.out.print("Internal path length: " + tree.internalPath());
                    break;

                    case 7://Find number of absent children.
                    System.out.print("Number of absent children: " + tree.absentChildren());
                    break;

                    case 8://See if user's input is a valid path sum.
                    int targetSum;
                    System.out.print("Enter path sum you would like to test: ");
                    targetSum = scnr.nextInt();
                    scnr.nextLine();

                    if (tree.hasPathSum(targetSum))
                        System.out.print(targetSum + " is a valid path sum.");
                    else
                        System.out.print(targetSum + " is not a valid path sum.");
                    break;

                    case 9:
                    if (tree.getRoot() == null) {
                        System.out.println("Tree is empty.");
                        break;
                    }
                    System.out.print("Enter the filename to export the tree: ");
                    String exportFileName = scnr.nextLine();
                    exportToFile(exportFileName, tree);
                    break;
                    default: 
                    System.out.print("Invalid choice, please try again.");

                }
        } while (choice != 0);//Quit if user picks 0.

        

    }

    // Method to export tree data to a file
    public void exportToFile(String filename, EnhancedBST tree) 
    {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            Iterator<Integer> iterator = tree.iterator();
            while (iterator.hasNext()) {
                writer.println(iterator.next());
            }
            System.out.println("Tree data has been exported to " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        UserInterface ui = new UserInterface();
        ui.go();
    }

}

