package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class OrderReport extends Report  {
    @OneToMany
    private List<Cart> orders;
    private int ordersnum;
    public OrderReport() {
    }

    public OrderReport(int ordersnum) {
        super();
        this.ordersnum = ordersnum;
    }

    public void AddOneCart(Cart c){
        orders.add(c);
    }
    public void DeleteOneCart(Cart c){
        orders.remove(c);
    }
    public List<Cart> getOrders() {
        return orders;
    }

    public void setOrders(List<Cart> orders) {
        this.orders = orders;
    }

    public int getOrdersnum() {
        return ordersnum;
    }

    public void setOrdersnum(int ordersnum) {
        this.ordersnum = ordersnum;
    }
}
