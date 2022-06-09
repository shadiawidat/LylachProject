package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import javafx.application.Platform;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SimpleServer extends AbstractServer {

    private static Session session;
    public boolean isRunning = false;
    public Thread timerthread = new Thread(this::handleThread);

    public SimpleServer(int port) throws IOException {
        super(port);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        listen();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.

        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Report.class);
        configuration.addAnnotatedClass(SMStext.class);
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

    public void handleThread() {
        int i = 0;

        while (isRunning) {

            Platform.runLater(() -> {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Client> query = builder.createQuery(Client.class);
                query.from(Client.class);
                List<Client> clients = session.createQuery(query).getResultList();

                for (Client client : clients) {
                    for (Cart cart : client.getMyorders()) {
                        if (cart.isSMSED())
                            continue;
                        if (cart.isForSomeOne() == false)
                            continue;
                        LocalDateTime d1 = LocalDateTime.now();
                        Date d = new Date(d1.getYear() - 1900 + 1, d1.getMonth().getValue() - 1, d1.getDayOfMonth(), d1.getHour(), d1.getMinute());

                        if (d.after(cart.getDeliverydate())) {
                            session.beginTransaction();
                            cart.setSMSED(true);
                            SMStext sms = new SMStext("Order number :" + cart.getId() + " was delivered");
                            client.getMySMS().add(sms);
                            sms.setClient(client);
                            session.save(sms);
                            session.flush();
                            session.getTransaction().commit();
                        } else
                            continue;

                    }
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException {
        Message ms = (Message) msg;
        String request = ms.getString();
        System.out.println(request);
        if (request.startsWith("#RemoveUser")) {
            String[] msgarray = request.split(" ");
            User user = session.find(User.class, msgarray[1]);

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
        if(request.equals("#LoadMS"))
        {
            Client client1=session.find(Client.class,((Client)ms.getObject()).getUsername());
            client.sendToClient(new Message(client1.getMySMS(),"#SMSLOADED"));

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
        if (request.startsWith("#AddItem")) {
            saveObject(ms.getObject());
            client.sendToClient(new Message(null, "#AddNewItem"));
        }
        if (request.startsWith("#LogIn")) {
            String[] msgarray = request.split(" ");
            session.beginTransaction();
            User user = session.find(User.class, msgarray[1]);

            if (user != null && user.getPassword().equals(msgarray[2])) {
                if (user.getPermission() == permissions.CLIENT) {
                    if (((Client) user).getMemberShipt().plusYears(1).isBefore(LocalDateTime.now())) {
                        ((Client) user).setAccounttype(AccountTypes.Gold);
                    }


                }

                client.sendToClient(new Message(user, "#Useridentify"));
                if (user.isFreeze() == false)
                    session.find(User.class, msgarray[1]).setLogedIn(true);
                session.flush();
                session.getTransaction().commit();


            } else {
                session.getTransaction().commit();
                client.sendToClient(new Message(null, "#Useridentify"));

            }

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
            if (user != null) {
                client.sendToClient(new Message(user, "#AddUserExists"));
            } else {

                App.server.saveObject(ms.getObject());
                User use = session.find(User.class, msgarray[1]);
                Branch b1 = session.find(Branch.class, msgarray[2]);

                if (b1 != null) {


                    session.beginTransaction();
                    b1.getUsers().add(use);
                    use.getMybranches().add(b1);
                    session.flush();
                    session.getTransaction().commit();

                } else {

                    b1 = new Branch(msgarray[2]);

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

            String[] msgarray = request.split("±");
            session.beginTransaction();

            List<Cart> myorders = session.find(Client.class, msgarray[1]).getMyorders();

            for (Cart cart : myorders) {

                if (cart.isPayed() == false) {

                    cart.setPayed(true);
                    cart.setAddress(msgarray[2]);
                    cart.setSomeOne(msgarray[3]);
                    cart.setSomeOnePhone(msgarray[4]);
                    cart.setBlessingticket(msgarray[5]);

                    cart.setDelivery(msgarray[6].equals("true"));

                    cart.setForSomeOne(!msgarray[3].equals(""));


                    cart.setPrice(Double.parseDouble(msgarray[10]));

                    cart.setHour(Integer.parseInt(msgarray[11]));
                    cart.setMinute(Integer.parseInt(msgarray[12]));
                    if(msgarray[13].equals("true"))
                    {
                        System.out.println("hello");
                        cart.setPaymentMethod(PaymentMethod.CASH);
                    }
                    else {
                        cart.setPaymentMethod(PaymentMethod.CREDIT);
                        Client client1=session.find(Client.class, msgarray[1]);
                        client1.setAmount(client1.getAmount()-Double.parseDouble(msgarray[10]));

                    }

                    //cart.
                    int year = Integer.parseInt(msgarray[7]);
                    int month = Integer.parseInt(msgarray[8]);
                    int day = Integer.parseInt(msgarray[9]);
                    Date d = new Date(Integer.parseInt(msgarray[7]) - 1900, Integer.parseInt(msgarray[8]) - 1, Integer.parseInt(msgarray[9]) + 1);
                    Date date1 = (Date) ms.getObject();

                    cart.setHour(Integer.parseInt(msgarray[11]));
                    cart.setMinute(Integer.parseInt(msgarray[12]));
                    d.setHours(Integer.parseInt(msgarray[11]));
                    d.setMinutes(Integer.parseInt(msgarray[12]));

                    cart.setDeliverydate(d);
                    cart.setDate(LocalDateTime.now());
                    session.flush();
                    session.getTransaction().commit();
                    client.sendToClient(new Message(null, "#ShippmentApproved"));
                    return;
                }
            }
        }
        if (request.startsWith("#RemoveOneFromCart")) {
            String[] msgarray = request.split(" ");
            Client nclient = session.find(Client.class, msgarray[1]);

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
            complain.setBranch(nbranch);
            complain.setDate((Date) (ms.getObject()));
            complain.setHandled(false);
            complain.setHour(LocalDateTime.now().getHour());
            complain.setMinute(LocalDateTime.now().getMinute());

            System.out.println(LocalDateTime.now().getHour());
            System.out.println(LocalDateTime.now().getMinute());
            mycomplains.add(complain);

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
            List<Complain> complainList = new ArrayList<>();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = builder.createQuery(Client.class);
            query.from(Client.class);
            List<Client> clients = session.createQuery(query).getResultList();

            for (Client c : clients) {
                for (Complain complain : c.getComplains()) {
                    complainList.add(complain);
                }
            }


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
                        if(branch != null) {
                            user.getMybranches().add(branch);
                        }
                        branch.getUsers().add(user);
                    }
                    else if(((Client) userrr).getAccounttype() == AccountTypes.Premium || ((Client) userrr).getAccounttype() == AccountTypes.Gold){

                        user.getMybranches().clear();

                        CriteriaBuilder builder = session.getCriteriaBuilder();
                        CriteriaQuery<Branch> query = builder.createQuery(Branch.class);
                        query.from(Branch.class);
                        user.setMybranches(session.createQuery(query).getResultList());


                        for (Branch branch : session.createQuery(query).getResultList()) {

                            branch.getUsers().add(user);

                        }
                    }
                }else{
                    if(user.getPermission() == permissions.WORKER){
                        user.getMybranches().clear();
                        Branch branch = session.find(Branch.class, msgarray[2]);
                        user.getMybranches().add(branch);

                    }
                }
                user.setPermission(userrr.getPermission());
                session.flush();
                session.getTransaction().commit();
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
        if (request.startsWith("#CancelOrder")) {
            String[] msgarray = request.split(" ");

            Client client1 = session.find(Client.class, msgarray[1]);
            Cart ncart = session.find(Cart.class, Integer.parseInt((msgarray[2])));
            if (client1 == null || ncart == null) {
                client.sendToClient(new Message(null, "#CancelOrderFailed"));
                return;
            }
            double refund = ncart.getPrice() * Integer.parseInt((msgarray[3])) / 100;

            session.beginTransaction();
            client1.setAmount(client1.getAmount() + refund);
            ncart.setCanceled(true);
            session.flush();
            session.getTransaction().commit();
            client.sendToClient(new Message(null, "#CanceledOrder"));
            return;


        } else if (request.startsWith("#PrepReports1")) {

            client.sendToClient(new Message(null, "#ReportsReady"));
            String[] msgarray = request.split(" ");
            if (msgarray[1].equals("Order")) {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
                query.from(Cart.class);
                List<Cart> carts = session.createQuery(query).getResultList();
                List<Cart> cartsSend = new ArrayList<>();
                if (!msgarray[2].equals("All")) {
                    for (Cart cart : carts) {
                        if (cart.getClient().getMybranches().get(0).getName().equals(msgarray[2])) {
                            cartsSend.add(cart);
                        }
                    }
                } else {
                    for (Cart cart : carts) {
                        cartsSend.add(cart);
                    }
                }
                IncomeReport rep = new IncomeReport();


            } else if (msgarray[1].equals("Income")) {

            } else if (msgarray[1].equals("Complain")) {

            }

        } else if (request.startsWith("#PrepReports2")) {
            String[] msgarray = request.split(" ");
            if (msgarray[1].equals("Order")) {

            } else if (msgarray[1].equals("Income")) {

            } else if (msgarray[1].equals("Complain")) {

            }
        } else if (request.startsWith("#Refund")) {
            String[] msgarray = request.split("±");
            Client c = session.find(Client.class, ((Client) ms.getObject()).getUsername());
            session.beginTransaction();
            c.setAmount(c.getAmount() + Double.parseDouble(msgarray[1]));
            session.flush();
            session.getTransaction().commit();
            Complain complain = session.find(Complain.class, Integer.parseInt(msgarray[2]));
            session.beginTransaction();
            complain.setHandled(true);
            session.flush();
            session.getTransaction().commit();

        }
    }

    public void makeitwork() {

        User johnny = session.find(User.class, "johnny");
        User Lili = session.find(User.class, "lili");
        User Shaggy = session.find(User.class, "shaggy");
        User Alber = session.find(User.class, "alber");
        User Adam = session.find(User.class, "adam");
        User Sandy = session.find(User.class, "sandy");
        User Sama = session.find(User.class, "sama");
        User Ben = session.find(User.class, "ben");
        User George = session.find(User.class, "george");
        User Liel = session.find(User.class, "liel");


        Branch Haifa = session.find(Branch.class, "haifa");
        Branch Nazareth = session.find(Branch.class, "nazareth");
        Branch Krayot = session.find(Branch.class, "krayot");
        Branch Telaviv = session.find(Branch.class, "telaviv");
        Branch Natanya = session.find(Branch.class, "natanya");
        Branch Karmiel = session.find(Branch.class, "karmiel");
        Branch Herzilya = session.find(Branch.class, "herzilya");
        Branch Hadera = session.find(Branch.class, "hadera");
        Branch Shefaraam = session.find(Branch.class, "shefaraam");
        Branch Nahariya = session.find(Branch.class, "nahariya");


        BranchManager Moshe = session.find(BranchManager.class, "moshe");
        BranchManager ProfMalki = session.find(BranchManager.class, "profmalki");
        BranchManager eli = session.find(BranchManager.class, "eli");
        BranchManager maya = session.find(BranchManager.class, "maya");
        BranchManager alex = session.find(BranchManager.class, "alex");
        BranchManager leen = session.find(BranchManager.class, "leen");
        BranchManager polina = session.find(BranchManager.class, "polina");
        BranchManager avi = session.find(BranchManager.class, "avi");
        BranchManager yacov = session.find(BranchManager.class, "yacov");
        BranchManager itay = session.find(BranchManager.class, "itay");


        CoroporationManager MsSneh = session.find(CoroporationManager.class, "sneh");

        Client lana31 = session.find(Client.class, "lana31");
        Client Ramkh = session.find(Client.class, "ramkh");
        Client MuradKh = session.find(Client.class, "muradkh");
        Client Lara = session.find(Client.class, "lara");
        Client Leam = session.find(Client.class, "leam");
        Client Tom = session.find(Client.class, "tom");
        Client Noel = session.find(Client.class, "noel");
        Client Wass = session.find(Client.class, "wass");
        Client Daniel = session.find(Client.class, "daniel");
        Client Marian = session.find(Client.class, "marian");

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
        Sama.getMybranches().add(Hadera);
        Hadera.getUsers().add(Sama);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Sandy.getMybranches().add(Nahariya);
        Nahariya.getUsers().add(Sandy);
        session.flush();
        tx.commit();


        tx = session.beginTransaction();
        Adam.getMybranches().add(Telaviv);
        Telaviv.getUsers().add(Adam);
        session.flush();
        tx.commit();


        tx = session.beginTransaction();
        Ben.getMybranches().add(Natanya);
        Natanya.getUsers().add(Ben);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        George.getMybranches().add(Karmiel);
        Karmiel.getUsers().add(George);
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
        maya.setMybranch(Karmiel);
        Karmiel.setBmanager(maya);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        alex.setMybranch(Herzilya);
        Herzilya.setBmanager(alex);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        avi.setMybranch(Shefaraam);
        Shefaraam.setBmanager(avi);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        yacov.setMybranch(Nahariya);
        Nahariya.setBmanager(yacov);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        polina.setMybranch(Natanya);
        Natanya.setBmanager(polina);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        itay.setMybranch(Telaviv);
        Telaviv.setBmanager(itay);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        leen.setMybranch(Hadera);
        Hadera.setBmanager(leen);
        session.flush();
        tx.commit();


        tx = session.beginTransaction();
        MsSneh.AddOneBranch(Haifa);
        MsSneh.AddOneBranch(Nazareth);
        MsSneh.AddOneBranch(Krayot);
        MsSneh.AddOneBranch(Karmiel);
        MsSneh.AddOneBranch(Hadera);
        MsSneh.AddOneBranch(Nahariya);
        MsSneh.AddOneBranch(Natanya);
        MsSneh.AddOneBranch(Herzilya);
        MsSneh.AddOneBranch(Telaviv);
        MsSneh.AddOneBranch(Shefaraam);
        Haifa.setCmanager(MsSneh);
        Nazareth.setCmanager(MsSneh);
        Krayot.setCmanager(MsSneh);
        Karmiel.setCmanager(MsSneh);
        Hadera.setCmanager(MsSneh);
        Nahariya.setCmanager(MsSneh);
        Natanya.setCmanager(MsSneh);
        Herzilya.setCmanager(MsSneh);
        Telaviv.setCmanager(MsSneh);
        Shefaraam.setCmanager(MsSneh);


        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Ramkh.AddOneBranch(Haifa);
        Ramkh.AddOneBranch(Nazareth);
        Ramkh.AddOneBranch(Krayot);
        Ramkh.AddOneBranch(Karmiel);
        Ramkh.AddOneBranch(Nahariya);
        Ramkh.AddOneBranch(Natanya);
        Ramkh.AddOneBranch(Telaviv);
        Ramkh.AddOneBranch(Shefaraam);
        Ramkh.AddOneBranch(Hadera);
        Ramkh.AddOneBranch(Herzilya);
        Haifa.getUsers().add(Ramkh);
        Nazareth.getUsers().add(Ramkh);
        Krayot.getUsers().add(Ramkh);
        Karmiel.getUsers().add(Ramkh);
        Nahariya.getUsers().add(Ramkh);
        Natanya.getUsers().add(Ramkh);
        Telaviv.getUsers().add(Ramkh);
        Shefaraam.getUsers().add(Ramkh);
        Hadera.getUsers().add(Ramkh);
        Herzilya.getUsers().add(Ramkh);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        lana31.AddOneBranch(Haifa);
        lana31.AddOneBranch(Nazareth);
        lana31.AddOneBranch(Krayot);
        lana31.AddOneBranch(Karmiel);
        lana31.AddOneBranch(Nahariya);
        lana31.AddOneBranch(Natanya);
        lana31.AddOneBranch(Telaviv);
        lana31.AddOneBranch(Shefaraam);
        lana31.AddOneBranch(Hadera);
        lana31.AddOneBranch(Herzilya);
        Haifa.getUsers().add(lana31);
        Nazareth.getUsers().add(lana31);
        Krayot.getUsers().add(lana31);
        Karmiel.getUsers().add(lana31);
        Nahariya.getUsers().add(lana31);
        Natanya.getUsers().add(lana31);
        Telaviv.getUsers().add(lana31);
        Shefaraam.getUsers().add(lana31);
        Hadera.getUsers().add(lana31);
        Herzilya.getUsers().add(lana31);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Leam.AddOneBranch(Haifa);
        Leam.AddOneBranch(Nazareth);
        Leam.AddOneBranch(Krayot);
        Leam.AddOneBranch(Karmiel);
        Leam.AddOneBranch(Nahariya);
        Leam.AddOneBranch(Natanya);
        Leam.AddOneBranch(Telaviv);
        Leam.AddOneBranch(Shefaraam);
        Leam.AddOneBranch(Hadera);
        Leam.AddOneBranch(Herzilya);
        Haifa.getUsers().add(Leam);
        Nazareth.getUsers().add(Leam);
        Krayot.getUsers().add(Leam);
        Karmiel.getUsers().add(Leam);
        Nahariya.getUsers().add(Leam);
        Natanya.getUsers().add(Leam);
        Telaviv.getUsers().add(Leam);
        Shefaraam.getUsers().add(Leam);
        Hadera.getUsers().add(Leam);
        Herzilya.getUsers().add(Leam);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Wass.AddOneBranch(Haifa);
        Wass.AddOneBranch(Nazareth);
        Wass.AddOneBranch(Krayot);
        Wass.AddOneBranch(Karmiel);
        Wass.AddOneBranch(Nahariya);
        Wass.AddOneBranch(Natanya);
        Wass.AddOneBranch(Telaviv);
        Wass.AddOneBranch(Shefaraam);
        Wass.AddOneBranch(Hadera);
        Wass.AddOneBranch(Herzilya);
        Haifa.getUsers().add(Wass);
        Nazareth.getUsers().add(Wass);
        Krayot.getUsers().add(Wass);
        Karmiel.getUsers().add(Wass);
        Nahariya.getUsers().add(Wass);
        Natanya.getUsers().add(Wass);
        Telaviv.getUsers().add(Wass);
        Shefaraam.getUsers().add(Wass);
        Hadera.getUsers().add(Wass);
        Herzilya.getUsers().add(Wass);
        session.flush();
        tx.commit();


        tx = session.beginTransaction();
        MuradKh.AddOneBranch(Krayot);
        Krayot.getUsers().add(MuradKh);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Lara.AddOneBranch(Nahariya);
        Nahariya.getUsers().add(Lara);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Noel.AddOneBranch(Herzilya);
        Herzilya.getUsers().add(Noel);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Daniel.AddOneBranch(Telaviv);
        Telaviv.getUsers().add(Daniel);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Marian.AddOneBranch(Karmiel);
        Karmiel.getUsers().add(Marian);
        session.flush();
        tx.commit();

        tx = session.beginTransaction();
        Tom.AddOneBranch(Natanya);
        Natanya.getUsers().add(Tom);
        session.flush();
        tx.commit();


    }

    private Boolean BetweenDates(Date date, Date date1, Date date2) {
        return !date.before(date1) && !date.after(date2);
    }
}
