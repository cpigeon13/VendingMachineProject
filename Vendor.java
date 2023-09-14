import java.util.Scanner;
public class Vendor {
    //Holds the current vending machine that we are handling
    public VendingMachine currentMachine;
    public Scanner input = new Scanner(System.in);
    public Vendor(VendingMachine currentMachine){
        this.currentMachine = currentMachine;
    }
    //Generates the interface for the user with vendor specific options
    public void vendorInterface(){
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Welcome vendor! What can I help you with today?");
            System.out.println("1) Refresh stock");
            System.out.println("2) Add an item to our inventory");
            System.out.println("3) Retrieve Funds");
            System.out.println("4) Exit");
            int decision = input.nextInt();

            if (decision == 1) {
                refreshStock();
            } else if (decision == 2) {
                addItemstoMachine();
            } else if (decision == 3) {
                retrieveFunds();
            } else {
                System.out.println("Thanks for stopping by!");
                break;
            }

            System.out.println("Would you like to perform another vendor action? (Y/N)");
            String answer = input.next();
            if (answer.equalsIgnoreCase("y")) {
                keepGoing = true;
            }
            else{
                keepGoing = false;
            }
        }

    }
    /*
    Provides all items currently available and allows the vendor to increase the stock by
    a given amount
     */
    public void refreshStock(){
        currentMachine.printItemListVendor();
        System.out.println("Which item would you like to refresh?");
        String itemToRefresh = input.next();
        if(currentMachine.searchItems(itemToRefresh)!=null){
            currentMachine.refreshItem(itemToRefresh);
        }
        else{
            System.out.println("Sorry, we currently don't have that item in stock!");
            System.out.println("Select option 2 if you wish to add the item");
        }
    }
    /*
    Checks if the Item they wish to add is already in the machine
    if not then begins the process to add a new item to the machine
     */
    public void addItemstoMachine(){
        System.out.println("What item would you like to add to the machine?");
        String itemToAdd = input.next();
        if(currentMachine.searchItems(itemToAdd)!= null){
            System.out.println("It looks like that item is currently available in the machine.");
            System.out.println("If you wish to refresh stock select option 1");
        }
        else{
            currentMachine.addItem(itemToAdd);
        }
    }
    /*
    Gives the user the funds of the machine
     */
    public void retrieveFunds(){
        double funds = currentMachine.getFunds();
        currentMachine.setFunds(0);

        System.out.println("Current Funds:  " + funds);
        System.out.println("Here you go!");
    }
}
