package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Branch;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.sql.Time;
import java.util.Date;

public class App {


    private static void generateItems() throws Exception {
        Item item = new Item("Orchid" , 87, "Flower", "Purple");
        server.saveObject(item);
        Item item2 = new Item("Lily", 15, "Flower", "White");
        server.saveObject(item2);
        Item item3 = new Item("Tulip", 13, "Flower", "Orange");
        server.saveObject(item3);
        Item item4 = new Item( "Rose", 7, "Flower", "Red");
        server.saveObject(item4);
        Item item5 = new Item("Dahlia", 9, "Flower", "Pink");
        server.saveObject(item5);
        Item item6 = new Item("SunFlower", 11, "Flower", "Yellow");
        server.saveObject(item6);
        Item item7 = new Item("Black Vase", 11, "Vase", "Black");
        server.saveObject(item7);

         item = new Item( "Bouquet 1", 7, "Bouquet", "Red");
        server.saveObject(item);
         item = new Item("Bouquet 2", 9, "Bouquet", "Multicolor");
        server.saveObject(item);
         item = new Item("Bouquet 3", 11, "Bouquet", "Yellow");
        server.saveObject(item);
         item = new Item("Bouquet 4", 11, "Bouquet", "MultiColor");
        server.saveObject(item);
    }
 /*
 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.
 */
    private static void generateBranches() throws Exception {
        Branch b = new Branch();
        server.saveObject(b);
        b = new Branch();
        server.saveObject(b);
        b = new Branch();
        server.saveObject(b);
     }




    public static SimpleServer server;

    public static void main(String[] args) throws Exception {

        server=new SimpleServer(4020);
        generateItems();
      //generateBranches();
        server.listen();
    }
}
