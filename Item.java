public class Item {
    //Holds the name of the Item
    String name;
    //Holds the price of the item
    double price;
    //Holds the unique ID of the item
    String ID;
    //Holds the amount of this item available
    int quantity;
    public Item(String name, double price, String ID, int quantity){
        this.name = name;
        this.price = price;
        this.ID = ID;
        this.quantity = quantity;
    }

    //Returns the item name
    public String getName(){
        return name;
    }
    //Returns the item price
    public double getPrice(){
        return price;
    }
    //returns the unique Item ID
    public String getID(){
        return ID;
    }
    //Returns the amount of the item available
    public int getQuantity(){
        return quantity;
    }
    //Changes the name of the item
    public void setName(String newName){
        name = newName;
    }
    //Changes the price of the item
    public void setPrice(double newPrice){
        price = newPrice;
    }
    //Decreases the amount of the item available by 1
    public void decreaseQuantity(){
        quantity--;
    }
    //Increases the amount available of this item by a given amount
    public void increaseQuantity(int amount){
        quantity = quantity + amount;
        System.out.println(name + " now has " + quantity + " in stock");
    }

    public String toString(){
        return ID + " - " + name + ": " + price + " (" + quantity + " in stock)";
    }
}
