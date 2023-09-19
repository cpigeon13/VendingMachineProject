import random
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
        self.quantity+= amount
        print(self.name + " now has " + self.quanity + " in stock")
    def __str__(self):
        return self.ID + " - " + self.name + ": " + self.price + " (" + self.quantity + " in stock)"
    
class VendingMachine:
    self.ItemList = []
    self.IDs = []
    self.machineMoney = 0
    self.location = ""
    def __init__(self,location):
        self.location = location
    def __init__(self,location,newItemList):
        for newItem in newItemList:
            self.ItemList.append(newItem)
    def addItem(self):
        productName = input("What is the Item you wish to add?")
        productPrice = int(input("What is the item's price?"))
        quantity = int(input("How many are available?"))
        newID = CreateID()
        newItem = Item(productName,productPrice,quantity,newID)
        self.ItemList.append(newItem)
    def CreateID(self):
        letters = ["A","B","C","D", "E"]
        newID = ""
        while True:
            newID = letters[random.randint(0,4)] + random.randint(0,10)
            if self.IDs.contains(newID):
                print()
            else:
                break
        return newID
    


