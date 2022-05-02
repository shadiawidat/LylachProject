package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;

public class App {


    private static void generateItems() throws Exception {
        Item item = new Item("Orchid" , 87, "Flower", "Purple","/img/");
        server.saveObject(item);
        Item item2 = new Item("Lily", 15, "Flower", "White","/img/");
        server.saveObject(item2);
        Item item3 = new Item("Tulip", 13, "Flower", "Orange","/img/");
        server.saveObject(item3);
        Item item4 = new Item( "Rose", 7, "Flower", "Red","/img/");
        server.saveObject(item4);
        Item item5 = new Item("Dahlia", 9, "Flower", "Pink","/img/");
        server.saveObject(item5);
        Item item6 = new Item("SunFlower", 11, "Flower", "Yellow","/img/");
        server.saveObject(item6);
        Item item7 = new Item("Black Vase", 11, "Vase", "Black","/img/");
        server.saveObject(item7);

         item = new Item( "Bouquet 1", 7, "Bouquet", "Red","/img/");
        server.saveObject(item);
         item = new Item("Bouquet 2", 9, "Bouquet", "Multicolor","/img/");
        server.saveObject(item);
         item = new Item("Bouquet 3", 11, "Bouquet", "Yellow","/img/");
        server.saveObject(item);
         item = new Item("Bouquet 4", 11, "Bouquet", "MultiColor","/img/");
        server.saveObject(item);
    }
 /*
 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.
 */


    public static SimpleServer server;

    public static void main(String[] args) throws Exception {

        server=new SimpleServer(3050);
//        generateItems();
        server.listen();
//        server.close();
    }
}
