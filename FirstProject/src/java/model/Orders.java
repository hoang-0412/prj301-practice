package model;

import java.sql.Date;

public class Orders {

    int orderID;
    Date orderDate;
    double total;
    String userID;

    public Orders() {
    }

    public Orders(int orderID, Date orderDate, double total, String userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
    }

    public Orders(Date orderDate, double total, String userID) {
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", orderDate=" + orderDate + ", total=" + total + ", userID=" + userID + '}';
    }

}
