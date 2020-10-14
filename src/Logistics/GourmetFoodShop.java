package Logistics;

import Food.*;
import People.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GourmetFoodShop implements Store{

    Map<String, Order> customerOrders;
    ArrayList<Order> dailyOrders;
    Inventory inventory;
    int dailyCustomers;
    int dayNumber;
    String day;
    int numOrders;

    public GourmetFoodShop() {
        inventory = new Inventory(new HashMap<>());
        inventory.addInventory(new jellyRoll(), 30);
        inventory.addInventory(new SausageRoll(), 30);
        inventory.addInventory(new eggRoll(), 30);
        inventory.addInventory(new pastryRoll(), 30);
        inventory.addInventory(new springRoll(), 30);


        this.customerOrders = new HashMap<>();
        this.dailyOrders = new ArrayList<>();
        this.dayNumber = 1;
        this.dailyCustomers = 0;
    }

    @Override
    public void open() {


    }

    @Override
    public void close() {
        //Close the shop and increment the day and replenish stock if it is out
        dayNumber++;

        //count through stock checking to see if any is out
    }

    @Override
    public void respondToOrder(Order newOrder) {

        /*
        method to check stock to make sure we could fulfill the order, If true, respond to Customer
        to let them buy the rolls and so we can record the order

        If false, allow them to use their respond to roll outage function.
         */

        boolean stockAvailable = true;

        for(OrderItem item : newOrder.getItems()){
            //want to get stock of current roll type
            stockAvailable = inventory.decrementInventory(item.roll.getKey(), item.quantity);
        }
        //if there was stock to complete the order, save order
        if (stockAvailable){
            this.dailyOrders.add(newOrder);
        }

        System.out.println();
        displayStock();
    }

    public void displayStock(){
        inventory.displayInventory();
    }
}
