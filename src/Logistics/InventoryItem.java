package Logistics;

import Food.Roll;

public class InventoryItem {

    private Roll roll;
    private int qty;

    public InventoryItem(Roll roll, int qty) {
        this.roll = roll;
        this.qty = qty;
    }

    public void displayItem(){
        System.out.println(this.roll.getName() + ": " + this.qty);
    }

    public Roll getRoll() {
        return roll;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    //Part of Obeserver Design Pattern implementation
    /*
    public static class Announcer implements Observer
    {

        //@Override
        public void update(String message)
        {
            System.out.println("Announcement: " + message);
        }
    }
    */
}
