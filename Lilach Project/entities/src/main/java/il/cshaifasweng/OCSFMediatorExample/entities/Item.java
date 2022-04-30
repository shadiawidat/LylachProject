
package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String type;
    private String color;
    private String imagesrc;

    public Item() {
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }





    //private Picture picture;
    public Item(String name, double price,String type,String color,String imagesrc){
        this.name=name;
        this.price=price;
        this.type=type;
        this.color=color;
        this.imagesrc=imagesrc;
    }

    public Item(String lavender, int price, String flower, String purple, String s) {

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
}




