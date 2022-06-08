package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.security.Permission;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SimpleServer extends AbstractServer {

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.

        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Report.class);
        configuration.addAnnotatedClass(Branch.class);
        configuration.addAnnotatedClass(Cart.class);
        configuration.addAnnotatedClass(Complain.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(BranchManager.class);
        configuration.addAnnotatedClass(CoroporationManager.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(IncomeReport.class);
        configuration.addAnnotatedClass(OrderReport.class);
        configuration.addAnnotatedClass(ComplainReport.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Collection<Object> getList(String classn) {


//		if(classn.equals("#getItems")){
//			CriteriaBuilder builder=session.getCriteriaBuilder();
//			CriteriaQuery<Item> query=builder.createQuery(Item.class);
//			query.from(Item.class);
//			List<Item> items=session.createQuery(query).getResultList();
//			return items;
//		}
        return null;
    }

    public SimpleServer(int port) throws IOException {
        super(port);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        listen();
    }

    protected Object findObject(String classn, String id) {
        session.beginTransaction();

        if (classn.equals("Item")) {
            return session.find(Item.class, id);
        }

        session.getTransaction().commit();
        return null;
    }

    protected void saveObject(Object o) {
        session.beginTransaction();

        session.save(o);
        session.flush();

        session.getTransaction().commit();

    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        System.out.println("Client connected: " + client.getInetAddress());
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        // TODO Auto-generated method stub

        System.out.println("Client disconnected.");
        super.clientDisconnected(client);
    }

    protected void saveObjectList(List<Object> list) {
        session.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            session.save(list.get(i));
            session.flush();
        }

        session.getTransaction().commit();
    }

    protected void deleteObject(Object o) {
        session.beginTransaction();

        session.delete(o);
        session.flush();

        session.getTransaction().commit();
    }

    protected void deleteObjectList(List<Object> list) {
        session.beginTransaction();

        for (int i = 0; i < list.size(); i++) {
            session.delete(list.get(i));
        }
        session.flush();

        session.getTransaction().commit();
    }

    protected void setInfo(int itemId, double price) {
        session.beginTransaction();
        session.find(Item.class, itemId).setPrice(price);
        session.getTransaction().commit();
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException {
        Message ms = (Message) msg;
        String request = ms.getString();

        if (request.startsWith("#RemoveUser")) {
            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);
            System.out.println(msgarray[1]);
            if (user != null) {
                session.beginTransaction();
                session.delete(user);
                session.flush();
                session.getTransaction().commit();
                client.sendToClient(new Message(null, "#UserRemoved"));
            } else {
                client.sendToClient(new Message(user, "#RemoveUserNotFound"));
            }
        }
        if (request.startsWith("#SignOut")) {
            session.beginTransaction();
            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);

            if (user != null) {

                session.find(User.class, msgarray[1]).setLogedIn(false);
                session.flush();
                session.getTransaction().commit();
                client.sendToClient(new Message(null, "#UserSignOut"));

            }
        }
        if(request.startsWith("#AddItem"))
        {
            saveObject(ms.getObject());
            client.sendToClient(new Message(null,"#AddNewItem"));
        }
        if (request.startsWith("#LogIn")) {
            String[] msgarray = request.split(" ");
            session.beginTransaction();
            User user = session.find(User.class, msgarray[1]);

            if (user != null && user.getPassword().equals(msgarray[2])) {
                if(user.getPermission()==permissions.CLIENT)
                {
                    if(((Client)user).getMemberShipt().plusYears(1).isBefore(LocalDateTime.now()))
                    {
                        ((Client) user).setAccounttype(AccountTypes.Gold);
                    }
                }
                client.sendToClient(new Message(user, "#Useridentify"));
                if(user.isFreeze()==false)
                    session.find(User.class, msgarray[1]).setLogedIn(true);
                session.flush();
                session.getTransaction().commit();



            } else {
                session.getTransaction().commit();
                client.sendToClient(new Message(null, "#Useridentify"));

            }
            System.out.println("1");
        }

        if (request.startsWith("#UserExist")) {
            String[] msgarray = request.split(" ");
            Client user = session.find(Client.class, msgarray[1]);
            if (user != null) {
                client.sendToClient(new Message(user, "#UserExists"));
            } else {


                if(msgarray[2].equals("-1") || ((Client) ms.getObject()).getAccounttype() == AccountTypes.Premium){
                    double x = ((Client) ms.getObject()).getAmount();
                    ((Client) ms.getObject()).setAmount(x-100);
                }


                saveObject((Client) ms.getObject());
                session.beginTransaction();
                user = session.find(Client.class, msgarray[1]);
                if (!msgarray[2].equals("-1")) {
                    user.AddOneBranch(session.find(Branch.class, msgarray[2]));
                    session.find(Branch.class, msgarray[2]).getUsers().add(user);
                } else {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
                    query.from(Branch.class);
                    List<Branch> branches = session.createQuery(query).getResultList();

                    for (Branch branch : branches) {
                        user.AddOneBranch(branch);
                        branch.getUsers().add(user);
                    }
                }
                session.flush();
                session.getTransaction().commit();
                client.sendToClient(new Message(ms.getObject(), "#UserCreated"));
            }

        }
        if (request.startsWith("#AddUser")) {

            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);
            Branch b = session.find(Branch.class, msgarray[2]);
            System.out.println(b.getName());
            System.out.println("hello");
            if (user != null) {
                client.sendToClient(new Message(user, "#AddUserExists"));
            } else {

                App.server.saveObject(ms.getObject());
                System.out.println(msgarray[1]);
                System.out.println(msgarray[2]);
                User use = session.find(User.class, msgarray[1]);
                Branch b1 = session.find(Branch.class, msgarray[2]);
                if(b1!=null) {
                    System.out.println("hon");
                    session.beginTransaction();
                    b1.getUsers().add(use);
                    use.getMybranches().add(b1);
                    session.flush();
                    session.getTransaction().commit();
                }
                else {
                    System.out.println("mesh hon");
                    b1=new Branch(msgarray[2]);
                    App.server.saveObject(b1);
                    b1 = session.find(Branch.class, msgarray[2]);
                    session.beginTransaction();
                    b1.getUsers().add(use);
                    use.getMybranches().add(b1);
                    session.flush();
                    session.getTransaction().commit();

                }
                client.sendToClient(new Message(ms.getObject(), "#AddUserCreated"));
            }


        }
        if (request.startsWith("#AddServiceWorker")) {

            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);

            if (user != null) {

                client.sendToClient(new Message(user, "#AddUserExists"));
            } else {

                App.server.saveObject(ms.getObject());
                System.out.println(msgarray[1]);

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
                query.from(Branch.class);
                List<Branch> branches = session.createQuery(query).getResultList();

                User use = session.find(User.class, msgarray[1]);
                System.out.println("loz");
                System.out.println(use.getUsername());
                session.beginTransaction();
                for(Branch branch : branches){
                    branch.getUsers().add(use);
                    use.getMybranches().add(branch);
                    session.flush();

                }
                session.getTransaction().commit();
                client.sendToClient(new Message(ms.getObject(), "#AddUserCreated"));
            }


        }
        if (request.startsWith("#SearchUser")) {

            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);

            if (user != null) {


                client.sendToClient(new Message(user, "#UserFound"));

            } else {
                client.sendToClient(new Message(null, "#UserNotFound"));
            }
            System.out.println("3");
        }
        if (request.startsWith("#FreezeUser")) {
            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);
            if (user != null) {
                if (user.getPermission().equals(permissions.ADMIN)) {
                    return;
                }
                user.setFreeze(!user.isFreeze());
                client.sendToClient(new Message(user, "#UserFreezed"));
            }
        }

        if (request.startsWith("#GetCart")) {
            String[] msgarray = request.split(" ");
            Client nclient = session.find(Client.class, msgarray[1]);
            if (nclient != null) {
                for (Cart cart : nclient.getMyorders()) {
                    if (cart.isPayed() == false) {
                        client.sendToClient(new Message(cart.getItems(), "#CartReady"));
                        return;
                    }
                }
                client.sendToClient(new Message(new ArrayList<>(), "#CartReady"));
                return;
            } else {
                client.sendToClient(new Message(null, "#CartUserNotFound"));
            }
        }
        if (request.startsWith("#RemoveFromCart")) {

            String[] msgarray = request.split(" ");
            Client nclient = session.find(Client.class, msgarray[1]);
            System.out.println(nclient.getFirstname());
            for (Cart cart : nclient.getMyorders()) {
                if (cart.isPayed() == false) {

                    int size = cart.getItems().size();

                    for (int i = 0; i < cart.getItems().size(); i++) {
                        if (cart.getItems().get(i).getId() == ((Item) ms.getObject()).getId()) {
                            cart.getItems().remove(i);
                            i--;

                        }
                    }
                    client.sendToClient(new Message(cart.getItems(), "#CartReady"));

                    return;
                }
            }

        }
        if (request.startsWith("#ApproveShipping")) {
            System.out.println("here1");
            String[] msgarray = request.split("±");
            session.beginTransaction();
            System.out.println("here2");
            List<Cart> myorders = session.find(Client.class, msgarray[1]).getMyorders();
            System.out.println("here3");
            for (Cart cart : myorders) {
                System.out.println("here4");
                if (cart.isPayed() == false) {

                    cart.setPayed(true);
                    cart.setAddress(msgarray[2]);
                    cart.setSomeOne(msgarray[3]);
                    cart.setSomeOnePhone(msgarray[4]);
                    cart.setBlessingticket(msgarray[5]);

                    cart.setDelivery(msgarray[6].equals("true"));
                    System.out.println("here6");
                    cart.setForSomeOne(!msgarray[3].equals(""));
                    System.out.println("here7");
                    System.out.println(msgarray[10]);
                    cart.setPrice(Double.parseDouble(msgarray[10]));
                    System.out.println("1");
                    cart.setHour(Integer.parseInt(msgarray[11]));
                    cart.setMinute(Integer.parseInt(msgarray[12]));
                    System.out.println("2");
                    //cart.
                    int year =Integer.parseInt(msgarray[7]);
                    int month=Integer.parseInt(msgarray[8]);
                    int day=Integer.parseInt(msgarray[9]);
                    Date d = new Date(Integer.parseInt(msgarray[7])-1900, Integer.parseInt(msgarray[8])-1, Integer.parseInt(msgarray[9])+1);
                    Date date1 = (Date) ms.getObject();
                    System.out.println(year);
                    System.out.println(month);
                    System.out.println(day);
                    System.out.println(d);
                    cart.setDeliverydate(d);
                    cart.setDate(LocalDateTime.now());
                    session.flush();
                    session.getTransaction().commit();
                    client.sendToClient(new Message(null,"#ShippmentApproved"));
                    return;
                }
            }
        }
        if (request.startsWith("#RemoveOneFromCart")) {
            String[] msgarray = request.split(" ");
            Client nclient = session.find(Client.class, msgarray[1]);
            System.out.println(nclient.getFirstname());
            for (Cart cart : nclient.getMyorders()) {
                if (cart.isPayed() == false) {
                    for (int i = 0; i < cart.getItems().size(); i++) {
                        if (cart.getItems().get(i).getId() == ((Item) ms.getObject()).getId()) {
                            cart.getItems().remove(i);
                            break;
                        }
                    }

                    client.sendToClient(new Message(cart.getItems(), "#RemovedOne"));
                    return;
                }
            }

        }
        if (request.startsWith("#GetOrders")) {
            String[] msgarray = request.split(" ");
            Client nclient = session.find(Client.class, msgarray[1]);
            client.sendToClient(new Message(nclient.getMyorders(), "#OrdersReady"));
        }


        if (request.startsWith("#SendingComplain")) {
            String[] msgarray = request.split("±");
            Client nclient = session.find(Client.class, msgarray[1]);

            Branch nbranch = session.find(Branch.class, msgarray[3]);

            List<Complain> mycomplains = session.find(Client.class, msgarray[1]).getComplains();
            Complain complain = new Complain(msgarray[2], null);

            complain.setClient(nclient);
            complain.setBranch(nclient.getMybranches().get(0));///////get(0)
            complain.setDate((Date) (ms.getObject()));
            mycomplains.add(complain);
            nclient.getMybranches().get(0).AddOneComplain(complain);


            App.server.saveObject(complain);

            session.beginTransaction();
            nbranch.AddOneComplain(complain);
            session.flush();
            session.getTransaction().commit();


            //session.find(Item.class, ((Item) ms.getObject()).getId()).getCarts().add(cartt);



            //Client nclient = session.find(Client.class, msgarray[1]);
            client.sendToClient(new Message(null, "#ComplainSent"));
        }


        if (request.startsWith("#GetComplains")) {
            String[] msgarray = request.split(" ");
            List<Complain> complainList=new ArrayList<>();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = builder.createQuery(Client.class);
            query.from(Client.class);
            List<Client> clients = session.createQuery(query).getResultList();

            for(Client c : clients){
                for(Complain complain : c.getComplains()){
                    complainList.add(complain);
                }
            }
            System.out.println(complainList.size());

            client.sendToClient(new Message(complainList, "#ComplainsReady"));

        }

        if (request.startsWith("#UpdateUser")) {

            session.beginTransaction();
            String[] msgarray = request.split(" ");
            System.out.println("a");
            System.out.println(msgarray.length);
            User user = session.find(User.class, msgarray[1]);
            User userrr = (User) ms.getObject();

            if (user != null) {
                user.setFirstname(userrr.getFirstname());
                user.setLastname(userrr.getLastname());
                user.setID(userrr.getID());
                user.setAddress(userrr.getAddress());
                user.setPhonenumber(userrr.getPhonenumber());
                user.setEmail(userrr.getEmail());

                if (user.getPermission() == permissions.CLIENT) {

                    ((Client) user).setCreditCard(((Client) userrr).getCreditCard());
                    ((Client) user).setAccounttype(((Client) userrr).getAccounttype());
                    ((Client) user).setAmount(((Client) userrr).getAmount());
                    if(((Client) userrr).getAccounttype() == AccountTypes.Basic){
                        user.getMybranches().clear();
                        Branch branch = session.find(Branch.class, msgarray[2]);
                        System.out.println(msgarray[2] + " #00" + branch.getName());
                        if(branch != null) {
                            user.getMybranches().add(branch);
                        }
                        branch.getUsers().add(user);
                    }
                    else if(((Client) userrr).getAccounttype() == AccountTypes.Premium || ((Client) userrr).getAccounttype() == AccountTypes.Gold){
                        System.out.println("nana");
                        user.getMybranches().clear();

                        CriteriaBuilder builder = session.getCriteriaBuilder();
                        CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
                        query.from(Branch.class);
                        user.setMybranches(session.createQuery(query).getResultList());


                        for(Branch branch : session.createQuery(query).getResultList()){

                            branch.getUsers().add(user);
                            System.out.println(branch.getName());
                        }
                    }
                }else{
                    if(user.getPermission() == permissions.WORKER){
                        user.getMybranches().clear();
                        Branch branch = session.find(Branch.class, msgarray[2]);
                        user.getMybranches().add(branch);

                    }
                }
                //System.out.println(msgarray[2] + " #00" );
                user.setPermission(userrr.getPermission());
                session.flush();
                session.getTransaction().commit();
                //System.out.println("nana");
                client.sendToClient(new Message(user, "#UserUpdated"));
            }
        }
//			else {
//				client.sendToClient(new Message(null, "#UserNotFound"));
//			}


        if (request.startsWith("#AddToCart")) {
            String[] msgarray = request.split(" ");
            List<Cart> myorders = session.find(Client.class, msgarray[1]).getMyorders();
            boolean added = false;
            if (myorders.size() == 0) {
                Cart cartt = new Cart(session.find(Client.class, msgarray[1]));
                cartt.getItems().add((Item) ms.getObject());
                App.server.saveObject(cartt);
                session.find(Item.class, ((Item) ms.getObject()).getId()).getCarts().add(cartt);
                added = true;
            } else {
                for (Cart cart : myorders) {
                    if (cart.isPayed() == false) {
                        session.beginTransaction();
                        cart.getItems().add((Item) ms.getObject());
                        session.find(Item.class, ((Item) ms.getObject()).getId()).getCarts().add(cart);
                        session.getTransaction().commit();
                        added = true;
                        break;
                    }
                }
            }
            if (added == false) {

                Cart cartt = new Cart(session.find(Client.class, msgarray[1]));
                cartt.getItems().add((Item) ms.getObject());
                App.server.saveObject(cartt);
                session.find(Item.class, ((Item) ms.getObject()).getId()).getCarts().add(cartt);

            }
            client.sendToClient(new Message(null, "#AddedItem"));
        }
        if (request.startsWith("#DeleteItem")) {

            Item item = session.find(Item.class, ((Item) ms.getObject()).getId());

            if (item != null) {

                session.beginTransaction();
                session.delete(item);
                session.flush();
                session.getTransaction().commit();
                client.sendToClient(new Message(null, "#DeleteItemSucceeded"));
            } else {
                client.sendToClient(new Message(null, "#DeleteItemFailed"));
            }
        }

        if (request.startsWith("#UpdateItemInfo")) {
            String[] msgarray = request.split(",");
            Item item = session.find(Item.class, ((Item) ms.getObject()).getId());
            if (item == null) {
                client.sendToClient(new Message(null, "#UpdateInfoFailed"));
            } else {
                session.beginTransaction();
                item.setinfo(msgarray[1]);
                session.flush();
                session.getTransaction().commit();

                client.sendToClient(new Message(session.find(Item.class, ((Item) ms.getObject()).getId()), "#UpdateInfoSucceeded"));
            }

        }
        if (request.equals("#LoadCatalog")) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Item> query = builder.createQuery(Item.class);
            query.from(Item.class);
            Message msa = new Message(session.createQuery(query).getResultList(), "#CatalogReady");
            client.sendToClient(msa);

        }
        if (request.equals("#getBranchesS")) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
            query.from(Branch.class);
            Message msa = new Message(session.createQuery(query).getResultList(), "#BranchesReadyS");
            client.sendToClient(msa);
        }
        if (request.equals("#getBranchesA")) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
            query.from(Branch.class);
            Message msa = new Message(session.createQuery(query).getResultList(), "#BranchesReadyA");
            client.sendToClient(msa);
            System.out.println("2");
        }
        if (request.equals("#getBranchesC")) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
            query.from(Branch.class);
            Message msa = new Message(session.createQuery(query).getResultList(), "#BranchesReadyC");
            client.sendToClient(msa);
        }
        if (request.equals("#getBranchesR")) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
            query.from(Branch.class);
            Message msa = new Message(session.createQuery(query).getResultList(), "#BranchesReadyR");
            client.sendToClient(msa);
        }

        /////////////////
//        if (request.startsWith("#RemoveUser")) {
//            String[] msgarray = request.split(" ");
//            User user = session.find(User.class, msgarray[1]);
//            if (user != null) {
//                session.delete(user);
//                client.sendToClient(new Message(user, "#UserRemoved"));
//            } else {
//                client.sendToClient(new Message(user, "#RemoveUserNotFound"));
//            }
//        }
        if(request.startsWith("#CancelOrder")){
            String[] msgarray = request.split(" ");

            Client client1=session.find(Client.class,msgarray[1]);
            Cart ncart=session.find(Cart.class,Integer.parseInt((msgarray[2])));
            if(client1==null||ncart==null)
            {
                client.sendToClient(new Message(null,"#CancelOrderFailed"));
                return;
            }
            double refund=ncart.getPrice()*Integer.parseInt((msgarray[3]))/100;

            session.beginTransaction();
                client1.setAmount(client1.getAmount()+refund);
                ncart.setCanceled(true);
                session.flush();
                session.getTransaction().commit();
            client.sendToClient(new Message(null,"#CanceledOrder"));
            return;


        }

        else if (request.startsWith("#PrepReports1")) {

            client.sendToClient(new Message(null,"#ReportsReady"));
            String[] msgarray = request.split(" ");
            if (msgarray[1].equals("Order")) {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
                query.from(Cart.class);
                List<Cart> carts=session.createQuery(query).getResultList();
                List<Cart> cartsSend=new ArrayList<>();
                if(!msgarray[2].equals("All")) {
                    for (Cart cart : carts) {
                        if (cart.getClient().getMybranches().get(0).getName().equals(msgarray[2])) {
                            cartsSend.add(cart);
                        }
                    }
                }
                else {
                    for (Cart cart : carts) {
                        cartsSend.add(cart);
                    }
                }
                IncomeReport rep=new IncomeReport();


            } else if (msgarray[1].equals("Income")) {

            } else if (msgarray[1].equals("Complain")) {

            }

        } else if (request.startsWith("#PrepReports2")) {
            String[] msgarray = request.split(" ");
            if (msgarray[1].equals("Order")) {

            } else if (msgarray[1].equals("Income")) {

            } else if (msgarray[1].equals("Complain")) {

            }
        }
    }

    public void makeitwork() {
        User johnny = session.find(User.class, "johnny");
        User Lili = session.find(User.class, "lili");
        User Shaggy = session.find(User.class, "shaggy");

        Branch Haifa = session.find(Branch.class, "haifa");
        Branch Nazareth = session.find(Branch.class, "nazareth");
        Branch Krayot = session.find(Branch.class, "krayot");

        BranchManager Moshe = session.find(BranchManager.class, "moshe");
        BranchManager ProfMalki = session.find(BranchManager.class, "profmalki");
        BranchManager eli = session.find(BranchManager.class, "eli");

        CoroporationManager MsSneh = session.find(CoroporationManager.class, "sneh");

        Client lana31 = session.find(Client.class, "lana31");
        Client Ramkh = session.find(Client.class, "ramkh");
        Client MuradKh = session.find(Client.class, "muradkh");

        Transaction tx = session.beginTransaction();

        johnny.getMybranches().add(Haifa);
        Haifa.getUsers().add(johnny);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Lili.getMybranches().add(Krayot);
        Krayot.getUsers().add(Lili);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Shaggy.getMybranches().add(Nazareth);
        Nazareth.getUsers().add(Shaggy);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Moshe.setMybranch(Krayot);
        Krayot.setBmanager(Moshe);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        ProfMalki.setMybranch(Haifa);
        Haifa.setBmanager(ProfMalki);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        eli.setMybranch(Nazareth);
        Nazareth.setBmanager(eli);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        MsSneh.AddOneBranch(Haifa);
        MsSneh.AddOneBranch(Nazareth);
        MsSneh.AddOneBranch(Krayot);
        Haifa.setCmanager(MsSneh);
        Nazareth.setCmanager(MsSneh);
        Krayot.setCmanager(MsSneh);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Ramkh.AddOneBranch(Haifa);
        Ramkh.AddOneBranch(Nazareth);
        Ramkh.AddOneBranch(Krayot);
        Haifa.getUsers().add(Ramkh);
        Nazareth.getUsers().add(Ramkh);
        Krayot.getUsers().add(Ramkh);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        lana31.AddOneBranch(Haifa);
        lana31.AddOneBranch(Nazareth);
        lana31.AddOneBranch(Krayot);
        Haifa.getUsers().add(lana31);
        Nazareth.getUsers().add(lana31);
        Krayot.getUsers().add(lana31);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        MuradKh.AddOneBranch(Krayot);
        Krayot.getUsers().add(MuradKh);
        session.flush();
        tx.commit();


    }

    private Boolean BetweenDates(Date date, Date date1, Date date2) {
        if (date.before(date1) || date.after(date2))
            return false;
        return true;
    }
}
