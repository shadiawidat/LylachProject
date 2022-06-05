package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    @JoinTable(
            name = "Cart_Item",
            joinColumns = { @JoinColumn(name = "cart_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<Item> items=new ArrayList<>();

    @ManyToOne
    @JoinTable(
            name = "Cart_Client",
            joinColumns = { @JoinColumn(name = "cart_id") },
            inverseJoinColumns = { @JoinColumn(name = "Client_id") }
    )
    private Client client;

    private Date date;
    private String blessingticket;
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isForSomeOne() {
        return forSomeOne;
    }

    public void setForSomeOne(boolean forSomeOne) {
        this.forSomeOne = forSomeOne;
    }

    public String getSomeOne() {
        return someOne;
    }

    public void setSomeOne(String someOne) {
        this.someOne = someOne;
    }

    public String getSomeOnePhone() {
        return someOnePhone;
    }

    public void setSomeOnePhone(String someOnePhone) {
        this.someOnePhone = someOnePhone;
    }

    private Boolean delivery;
    private String address;
    private String paymentmethod;
    private boolean Payed;
    private boolean forSomeOne;
    private String someOne;
    private String someOnePhone;

    public Cart() {

    }

    public boolean isPayed() {
        return Payed;
    }

    public void setPayed(boolean payed) {
        Payed = payed;
    }

    public Cart(Client client) {
        this.client = client;
        client.AddOneToCart(this);
        this.date = new Date(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        ;
        Payed = false;
    }

    public Cart(Date date, String blessingticket, Double price, Boolean delivery, String address, String paymentmethod, Client client) {
        super();
        this.date = date;
        this.blessingticket = blessingticket;
        this.price = price;
        this.delivery = delivery;
        this.address = address;
        this.paymentmethod = paymentmethod;
        this.client=client;
        this.Payed=false;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public void DropInCart(Item item)
    {
        items.add(item);
    }
    public void GetOutCart(Item item){
        items.remove(item);
    }
}
