package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;

import java.util.List;
import java.util.Random;
public class App {


    private static void generateItems() throws Exception {
        Random random = new Random();
        Item item1 = new Item("Orchid" , 87, "flower", "white","img/orchid.jpg");
        server.saveObject(item1);
        Item item2 = new Item("Lily", 15, "flower", "white","img/orchid.jpg");
        server.saveObject(item2);
        Item item3 = new Item("Tulip", 13, "flower", "orange","img/orchid.jpg");
        server.saveObject(item3);
        Item item4 = new Item( "Rose", 7, "flower", "red","img/orchid.jpg");
        server.saveObject(item4);
        Item item5 = new Item("Dahlia", 9, "flower", "pink","img/orchid.jpg");
        server.saveObject(item5);
        Item item6 = new Item("Lavender", 11, "flower", "purple","img/orchid.jpg");
        server.saveObject(item6);}
 /*
 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.
 */

    public static List<Object> getList(String classn){
        return server.getList(classn);
    }


    public static SimpleServer server;

    public static void main(String[] args) throws Exception {

        server=new SimpleServer(4030);
        generateItems();
        server.listen();
//        server.close();
    }
}
