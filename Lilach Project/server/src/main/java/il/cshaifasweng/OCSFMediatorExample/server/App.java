package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

/**
 * Hello world!
 *
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class App {
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

    private static void generateItems() throws Exception {
        Random random = new Random();
        Item item1 = new Item("Orchid" , 87, "flower", "white","img/orchid.jpg");
        session.save(item1);
        Item item2 = new Item("Lily", 15, "flower", "white","img/orchid.jpg");
        session.save(item2);
        Item item3 = new Item("Tulip", 13, "flower", "orange","img/orchid.jpg");
        session.save(item3);
        Item item4 = new Item( "Rose", 7, "flower", "red","img/orchid.jpg");
        session.save(item4);
        Item item5 = new Item("Dahlia", 9, "flower", "pink","img/orchid.jpg");
        session.save(item5);
        Item item6 = new Item("Lavender", 11, "flower", "purple","img/orchid.jpg");
        session.save(item6);
 /*
 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.
 */
            session.flush();
        }



    private static SimpleServer server;

    public static void main(String[] args) throws IOException {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            generateItems();
            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            server = new SimpleServer(3000);
            server.listen();

        }
    }
}
