package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private double Discount;
    private String type;
    private String color;
    private String imagesrc;


    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)

    private List<Cart> carts = new ArrayList<>();

    public Item() {
    }

    public Item(String name, double price, String type, String color, double discount) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.color = color;
        this.imagesrc = "/Media/" + type + "/" + name + ".png";
        this.Discount = discount;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void EngageCart(Cart cart) {
        carts.add(cart);
    }

    public void DisEngageCart(Cart cart) {
        carts.remove(cart);
    }

    public void setinfo(String newinfo) {
        String[] info = newinfo.split(" ");
        setName(info[0]);
        setPrice(Double.parseDouble(info[1]));
        setType(info[2]);
        setColor(info[3]);
        setDiscount(Double.parseDouble(info[4]));
    }
}




