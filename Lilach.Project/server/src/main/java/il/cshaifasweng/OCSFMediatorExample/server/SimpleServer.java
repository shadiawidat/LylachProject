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
import java.util.Collection;
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

		if(request.startsWith("#identify"))
		{
			String[] msgarray=request.split(" ");
						client.sendToClient(new Message(session.find(User.class,msgarray[1]),"#Useridentified"));
		}
		if(request.equals("#LoadCatalog"))
		{
			CriteriaBuilder builder=session.getCriteriaBuilder();
			CriteriaQuery<Item> query=builder.createQuery(Item.class);
			query.from(Item.class);
			Message msa=new Message(session.createQuery(query).getResultList(),"#CatalogReady");
			client.sendToClient(msa);
		}
	}

}
