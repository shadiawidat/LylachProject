package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
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
	public Collection<Object> getList(String classn){


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
	protected Object findObject(String classn,String id){
		session.beginTransaction();

		if(classn.equals("Item"))
		{
			return session.find(Item.class,id);
		}

		session.getTransaction().commit();
		return null;
	}

	protected void saveObject(Object o)
	{
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
	protected void saveObjectList(List<Object> list)
	{
		session.beginTransaction();
		for(int i =0; i < list.size();i++){
		session.save(list.get(i));
		session.flush();
		}

		session.getTransaction().commit();
	}

	protected void deleteObject(Object o)
	{
		session.beginTransaction();

		session.delete(o);
		session.flush();

		session.getTransaction().commit();
	}

	protected void deleteObjectList(List<Object> list)
	{
		session.beginTransaction();

		for(int i =0; i < list.size();i++){
			session.delete(list.get(i));
		}
		session.flush();

		session.getTransaction().commit();
	}
	protected void setInfo(int itemId,double price)
	{
		session.beginTransaction();
		session.find(Item.class,itemId).setPrice(price);
		session.getTransaction().commit();
	}
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException {
		Message ms=(Message) msg;
		String request=ms.getString();
		System.out.println(request);
		if(request.startsWith("#SignOut"))
		{
			session.beginTransaction();
			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);

			if(user!=null) {

				session.find(User.class,msgarray[1]).setLogedIn(false);
				session.flush();
				session.getTransaction().commit();
				client.sendToClient(new Message(null, "#UserSignOut"));

			}
		}
		if(request.startsWith("#LogIn"))
		{
			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);
			if(user!=null&&user.getPassword().equals(msgarray[2])) {


				client.sendToClient(new Message(user, "#Useridentify"));
				session.find(User.class,msgarray[1]).setLogedIn(true);
				session.flush();
			}
			else
				client.sendToClient(new Message(null,"#Useridentify"));

		}
		if(request.startsWith("#UserExist"))
		{
			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);
			if(user!=null) {
				client.sendToClient(new Message(user, "#UserExists"));
			}
			else {
				saveObject((User) ms.getObject());
				client.sendToClient(new Message(ms.getObject(), "#UserCreated"));
			}

		}
		if(request.startsWith("#AddUser"))
		{

			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);
			if(user!=null) {
				client.sendToClient(new Message(user, "#AddUserExists"));
			}
			else {

				saveObject((User) ms.getObject());
				client.sendToClient(new Message(ms.getObject(), "#AddUserCreated"));
			}
		}
		if(request.startsWith("#SearchUser"))
		{

			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);

			if(user!=null) {
				client.sendToClient(new Message(user, "#UserFound"));
			}
			else {
				client.sendToClient(new Message(null, "#UserNotFound"));
			}
		}
		if(request.startsWith("#FreezeUser"))
		{
			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);
			if(user!=null) {
				user.setFreeze(!user.isFreeze());
				client.sendToClient(new Message(user, "#UserFreezed"));
			}
		}
		if(request.startsWith("#RemoveUser"))
		{
			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);
			if(user!=null) {
				session.delete(user);
				client.sendToClient(new Message(user, "#UserRemoved"));
			}
			else
			{
				client.sendToClient(new Message(user, "#RemoveUserNotFound"));
			}
		}
		if(request.startsWith("#GetCart"))
		{
			String[] msgarray=request.split(" ");
			Client nclient=session.find(Client.class,msgarray[1]);
			if(nclient!=null) {
				for(Cart cart:nclient.getMyorders())
				{
					if(cart.isPayed()==false) {
						client.sendToClient(new Message(cart.getItems(), "#CartReady"));
						return;
					}
				}
				client.sendToClient(new Message(new ArrayList<>(), "#CartReady"));
				return;
			}
			else
			{
				client.sendToClient(new Message(null, "#CartUserNotFound"));
			}
		}
		if(request.startsWith("#RemoveFromCart"))
		{

			String[] msgarray=request.split(" ");
			Client nclient=session.find(Client.class,msgarray[1]);
			System.out.println(nclient.getFirstname());
			for(Cart cart:nclient.getMyorders())
			{
				if(cart.isPayed()==false) {

					int size=cart.getItems().size();

					for(int i=0;i<cart.getItems().size();i++)
					{
						if(cart.getItems().get(i).getId()==((Item)ms.getObject()).getId())
						{
							cart.getItems().remove(i);
							i--;

						}
					}
					client.sendToClient(new Message(cart.getItems(), "#CartReady"));

					return;
				}
			}

		}
		if(request.startsWith("#RemoveFromCartOne"))
		{
			String[] msgarray=request.split(" ");
			Client nclient=session.find(Client.class,msgarray[1]);
			System.out.println(nclient.getFirstname());
			for(Cart cart:nclient.getMyorders())
			{
				if(cart.isPayed()==false) {
					for(int i=0;i<cart.getItems().size();i++)
					{
						if(cart.getItems().get(i).getId()==((Item)ms.getObject()).getId())
						{
							cart.getItems().remove(i);
							break;
						}
					}

					client.sendToClient(new Message(cart.getItems(), "#RemovedOne"));
					return;
				}
			}

		}
		if(request.startsWith("#UpdateUser"))
		{
			session.beginTransaction();
			String[] msgarray=request.split(" ");
			User user=session.find(User.class,msgarray[1]);
			User userrr = (User) ms.getObject();
			if(user!=null) {
				user.setFirstname(userrr.getFirstname());
				user.setLastname(userrr.getLastname());
				user.setID(userrr.getID());
				user.setAddress(userrr.getAddress());
				user.setPhonenumber(userrr.getPhonenumber());
				user.setEmail(userrr.getEmail());
				if(user.getPermission() == permissions.CLIENT){
					((Client) user).setCreditCard(((Client)userrr).getCreditCard());
					((Client) user).setAccounttype(((Client)userrr).getAccounttype());
				}
				session.flush();
				session.getTransaction().commit();
				client.sendToClient(new Message(user, "#UserUpdated"));
			}
//			else {
//				client.sendToClient(new Message(null, "#UserNotFound"));
//			}

		}

		if(request.startsWith("#AddToCart"))
		{
			String[] msgarray=request.split(" ");
			List<Cart> myorders=session.find(Client.class,msgarray[1]).getMyorders();
			boolean added=false;
			if(myorders.size()==0)
			{
				Cart cartt=new Cart(session.find(Client.class,msgarray[1]));
				cartt.getItems().add((Item)ms.getObject());
				App.server.saveObject(cartt);
				session.find(Item.class,((Item)ms.getObject()).getId()).getCarts().add(cartt);
				added=true;
			}
			else{
				for(Cart cart:myorders)
				{


					if(cart.isPayed()==false)
					{

						session.beginTransaction();
						cart.getItems().add((Item)ms.getObject());
						session.find(Item.class,((Item)ms.getObject()).getId()).getCarts().add(cart);
						session.getTransaction().commit();
						added=true;
						break;
					}
				}
			}
			if(added==false){

				Cart cartt=new Cart(session.find(Client.class,msgarray[1]));
				cartt.getItems().add((Item)ms.getObject());
				App.server.saveObject(cartt);
				session.find(Item.class,((Item)ms.getObject()).getId()).getCarts().add(cartt);

			}
			client.sendToClient(new Message(null,"#AddedItem"));
		}
		if(request.equals("#LoadCatalog"))
		{
			CriteriaBuilder builder=session.getCriteriaBuilder();
			CriteriaQuery<Item> query=builder.createQuery(Item.class);
			query.from(Item.class);
			Message msa=new Message(session.createQuery(query).getResultList(),"#CatalogReady");
			client.sendToClient(msa);

		}
		if(request.equals("#getBranchesS"))
		{
			CriteriaBuilder builder=session.getCriteriaBuilder();
			CriteriaQuery<Branch> query=builder.createQuery(Branch.class);
			query.from(Branch.class);
			Message msa=new Message(session.createQuery(query).getResultList(),"#BranchesReadyS");
			client.sendToClient(msa);
		}
		if(request.equals("#getBranchesA"))
		{
			CriteriaBuilder builder=session.getCriteriaBuilder();
			CriteriaQuery<Branch> query=builder.createQuery(Branch.class);
			query.from(Branch.class);
			Message msa=new Message(session.createQuery(query).getResultList(),"#BranchesReadyA");
			client.sendToClient(msa);
		}
		if(request.equals("#getBranchesR"))
		{
			CriteriaBuilder builder=session.getCriteriaBuilder();
			CriteriaQuery<Branch> query=builder.createQuery(Branch.class);
			query.from(Branch.class);
			Message msa=new Message(session.createQuery(query).getResultList(),"#BranchesReadyR");
			client.sendToClient(msa);
		}else if(request.startsWith("#PrepReports1")){



			String[] msgarray=request.split(" ");
			if(msgarray[1].equals("Order")){

			}else if(msgarray[1].equals("Income")) {

			}else if(msgarray[1].equals("Complain")){

			}

		}else if(request.startsWith("#PrepReports2")){

		}
	}

	private Boolean BetweenDates(Date date,Date date1, Date date2){
		if(date.before(date1)||date.after(date2))
			return false;
		return true;
	}
}
