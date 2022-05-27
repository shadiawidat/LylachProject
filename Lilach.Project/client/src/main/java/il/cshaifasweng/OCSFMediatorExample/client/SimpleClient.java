package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Controllers.*;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {

	public About aboutControl;
	public Account accountControl;
	public Cart cartControl;
	public CartItem cartitemControl;
	public Catalog catalogControl;
	public Complain complainControl;
	public ItemShow itemshowControl;
	public LogIn logControl;
	public Report reportControl;
	public ReportHistogram reportHistogramControl;
	public ReportView reportViewControl;
	public SignUp	signUpControl;



	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Message ms=(Message)msg;
		String deliver=ms.getString();
		if(deliver.equals("#CatalogReady")) {
			Catalog.Catalog = (List<Item>) ms.getObject();
			Platform.runLater(()->{catalogControl.LoadList(Catalog.Catalog);});
		}
		else if (deliver.equals("#Useridentify"))
		{
			App.setUser((User) ms.getObject());
			Platform.runLater(()->{logControl.Sign();});
		}
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}

	}



	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3330);
		}
		return client;
	}

}
