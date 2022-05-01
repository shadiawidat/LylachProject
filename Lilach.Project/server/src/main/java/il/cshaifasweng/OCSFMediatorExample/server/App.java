package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;

public class App {


    private static void generateItems() throws Exception {
        Item item1 = new Item("Orchid" , 87, "flower", "White","/img/");
        server.saveObject(item1);
        Item item2 = new Item("Lily", 15, "flower", "White","/img/");
        server.saveObject(item2);
        Item item3 = new Item("Tulip", 13, "flower", "Orange","/img/");
        server.saveObject(item3);
        Item item4 = new Item( "Rose", 7, "flower", "Red","/img/");
        server.saveObject(item4);
        Item item5 = new Item("Dahlia", 9, "flower", "Pink","/img/");
        server.saveObject(item5);
        Item item6 = new Item("SunFlower", 11, "flower", "Purple","/img/");
        server.saveObject(item6);
        Item item7 = new Item("Black Vase", 11, "Vase", "Black","/img/");
        server.saveObject(item7);
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
        generateItems();
        server.listen();
//        server.close();
    }
}
