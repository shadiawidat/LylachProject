package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    private List<Item> items;
    @OneToOne
    private User client;
    private Date date;
    private String blessingticket;
    private Double price;
    private Boolean delivery;
    private String address;

    public Cart(Date date, String blessingticket, Double price, Boolean delivery, String address, String paymentmethod) {
        super();
        this.date = date;
        this.blessingticket = blessingticket;
        this.price = price;
        this.delivery = delivery;
        this.address = address;
        this.paymentmethod = paymentmethod;
    }

    public Cart() {

    }
    public void AddOneItem(Item i){
        items.add(i);
    }
    public void DeleteOneItem(Item i){
        items.remove(i);
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBlessingticket() {
        return blessingticket;
    }

    public void setBlessingticket(String blessingticket) {
        this.blessingticket = blessingticket;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    private String paymentmethod;
}
