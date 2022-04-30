package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
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
import java.util.Collections;
import java.util.List;

public class SimpleServer extends AbstractServer {

	private static Session session;
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Item.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}
	public List<Object> getList(String classn){
		CriteriaBuilder builder=session.getCriteriaBuilder();

		if(classn.equals("#getItems")){

			CriteriaQuery<Item> query=builder.createQuery(Item.class);
			query.from(Item.class);
			return Collections.singletonList(session.createQuery(query).getResultList());
		}
		return null;
	}
	public SimpleServer(int port) throws IOException {
		super(port);

			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			listen();

	}
	protected void saveObject(Object o)
	{
		session.beginTransaction();
		session.save(o);
		session.flush();
		session.getTransaction().commit();
	}
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();


		double price=Double.parseDouble(msgString);
	}

}
