package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Controllers.*;
import il.cshaifasweng.OCSFMediatorExample.client.Controllers.Cart;
import il.cshaifasweng.OCSFMediatorExample.client.Controllers.Catalog;
import il.cshaifasweng.OCSFMediatorExample.client.Controllers.Complain;
import il.cshaifasweng.OCSFMediatorExample.client.Controllers.Report;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
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
		System.out.println(deliver);
		if(deliver.equals("#CatalogReady")) {
			Catalog.Catalog = (List<Item>) ms.getObject();
			Platform.runLater(()->{catalogControl.LoadList(Catalog.Catalog);});
		}
		else if (deliver.equals("#Useridentify"))
		{
			App.setUser((User) ms.getObject());
			Platform.runLater(()->{logControl.Sign();});
		}else if (deliver.equals("#UserExists"))
		{
			Platform.runLater(()->{signUpControl.UserExist();});
		}else if (deliver.equals("#UserCreated"))
		{
			Platform.runLater(()->{
				try {
					signUpControl.NewUser((User) ms.getObject());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}else if (deliver.equals("#BranchesReady"))
		{
			signUpControl.setBranchesL((List<Branch>) ms.getObject());
			Platform.runLater(()->{signUpControl.loadBranches();});
		}else if (deliver.equals("#UserFound"))
		{
			accountControl.setUser((User) ms.getObject());
			Platform.runLater(()->{accountControl.fillInfo((User) ms.getObject());});

		}else if (deliver.equals("#UserNotFound"))
		{
			accountControl.setUser(null);
			Platform.runLater(()->{accountControl.fillInfo(null);});
		}
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}


	}

	public static SimpleClient getClient() {
		if (client == null) {



			client = new SimpleClient("localhost", 3100);
		}
		return client;
	}

}
