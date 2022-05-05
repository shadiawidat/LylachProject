package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class OrderReport extends Report implements Serializable {
    private List<Cart> orders;
    private int ordersnum;

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
