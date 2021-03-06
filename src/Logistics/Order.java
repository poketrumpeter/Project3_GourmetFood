package Logistics;

import Food.Factories.DefaultRollFactory;
import Food.Factories.ExtrasFactory;
import Food.Roll;

import java.util.ArrayList;
import java.util.Random;

public class Order {

    public float orderNumber;
    private ArrayList<OrderItem> items;
    double orderTotal;
    boolean fulfilled;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        this.items = new ArrayList<>();
        this.orderTotal = 0;
        this.fulfilled = false;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    //Form an order and add toppings based on RollKey and quantity of rolls
    public void addItems(String rollKey, int quantity) {

        DefaultRollFactory rollFactory = new DefaultRollFactory();

        for (int i = 0; i < quantity; i++) {
            //For each roll, add toppings
            Roll newRoll = rollFactory.createRoll(rollKey);

            Roll wrappedRoll = addToppings(newRoll);
            //Roll wrappedRoll = newRoll;

            items.add(new OrderItem(wrappedRoll, 1));

            this.orderTotal += wrappedRoll.cost();
        }

        //this.items.add(new OrderItem(roll, quantity));
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public int getNumberOfRolls() {
        int numRolls = 0;
        for (OrderItem item : this.items) {
            numRolls += item.quantity;
        }
        return numRolls;
    }

    public String getItemType(int index){
        return items.get(index).roll.getKey();
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void displayOrder() {

        for (OrderItem item : this.items) {
            System.out.print(item.quantity + " - " + item.roll.getName() + ": "); // + item.roll.cost());
            System.out.printf("%.2f", item.roll.cost());
            System.out.println();
        }

        System.out.print("Total Order cost is: ");
        System.out.printf("%.2f", orderTotal);

        System.out.println();
    }

    private Roll addToppings(Roll rollToWrap){ //wrap roll with decorators

        //Wrapping roll with random choice for fillings

        //declare new extrasfactory based on rollType
        ExtrasFactory newExtras = rollToWrap.getExtrasFactory();

        Random rand = new Random();
        int fillings = rand.nextInt(2); //Options of 0 and 1 filling

        //if fillings = 0, should not wrap
        //if fillings = 1, wrap once
        for (int i = 0; i < (fillings); i++){
            rollToWrap = newExtras.addFilling(rollToWrap);
        }

        int sauce = rand.nextInt(4);
        //Wrapping roll with random choice for toppings
        for(int i = 0; i < 3; i++){
            rollToWrap = newExtras.addSauce(rollToWrap);
        }

        //Wrapping roll with random choice for sauces
        int toppings = rand.nextInt(3);
        for (int i = 0; i < 2; i++){
            rollToWrap = newExtras.addTopping(rollToWrap);
        }


        return rollToWrap;
    }

    public double currentCost(int index){
        return items.get(index).roll.cost();
    }
}
