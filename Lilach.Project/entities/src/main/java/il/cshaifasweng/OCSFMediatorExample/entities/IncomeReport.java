package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class IncomeReport extends Report implements Serializable {
    private Double Netincome;
    private List<Cart> orders;
    private int totalcount;
    private int canceledcount;
    private int orderscount;
    private List<Cart> canceledorders;

    public Double getNetincome() {
        return Netincome;
    }

    public void setNetincome(Double netincome) {
        Netincome = netincome;
    }

    public List<Cart> getOrders() {
        return orders;
    }

    public void setOrders(List<Cart> orders) {
        this.orders = orders;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getCanceledcount() {
        return canceledcount;
    }

    public void setCanceledcount(int canceledcount) {
        this.canceledcount = canceledcount;
    }

    public int getOrderscount() {
        return orderscount;
    }

    public void setOrderscount(int orderscount) {
        this.orderscount = orderscount;
    }

    public List<Cart> getCanceledorders() {
        return canceledorders;
    }

    public void setCanceledorders(List<Cart> canceledorders) {
        this.canceledorders = canceledorders;
    }
}
