package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Controllers.Catalog;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {



	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	private static List<Item> recievedmsg=new ArrayList<>();

	public List<Item> getRecievedmsg() {
		return recievedmsg;
	}

	public void setRecievedmsg(List<Item> recievedmsg1) {
		recievedmsg.addAll(recievedmsg1);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {

		List<Item> items=(List<Item>)msg;
		Catalog.itemsg=items;
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}

	}



	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3010);
		}
		return client;
	}

}
