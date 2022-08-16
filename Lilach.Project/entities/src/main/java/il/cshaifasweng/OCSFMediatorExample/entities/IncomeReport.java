package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class IncomeReport extends Report implements Serializable {


    @OneToMany
    private List<Cart> canceledorders = new ArrayList<>();
    @OneToMany
    private List<Cart> orders = new ArrayList<>();


    private int totalcount;
    private int canceledcount;
    private int orderscount;
    private Double Netincome;


    public IncomeReport(Double netincome, int totalcount, int canceledcount, int orderscount) {
        Netincome = 0.0;
        this.totalcount = 0;
        this.canceledcount = 0;
        this.orderscount = 0;
    }

    public IncomeReport() {
        Netincome = 0.0;
        this.totalcount = 0;
        this.canceledcount = 0;
        this.orderscount = 0;
        this.setReportType(ReportType.INCOME);
    }

    public void IncOrders() {
        orderscount++;
    }

    public void DecOrders() {
        orderscount--;
    }

    public void IncCanceled() {
        canceledcount++;
    }

    public void DecCanceled() {
        canceledcount--;
    }

    public void IncTotal() {
        totalcount++;
    }

    public void DecTotal() {
        totalcount--;
    }

    public void IncNet(double price) {
        Netincome += price;
    }

    public void DecNet(double price) {
        Netincome -= price;
    }

    public void AddOneCart(Cart c) {
        orders.add(c);
    }

    public void DeleteOneCart(Cart c) {
        orders.remove(c);
    }

    public void AddOneCanceledCart(Cart c) {
        canceledorders.add(c);
    }

    public void DeleteOneCanceledCart(Cart c) {
        canceledorders.remove(c);
    }

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
