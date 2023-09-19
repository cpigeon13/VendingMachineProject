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
    def __str__():
        return self.ID + " - " + self.name + ": " + self.price + " (" + self.quantity + " in stock)"
    