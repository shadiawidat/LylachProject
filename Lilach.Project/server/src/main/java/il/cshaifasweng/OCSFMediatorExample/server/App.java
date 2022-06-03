package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Branch;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.security.Permission;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

    private static void generateUsers() throws Exception {


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


        //Admin

        User user= new User("F","f","Noor","Abu elfoul","Noorabo7@outlook.com","0528218268",new Date(2000,7,26),"Shefa'amer",permissions.ADMIN ,"123456789","123546789",false);
        server.saveObject(user);

        //Branches
        Branch b1 = new Branch("Haifa");
        server.saveObject(b1);
        Branch b2 = new Branch("Nazareth");
        server.saveObject(b2);
        Branch b3 = new Branch("Krayot");
        server.saveObject(b3);

        //Branch Managers
        BranchManager Manager=new BranchManager("Moshe","Grossman","Moshe","Grossman","malkigr@gmail.com","0549999999",new Date(1960,10,18),"Haifa",permissions.MANAGER,"321551287","4580160005429090",false,b1);
        server.saveObject(Manager);
        b1.setBmanager(Manager);

        Manager=new BranchManager("ProfMalki","Grossman","Malki","Grossman","malkigr@gmail.com","0549999999",new Date(1960,10,18),"Haifa",permissions.MANAGER,"321551287","4580160005429090",false,b2);
        server.saveObject(Manager);
        b2.setBmanager(Manager);

        Manager=new BranchManager("eli","albyan","Elias","Haddad","Info@albyan.net","0548888888",new Date(1960,7,16),"Haifa",permissions.MANAGER,"321654127","4580160005429090",false,b3);
        server.saveObject(Manager);
        b3.setBmanager(Manager);

        //Corporation
        List<Branch> branches=new ArrayList<>();
        branches.add(b1);
        branches.add(b2);
        branches.add(b3);
        CoroporationManager Manager1=new CoroporationManager("MsSneh","Shir","Shir","Sneh","shirsneh.uni@gmail.com","0548888888",new Date(1998,10,18),"Haifa",permissions.CorpManager,"321654127",false,branches);
        server.saveObject(Manager1);
        b1.setCmanager(Manager1);
        b2.setCmanager(Manager1);
        b3.setCmanager(Manager1);

        //Worker
        user= new User("Johny","CSapple","John Pierre","Haddad","John.pierre.haddad@gmail.com","0547705173",new Date(1997,1,01),"Haifa",permissions.WORKER,"123456789","123456789" ,false,b1);

        server.saveObject(user);

        user= new User("Lili","LilianCs","Lilian","Mansour","Lilianmansour3@gmail.com","0528345268",new Date(2000,8,16),"Haifa",permissions.WORKER,"123456789","123456789",false,b3 );

        server.saveObject(user);

        user= new User("Shaggy","Shadi123","Shadi","Awidat","Shadiawidat2001@gmail.com","0528020276",new Date(2001,10,18),"Majdal shams",permissions.WORKER,"123456789","123456789" ,false,b2);

        server.saveObject(user);

        Client nClient=new Client("lana31","Lana3182!","Lana","Shehab","lana.shehab0111@gmail.com","0522481844",new Date(2000,2,8),"Haifa",permissions.CLIENT,"206539199","4580404032194023" ,AccountTypes.Premium,0.0);
        nClient.setMybranches(branches);
        b1.AddOneClient(nClient);
        b2.AddOneClient(nClient);
        b3.AddOneClient(nClient);
        server.saveObject(nClient);

        nClient=new Client("Ramkh","ramkh","Ram","Khoury","Ramkh2000@gmail.com","0528020276",new Date(2001,10,18),"Haifa",permissions.CLIENT,"123456789","123456789" ,AccountTypes.Gold,0.0);
        nClient.setMybranches(branches);
        b1.AddOneClient(nClient);
        b2.AddOneClient(nClient);
        b3.AddOneClient(nClient);
        server.saveObject(nClient);

        nClient=new Client("MuradKh","muradkh","Murad","Khoury","Muradkh@gmail.com","0528020276",new Date(2001,10,18),"Haifa",permissions.CLIENT,"123456789","123456789" ,AccountTypes.Basic,0.0);
        nClient.getMybranches().add(b2);
        b2.AddOneClient(nClient);
        server.saveObject(nClient);





    }
 /*
 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.
 */
    private static void generateBranches() throws Exception {

     }




    public static SimpleServer server;

    public static void main(String[] args) throws Exception {

        server=new SimpleServer(3080);
//        generateUsers();
        generateItems();
//        generateBranches();
        server.listen();
    }
}
