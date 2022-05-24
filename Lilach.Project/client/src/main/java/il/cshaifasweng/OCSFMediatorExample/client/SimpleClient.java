package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Controllers.Catalog;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	public static String lastms=new String("");

	public static String getLastms() {
		return lastms;
	}

	public static void setLastms(String lastms) {
		SimpleClient.lastms = lastms;
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Message ms=(Message)msg;
		String deliver=ms.getString();
		if(deliver.equals("#CatalogReady")) {
			Catalog.Catalog = (List<Item>) ms.getObject();
		}
		else if (deliver.equals("#Useridentified"))
		{
			App.setUser((User) ms.getObject());

		}
		else if (deliver.equals("#Usernotidentified"))
		{
			App.setUser(null);
		}
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}

	}



	public static SimpleClient getClient() {
		if (client == null) {

			client = new SimpleClient("localhost", 3030);

		}
		return client;
	}

}
