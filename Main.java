import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String order;
        boolean keepGoing = true;
        VendingMachine HargrovesVending = new VendingMachine("Hargroves");
        Item Oreos = new Item("Oreos",1.25,"A1",2);
        Vendor ven = new Vendor(HargrovesVending);
        HargrovesVending.addItem(Oreos);

        HargrovesVending.addItem("Lays Potato Chips", 1.50,5);
        //HargrovesVending.addItem();
        while(keepGoing) {
            System.out.println("Welcome to the " + HargrovesVending.getLocation() + " Vending Machine!");
            HargrovesVending.printItemList();
            do {
                System.out.println("Please enter the ID number of the item you would like to order? ( (If vendor enter vendor)");
                order = input.next();
            } while (!HargrovesVending.getIDs().contains(order) && !order.equalsIgnoreCase("vendor"));
            if(order.equalsIgnoreCase("vendor")){
                ven.vendorInterface();
            }
            else {
                HargrovesVending.orderItem(order);
            }
            System.out.println("Would you like to make another order? (Y/N)");
            String answer = input.next();
            if(answer.equals("Y") || answer.equals("y")){
                keepGoing = true;
            }
            else{
                keepGoing = false;
                System.out.println("Thank you for ordering from the " + HargrovesVending.getLocation() + " Vending Machine");
            }

        }

    }
}