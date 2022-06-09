package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;

public class App extends Application {

    private static Scene scene;

    private static Stage theStage;
    private static void generateUsers() throws Exception {


    }
    public static void generateItems() throws Exception {
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
         item = new Item( "Valentine", 7, "Bouquet", "Red",35);
        server.saveObject(item);
         item = new Item("Passion", 9, "Bouquet", "MultiColor",10);
        server.saveObject(item);
         item = new Item("Evelyn", 11, "Bouquet", "Yellow",5);
        server.saveObject(item);
         item = new Item("Amour", 11, "Bouquet", "MultiColor",0);
        server.saveObject(item);

        item = new Item("Garden Gloves", 18, "Gardening", "Yellow",10);
        server.saveObject(item);
        item = new Item("Hoe", 15, "Gardening", "Black",0);
        server.saveObject(item);
        item = new Item("Shovel", 15, "Gardening", "Black",0);
        server.saveObject(item);
        item = new Item("Hedge Shears", 12, "Gardening", "Orange",0);
        server.saveObject(item);
        item = new Item("Shovel Set", 38, "Gardening", "Black",30);
        server.saveObject(item);

        item = new Item("White Bouquet", 85, "Wedding", "White",0);
        server.saveObject(item);
        item = new Item("Purple Bouquet", 90, "Wedding", "Purple",15);
        server.saveObject(item);
        item = new Item("Pink Bouquet", 80, "Wedding", "Pink",0);
        server.saveObject(item);
        item = new Item("Red Bouquet", 90, "Wedding", "Red",0);
        server.saveObject(item);

        //Admin

        User user= new User("Fole","fole","Noor","Abu elfoul","Noorabo7@outlook.com","0528218268",new Date(2000-1900,7-1,26+1),"Shefa'amer",permissions.ADMIN ,"123456789","123546789",false);
        server.saveObject(user);

        //Branches
        Branch b1 = new Branch("haifa");
        server.saveObject(b1);
        Branch b2 = new Branch("nazareth");
        server.saveObject(b2);
        Branch b3 = new Branch("krayot");
        server.saveObject(b3);
        Branch b4 = new Branch("karmiel");
        server.saveObject(b4);
        Branch b5 = new Branch("natanya");
        server.saveObject(b5);
        Branch b6 = new Branch("nahariya");
        server.saveObject(b6);
        Branch b7 = new Branch("herzilya");
        server.saveObject(b7);
        Branch b8 = new Branch("telaviv");
        server.saveObject(b8);
        Branch b9 = new Branch("hadera");
        server.saveObject(b9);
        Branch b10 = new Branch("shefaraam");
        server.saveObject(b10);




        //Branch Managers
        BranchManager Manager=new BranchManager("moshe","Grossman","Moshe","Grossman","malkigr@gmail.com","0549999999",new Date(1960-1900,6-1,1+1),"Haifa",permissions.MANAGER,"321679287","4580160005429090",false,b1);
        server.saveObject(Manager);

        Manager=new BranchManager("profmalki","Grossman","Malki","Grossman","malkigr@gmail.com","0549999999",new Date(1970-1900,9-1,17+1),"Nazareth",permissions.MANAGER,"321551287","4580160005429090",false,b2);
        server.saveObject(Manager);

        Manager=new BranchManager("eli","albyan","Elias","Haddad","Info@albyan.net","0548888888",new Date(1980-1900,7-1,16+1),"Tel Aviv",permissions.MANAGER,"321654127","4580145554666567",false,b3);
        server.saveObject(Manager);

        Manager=new BranchManager("maya","Mayah","Maya","Hanna","mayah168@hotmail.com","0541111111",new Date(1990-1900,1-1,3+1),"Natanya",permissions.MANAGER,"567689407","4580161111429090",false,b1);
        server.saveObject(Manager);

        Manager=new BranchManager("alex","Brams","Alexander","Brams","bramsalex@outlook.com","0541234567",new Date(1950-1900,7-1,5+1),"Baer Shevaa",permissions.MANAGER,"465378290","6574830005429090",false,b2);
        server.saveObject(Manager);

        Manager=new BranchManager("leen","segseg","Leen","Segal","leenseg3@gmail.co.il","0547654321",new Date(1965-1900,2-1,8+1),"Shefaraam",permissions.MANAGER,"947658392","4583425167429090",false,b3);
        server.saveObject(Manager);

        Manager=new BranchManager("polina","Amar33","Polina","Gidon","polina225@gmail.com","0542223334",new Date(1975-1900,11-1,11+1),"Haifa",permissions.MANAGER,"234567231","1234560005429090",false,b2);
        server.saveObject(Manager);

        Manager=new BranchManager("avi","Golan","Avishay","Golan","avigolan@hotmail.com","0543452135",new Date(1967-1900,12-1,24+1),"Karmael",permissions.MANAGER,"111222333","4580160005123456",false,b1);
        server.saveObject(Manager);

        Manager=new BranchManager("yacov","yac123","yacov","Levi","leviyac2@gmail.com","0545647859",new Date(1983-1900,8-1,30+1),"Nahariya",permissions.MANAGER,"321344555","4580123456429090",false,b2);
        server.saveObject(Manager);

        Manager=new BranchManager("itay","itay773","Itay","Godiva","itaygo@outlook.com","0548674849",new Date(1974-1900,4-1,22+1),"Krayot",permissions.MANAGER,"765839024","4500000054290090",false,b3);
        server.saveObject(Manager);



        //Corporation
        CoroporationManager Manager1=new CoroporationManager("sneh","Shir","Shir","Sneh","shirsneh.uni@gmail.com","0548888888",new Date(1998-1900,10-1,18+1),"Haifa",permissions.CorpManager,"321654127",false);
        server.saveObject(Manager1);

        //Worker
        user= new User("johnny","CSapple","John Pierre","Haddad","John.pierre.haddad@gmail.com","0547705173",new Date(1997-1900,1-1,01+1),"Haifa",permissions.WORKER,"123456789","1234567896543210" ,false,b1);
        server.saveObject(user);
        user= new User("lili","Lilian168","Lilian","Mansour","Lilianmansour3@gmail.com","0528345268",new Date(2000-1900,8-1,16+1),"Haifa",permissions.WORKER,"123456789","1234567890123456",false,b3 );
        server.saveObject(user);
        user= new User("shaggy","Shadi123","Shadi","Awidat","Shadiawidat2001@gmail.com","0528020276",new Date(2001-1900,10-1,18+1),"Majdal shams",permissions.WORKER,"111111119","1111234566665555" ,false,b2);
        server.saveObject(user);
        user= new User("adam","ada23&","Adam","Hawa","adamhawa21@outlook.com","05285665276",new Date(1985-1900,8-1,8+1),"Nazareth",permissions.WORKER,"143526789","3456888779123456" ,false,b1);
        server.saveObject(user);
        user= new User("sama","Sama123","Sama","Saad","samasaad20@hotmail.com","0528020234",new Date(1998-1900,3-1,1+1),"Tel Aviv",permissions.WORKER,"123455555","123456456781239" ,false,b3);
        server.saveObject(user);
        user= new User("sandy","Ibr256","Sandy","Ibrahim","sandy2001@gmail.co.il","0521230276",new Date(1990-1900,2-1,15+1),"Krayot",permissions.WORKER,"222226789","122313445656789" ,false,b1);
        server.saveObject(user);
        user= new User("george","geor@1","George","Alayof","alayofgeo01@gmail.com","0528123456",new Date(2003-1900,7-1,27+1),"Hadera",permissions.WORKER,"111116789","123434556756789" ,false,b2);
        server.saveObject(user);
        user= new User("ben","Ben23!","Ben","Hazan","benhazan@gmail.co.il","0528021334",new Date(2002-1900,12-1,31+1),"Karmiel",permissions.WORKER,"126666789","456123456789123" ,false,b3);
        server.saveObject(user);
        user= new User("liel","liel123","Liel","Daniel","lieldaniel23@hotmail.com","0533330276",new Date(1999-1900,11-1,21+1),"Natanya",permissions.CustomerServiceWorker,"123456789","123123457896789" ,false,b2);
        server.saveObject(user);
        user= new User("alber","Eli34@!","Albert","Eli","elialbert5@outlook.com","0528444476",new Date(2000-1900,9-1,4+1),"Nazareth",permissions.CustomerServiceWorker,"123456789","123444456333789" ,false,b1);
        server.saveObject(user);


        Client LClient=new Client("lana31","Lana3182!","Lana","Shehab","lana.shehab0111@gmail.com","0522481844",new Date(2000-1900,2-1,8+1),"Haifa",permissions.CLIENT,"206539199","4580404032194023" ,AccountTypes.Premium,300.0);
        server.saveObject(LClient);
        Client RClient=new Client("ramkh","ramkh","Ram","Khoury","Ramkh2000@gmail.com","0528020276",new Date(2001-1900,10-1,18+1),"Haifa",permissions.CLIENT,"123456789","123456789" ,AccountTypes.Gold,100.0);
        server.saveObject(RClient);
        Client nClient=new Client("muradkh","muradkh","Murad","Khoury","Muradkh@gmail.com","0528020276",new Date(2001-1900,10-1,18+1),"Haifa",permissions.CLIENT,"123456789","123456789" ,AccountTypes.Basic,15.0);
        server.saveObject(nClient);
        nClient=new Client("lara","abboudd","Lara","Abboud","larabaid@hotmail.co.il","0528111276",new Date(1999-1900,2-1,28+1),"Krayot",permissions.CLIENT,"123452345","1888234564446789" ,AccountTypes.Basic,150.0);
        server.saveObject(nClient);
        nClient=new Client("marian","mariankh@","Marian","Shama","marian356@gmail.com","0528222276",new Date(1984-1900,8-1,13+1),"Herzilya",permissions.CLIENT,"122345789","1333434588886789" ,AccountTypes.Basic,250.0);
        server.saveObject(nClient);
        nClient=new Client("wass","wass31!","Wissam","Najjar","wissnajh@outlook.com","0512340276",new Date(2000-1900,10-1,13+1),"Baer shevaa",permissions.CLIENT,"401376789","6681234234556789" ,AccountTypes.Gold,500.0);
        server.saveObject(nClient);
        nClient=new Client("tom","tom647","Tom","Baum","baumtom33@gmail.co.il","0528020234",new Date(2003-1900,9-1,9+1),"HNatanya",permissions.CLIENT,"123477777","1345623456789789" ,AccountTypes.Basic,60.0);
        server.saveObject(nClient);
        nClient=new Client("daniel","levir42","Daniel","Levi","danielhm@hotmail.co.il","0528570276",new Date(1993-1900,7-1,3+1),"Krayot",permissions.CLIENT,"103458889","1456234512346789" ,AccountTypes.Basic,650.0);
        server.saveObject(nClient);
        nClient=new Client("leam","Leamor4","Leam","Mor","leamoh46@gmail.com","0528055555",new Date(2002-1900,5-1,8+1),"Shefaraam",permissions.CLIENT,"123000089","1234234545636789" ,AccountTypes.Premium,100.0);
        server.saveObject(nClient);
        nClient=new Client("noel","Nono45","Noel","Cohen","noelco54hotmail.com","0587920276",new Date(1981-1900,1-1,18+1),"Hadera",permissions.CLIENT,"103496789","1234567890354782" ,AccountTypes.Basic,50.0);
        server.saveObject(nClient);

        server.makeitwork();
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

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(getClass().getResource(""));
        Parent root = FXMLLoader.load(getClass().getResource("Connect.fxml"));

        stage.setTitle("LyLach");
        scene=new Scene(root, 350, 150);
        stage.setScene(scene);
        stage.show();

    }


}
