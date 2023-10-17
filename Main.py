import random
import os
class Item:
    name= ""
    price = 0
    ID = ""
    quantity = 0
    def __init__(self,name, price,ID,quantity):
        self.name = name
        self.price = price
        self.ID = ID    
        self.quantity = quantity
    def decreaseQuantity(self):
        self.quantity-=1
    def increaseQuantity(self,amount):
        self.quantity += amount
        print(self.name + " now has " + str(self.quantity) + " in stock")
    def __str__(self):
        return str(self.ID) + " - " + str(self.name) + ": " + str(self.price) + " (" + str(self.quantity) + " in stock)"
    
class VendingMachine:
    def __init__(self,location):
        self.location = location
        self.ItemList = []
        self.IDs = []
        self.machineMoney = 0
    @classmethod
    def addPreviousList(self,location,newItemList):
        for newItem in newItemList:
            self.ItemList.append(newItem)
    def addItem(self,newItem):
        self.ItemList.append(newItem)
        self.IDs.append(newItem.ID)
    def addItemFresh(self,name, price, quantity):
        newID = self.CreateID()
        newItem = Item(name,price,newID,quantity)
        self.IDs.append(newID)
        self.ItemList.append(newItem)
    def addItemVendor(self, productName):
        productPrice = float(input("What is the item's price?"))
        quantity = int(input("How many are available?"))
        newID = self.CreateID()
        self.IDs.append(newID)
        newItem = Item(productName,productPrice,newID,quantity)
        self.ItemList.append(newItem)
    def addItemUser(self):
        productName = input("What is the Item you wish to add?")
        productPrice = float(input("What is the item's price?"))
        quantity = int(input("How many are available?"))
        newID = self.CreateID()
        self.IDs.append(newID)
        newItem = Item(productName,productPrice,quantity,newID)
        self.ItemList.append(newItem)
    def CreateID(self):
        letters = ["A","B","C","D","E"]
        newID = ""
        while True:
            newID = letters[random.randint(0,4)] + str(random.randint(0,10))
            if self.IDs.count(newID)>0:
                print()
            else:
                break
        return newID
    def printItemList(self):
        print("Below are the current Items available: ")
        for Item in self.ItemList:
            if(Item.quantity > 0):
                print(Item)
    def printItemListVendor(self):
        for Item in self.ItemList:
            print(Item)
    def orderItem(self, order):
        index = self.IDs.index(order)
        currentItem = self.ItemList[index]
        if(currentItem.quantity >0):
            currentItem.decreaseQuantity()
            self.machineMoney+= currentItem.price
            print("Here are your " + currentItem.name)
        else:
            print("Sorry that item is out of stock!")

    def searchItems(self,itemName):
        for currentItem in self.ItemList:
            if currentItem.name.lower() == itemName.lower():
                return currentItem
        return None
    def refreshItem(self,order):
        currentItem = self.searchItems(order)
        increaseNumber = int(input("How many are you adding?"))
        currentItem.increaseQuantity(increaseNumber)
class Vendor:
    def __init__(self,currentMachine):
        self.currentMachine = currentMachine
    def vendorInterface(self):
        keepGoing = True
        while(keepGoing):
            print("Welcome vendor! What can I help you with today?")
            print("1) Refresh stock")
            print("2) Add an item to our inventory")
            print("3) Retrieve Funds")
            print("4) Exit");
            decision = int(input(""))
            match decision:
                case 1:
                    self.refreshStock()
                case 2:
                    self.addItemstoMachine()
                case 3:
                    self.retrieveFunds()
                case 4:
                    print("Sorry that is not a proper command.")
                case _:
                    print("Thanks for stopping by")
                    break
            answer = input("Would you like to perform another vendor action? (Y/N)")
            if(answer.lower() != "y"):
                keepGoing = False
    def refreshStock(self):
        self.currentMachine.printItemListVendor()
        itemToRefresh = input("Which item would you like to refresh?")
        if(self.currentMachine.searchItems(itemToRefresh)!=None):
            self.currentMachine.refreshItem(itemToRefresh)
        else:
            print("Sorry, we currently don't have that item in stock!")
            print("Select option 2 if you wish to add the item")
    def addItemstoMachine(self):
        itemToAdd = input("What item would you like to add to the machine?")
        if(self.currentMachine.searchItems(itemToAdd) != None):
            print("It looks It looks like that item is currently available in the machine.")
            print("If you wish to refresh stock select option 1")
        else:
            self.currentMachine.addItemVendor(itemToAdd)
    def retrieveFunds(self):
        funds = self.currentMachine.machineMoney
        self.currentMachine.machineMoney = 0
        print("Current Funds:  " + str(funds));
        print("Here you go!")

def Main():
    order = ""
    keepGoing = True
    HargrovesVending = VendingMachine("Hargroves")
    Oreos = Item("Oreos",1.25,"A1",2)
    HargrovesVending.addItem(Oreos)
    HargrovesVending.addItemFresh("Lays Potato Chips", 1.50,5);
    ven = Vendor(HargrovesVending)
    while(keepGoing):
        print("Welcome to the " + HargrovesVending.location + " Vending Machine!")
        HargrovesVending.printItemList()
        while(True):
            order = input("Please enter the ID number of the item you would like to order? (If vendor enter vendor)")
            if(order.lower() == "vendor"):
                break
            elif(HargrovesVending.IDs.count(order) > 0):
                break
            else:
                print("Sorry that item is not in the machine!")
        if(order == "vendor"):
            #perform vendor action
            ven.vendorInterface()
        else:
            HargrovesVending.orderItem(order)
        answer = input("Would you like to make another order? (Y/N)")
        if(answer.lower() != "y"):
            keepGoing = False
            print("Thank you for ordering from the " + HargrovesVending.location + " Vending Machine!")
        else:
            os.system('cls')

os.system('clear')
Main()


