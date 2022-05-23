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
import java.io.IOException;
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
		configuration.addAnnotatedClass(Catalog.class);
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
	public List<Object> getList(String classn){
		CriteriaBuilder builder=session.getCriteriaBuilder();
		if(classn.equals("#getItems")){
			CriteriaQuery<Object> query=builder.createQuery(Object.class);
			query.from(Item.class);
			return session.createQuery(query).getResultList();
		}
		return null;
	}

//	public Object getItem(String classn, int id){
//		CriteriaBuilder builder=session.getCriteriaBuilder();
//
//
//	}

	public SimpleServer(int port) throws IOException {
		super(port);

			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			listen();

	}

	protected Object findObject(Object object){
		session.beginTransaction();
		if(session.find(object.getClass(), object) == null){
			return null;
		}

		session.getTransaction().commit();
		return object;
	}

	protected void saveObject(Object o)
	{
		session.beginTransaction();

		session.save(o);
		session.flush();

		session.getTransaction().commit();

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
		String msgString = msg.toString();

		//if(msg.toString().startsWith())
		if(msg.toString().equals("#initCatalog"))
        {
			client.sendToClient("#prepItems");
			client.sendToClient(getList("#getItems"));
			client.sendToClient("#recviveItems");
        }

		if(msg.toString().equals("#addItemToCart")){
			client.sendToClient("#prepItemToAddToCart");
			//client.sendToClient(getItem("#getItem", ));
			client.sendToClient("#itemAddedToCart");
		}

		if(msg.toString().equals("#deleteItemFromCart")){
			client.sendToClient("#prepItemToDelete");

			client.sendToClient("#ItemDeleted");
			client.sendToClient("#ItemDoesn'tExist");

		}
		if(msg.toString().equals("#addItemToCatalog")){
			client.sendToClient("#prepItemToAddToCatalog");

			client.sendToClient("#ItemAddedToCatalog");

		}
		if(msg.toString().equals("#deleteItemFromCatalog")){
			client.sendToClient("#prepItemToDeleteFromCatalog");

			client.sendToClient("#ItemDeletedFromCatalog");
			client.sendToClient("#ItemDoesn'tExist");
		}

		if(msg.toString().equals("#addClient")){
			client.sendToClient("#ItemAdded");
		}
		if(msg.toString().equals("#deleteClient")){}
		if(msg.toString().equals("#addWorker")){}
		if(msg.toString().equals("#deleteWorker")){}
		if(msg.toString().equals("#addReport")){}
		if(msg.toString().equals("#showReportInfo")){}
		if(msg.toString().equals("#addComplain")){}
		if(msg.toString().equals("#deleteComplain")){}

		if(msg.toString().equals("#addCart")){}
		if(msg.toString().equals("#delteCart")){}
		if(msg.toString().equals("#addBranch")){}
		if(msg.toString().equals("#deleteBranch")){}













		if(msg.toString().startsWith("#changePrice"))
		{
			String[] msgarray=msgString.split(" ");
			setInfo(Integer.parseInt(msgarray[1]),Double.parseDouble(msgarray[2]));
		}
		double price=Double.parseDouble(msgString);
	}

}
