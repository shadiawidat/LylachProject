package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Branch;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.security.Permission;
import java.sql.Time;
import java.util.Date;

public class App {

    private static void generateUsers() throws Exception {

        User user= new User("Fole","Noor26","Noor","Abu elfoul","Noorabo7@outlook.com","0528218268",new Date(2000,7,26),"Shefa'amer",permissions.CLIENT );
        server.saveObject(user);

         user= new User("Johnny","CSapple","John Pierre","Haddad","John.pierre.haddad@gmail.com","0547705173",new Date(1997,1,01),"Haifa",permissions.CLIENT );
        server.saveObject(user);

         user= new User("Lili","LilianCs","Lilian","Mansour","Lilianmansour3@gmail.com","0528345268",new Date(2000,8,16),"Haifa",permissions.CLIENT );
        server.saveObject(user);

         user= new User("Shaggy","Shadi123","Shadi","Awidat","Shadiawidat2001@gmail.com","0528020276",new Date(2001,10,18),"Majdal shams",permissions.CLIENT );
        server.saveObject(user);
    }
    private static void generateItems() throws Exception {
        Item item = new Item("Orchid" , 87, "Flower", "Purple",0);
        server.saveObject(item);
         item = new Item("Lily", 15, "Flower", "White",0);
        server.saveObject(item);
         item = new Item("Tulip", 13, "Flower", "Orange",23);
        server.saveObject(item);
         item = new Item( "Rose", 7, "Flower", "Red",0);
        server.saveObject(item);
         item = new Item("Dahlia", 9, "Flower", "Pink",0);
        server.saveObject(item);
         item = new Item("SunFlower", 11, "Flower", "Yellow",0);
        server.saveObject(item);
         item = new Item("Black Vase", 11, "Vase", "Black",20);
        server.saveObject(item);
         item = new Item( "Bouquet 1", 7, "Bouquet", "Red",35);
        server.saveObject(item);
         item = new Item("Bouquet 2", 9, "Bouquet", "MultiColor",10);
        server.saveObject(item);
         item = new Item("Bouquet 3", 11, "Bouquet", "Yellow",5);
        server.saveObject(item);
         item = new Item("Bouquet 4", 11, "Bouquet", "MultiColor",0);
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

        server=new SimpleServer(3330);
        generateUsers();
        generateItems();

        //generateBranches();
        server.listen();
    }
}
