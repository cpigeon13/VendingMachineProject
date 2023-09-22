import java.util.ArrayList;
import java.util.*;

/*
This class holds all methods related to the vending machine such as
searching for items, checking availability, and adding items
 */
public class VendingMachine {
    //Holds all items currently available in the machine
    public ArrayList<Item> ItemList = new ArrayList<Item>();
    Scanner input = new Scanner(System.in);
    //Holds the list of IDs that are currently being used so that we don't have repeats
    public ArrayList<String> IDs = new ArrayList<String>();

    //Holds the money that the machine currently has
    public double machineMoney = 0;
    //Holds the location of the machine
    public String location;

    /*
    This constructor will run if the vending machine is new with no
    preexisting inventory
     */

    public VendingMachine(String location) {
        this.location = location;
    }
    /*
    If the machine already has an inventory then this will bring that preexisting inventory
    over for us
     */
    public VendingMachine(String location, ArrayList<Item> newItemList) {
        for (int i = 0; i < newItemList.size(); i++) {
            ItemList.add(newItemList.get(i));
        }
        this.location = location;
    }
    //Returns the location of the machine
    public String getLocation() {
        return location;
    }
    //Changes the location of the machine
    public void setLocation(String newLocation) {
        location = newLocation;
    }
    //Returns the funds of the machine
    public double getFunds(){
        return machineMoney;
    }
    //Changes the funds of the machine
    public void setFunds(double newFunds){
        machineMoney = newFunds;
    }
    /*
    There are several ways to add items to a given machine based on
    the information that the user has available.
    This first one adds a completely new item by prompting the user for information
     */
    public void addItem() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is the Item you wish to add?");
        String productName = input.next();
        System.out.println("What is the item's price?");
        double productPrice = input.nextDouble();
        System.out.println("How many are available");
        int quantity = input.nextInt();
        String newID = CreateID();
        Item newItem = new Item(productName, productPrice, newID, quantity);
        ItemList.add(newItem);
    }
    //This one is used if the user already knows the information but needs an ID generated
    public void addItem(String name, double price, int quantity) {

        String newID = CreateID();
        Item newItem = new Item(name, price, newID, quantity);
        ItemList.add(newItem);
    }
    /*
    This method is mainly used through the vendor interface since we previously have to check
    if the item is already in the machine
     */

    public void addItem(String productName) {

        System.out.println("What is the item's price?");
        double productPrice = input.nextDouble();
        System.out.println("How many are available");
        int quantity = input.nextInt();
        String newID = CreateID();
        Item newItem = new Item(productName, productPrice, newID, quantity);
        ItemList.add(newItem);
    }
    //This is only used at the start of the program for hard coding Items into the start of the machine
    public void addItem(Item newItem) {
        ItemList.add(newItem);
        IDs.add(newItem.getID());
    }

    //This method generates a new ID that is not currently being used
    public String CreateID() {
        String[] letters = {"A", "B", "C", "D", "E"};
        Random rand = new Random();
        String newID = " ";
        do {
            newID = letters[rand.nextInt(5)] + rand.nextInt(10);

        }
        while (IDs.contains(newID));
        IDs.add(newID);
        return newID;
    }
    //Prints out the items that don't have 0 quantity (current available items)
    public void printItemList() {
        Item currentItem;
        System.out.println("Below are the current Items available: ");
        for (int i = 0; i < ItemList.size(); i++) {
            currentItem = ItemList.get(i);
            if (currentItem.quantity > 0) {
                System.out.println(currentItem);
            }
        }
    }
    /*
    Prints out all items in the machine including those that have no quantity
    This is mainly for the vendor interface
     */

    public void printItemListVendor() {
        for (Item currentItem : ItemList) {
            System.out.println(currentItem);
        }
    }
    //Returns the list of items
    public ArrayList<Item> getItems() {
        return ItemList;
    }
    //Returns the list of currently used IDs
    public ArrayList<String> getIDs() {
        return IDs;
    }
    //Decreases the quantity of the given item by 1
    public void orderItem(String order) {
        Item currentItem = ItemList.get(IDs.indexOf(order));
        if (currentItem.getQuantity() > 0) {
            currentItem.decreaseQuantity();
            machineMoney = currentItem.getPrice() + machineMoney;
            System.out.println("Here are your " + currentItem.getName());
            //return true;
        } else {
            System.out.println("Sorry that item is out of stock!");
            //return false;
        }
    }
    //Increases the quantity of an item by a given amount
    public void refreshItem(String order) {
        Item currentItem = searchItems(order);
        System.out.println("How many are you adding?");
        int increaseNumber = input.nextInt();
        currentItem.increaseQuantity(increaseNumber);
    }
    //Searches through the list of Items for the given Item Name
    public Item searchItems(String itemName) {
        for (Item currentItem : ItemList) {
            if (currentItem.getName().equalsIgnoreCase(itemName)) {
                return currentItem;
            }
        }
    return null;
    }
}
