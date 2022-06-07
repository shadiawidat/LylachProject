package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Cart_Client",
            joinColumns = { @JoinColumn(name = "cart_id") },
            inverseJoinColumns = { @JoinColumn(name = "Client_id") }
    )
    private Client client;

    private LocalDateTime date;
    private Date Deliverydate;
    private String blessingticket;
    private Double price;
    private int Hour;
    private int Minute;
    private Boolean canceled=false;

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMinute() {
        return Minute;
    }

    public void setMinute(int minute) {
        Minute = minute;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Date getDeliverydate() {
        return Deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        Deliverydate = deliverydate;
    }

    public Cart(Client client) {
        this.client = client;
        client.AddOneToCart(this);
//        this.date = new Date(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getDayOfMonth());
        ;
        Payed = false;
    }

    public Cart(Date date, String blessingticket, Double price, Boolean delivery, String address, String paymentmethod, Client client) {
        super();
//        this.date = date;
        this.blessingticket = blessingticket;
        this.price = price;
        this.delivery = delivery;
        this.address = address;

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

    public LocalDateTime getDate() {
        return date;
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

    public void DropInCart(Item item)
    {
        items.add(item);
    }
    public void GetOutCart(Item item){
        items.remove(item);
    }
}
