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
	public Shipping shippingControl;

	public Complain complainControl;
	public ItemShow itemshowControl;
	public LogIn logControl;
	public Report reportControl;
	public ReportHistogram reportHistogramControl;
	public ReportView reportViewControl;
	public SignUp signUpControl;
	public ItemView itemviewControl;
	public MyCarts myCartsControl;
	public CartView cartView;
	public Complain complain;
	public AllComplains allComplains;
	public clientComplain clientComplainControl;

	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
		}
		else if(deliver.equals("#ShippmentApproved"))
		{
			Platform.runLater(()->{
				try {
					shippingControl.Approval();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if (deliver.equals("#BranchesReadyA"))
		{
			accountControl.setBranchesL((List<Branch>) ms.getObject());
			Platform.runLater(()->{
				accountControl.loadBranches();
				accountControl.resetFields();
				});

		}else if (deliver.equals("#ComplainsReady"))
		{

			Platform.runLater(()->{
				allComplains.loadComplains((List<il.cshaifasweng.OCSFMediatorExample.entities.Complain>)ms.getObject());
			});

		}
		else if (deliver.equals("#OrdersReady"))
		{

			Platform.runLater(()->{
				myCartsControl.loadOrders((List<il.cshaifasweng.OCSFMediatorExample.entities.Cart>)ms.getObject());
			});

		}else if (deliver.equals("#BranchesReadyC"))
		{
			clientComplainControl.setBranchesL((List<Branch>) ms.getObject());
			Platform.runLater(()->{clientComplainControl.loadBranches();});

		}else if (deliver.equals("#BranchesReadyS"))
		{
			signUpControl.setBranchesL((List<Branch>) ms.getObject());
			Platform.runLater(()->{signUpControl.loadBranches();});

		}else if (deliver.equals("#BranchesReadyR"))
		{
			reportControl.setBranchesL((List<Branch>) ms.getObject());
			Platform.runLater(()->{
				reportControl.loadBranches1();
				reportControl.loadBranches2();
			});

		} else if (deliver.equals("#UserFound"))
		{
			accountControl.setUser((User) ms.getObject());
			System.out.println("asmo");
			System.out.println(((User) ms.getObject()).getUsername());
			Platform.runLater(()->{accountControl.fillInfo((User) ms.getObject());});

		}else if (deliver.equals("#UserNotFound"))
		{
			accountControl.setUser(null);
			Platform.runLater(()->{accountControl.fillInfo(null);});
		}else if (deliver.equals("#UserFreezed"))
		{
			accountControl.setUser((User) ms.getObject());
			Platform.runLater(()->{accountControl.fillInfo((User) ms.getObject());});

		}
		else if (deliver.equals("#UserRemoved"))
		{
			Platform.runLater(()->{
				accountControl.fillInfo(App.getUser());
				accountControl.ShowNote("User Removed Successfully!");
			});

		}
		else if(deliver.equals("#CartReady"))
		{
			Platform.runLater(()->{cartControl.loadCart((List<Item>) ms.getObject());});
		}
		else if(deliver.equals("#CartUserNotFound"))
		{
			Platform.runLater(()->{cartControl.loadCart((List<Item>) ms.getObject());});
		}
		else if (deliver.equals("#RemoveUserNotFound"))
		{
			accountControl.ShowNote("The user you select to remove doesn't exist!");
		}
		else if (deliver.equals("#RemovedOne"))
		{

		}
		else if (deliver.equals("#UserUpdated"))
		{
			accountControl.setUser((User) ms.getObject());
			Platform.runLater(()->{

				accountControl.fillInfo((User) ms.getObject());
				accountControl.ShowNote("User Updated Successfully!");
			});
		}
		else if(deliver.equals("#UpdateInfoFailed")){
			Platform.runLater(()->{
				try {
					itemshowControl.Created(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		}
		else if(deliver.equals("#UpdateInfoSucceeded")){

			Platform.runLater(()->{
				try {
					itemshowControl.Created(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if(deliver.equals("#DeleteItemSucceeded")){

			Platform.runLater(()->{
				try {
					itemshowControl.Deleted(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if(deliver.equals("#DeleteItemFailed")){

			Platform.runLater(()->{
				try {
					itemshowControl.Deleted(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if(deliver.equals("#ReportsReady"))
		{
			while(SimpleClient.getClient().reportViewControl==null)
			{

			}
			Platform.runLater(()->SimpleClient.getClient().reportViewControl.loadReports(null,null));
		}
		else if(deliver.equals("#CanceledOrder")){

			Platform.runLater(()->{
				try {
					cartView.Deleted(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if(deliver.equals("#AddNewItem"))
		{
			Platform.runLater(()->{
				try {
					itemshowControl.additem();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		}
		else if(deliver.equals("#AddUserExist")){
			Platform.runLater(()->accountControl.UserAlreadyExist());
		}
		else if(deliver.equals("#AddUserCreated")){
			Platform.runLater(()->{
				accountControl.ShowNote("User Added Successfully!");

			});
		}
		else if(deliver.equals("#CancelOrderFailed")){

			Platform.runLater(()->{
				try {
					cartView.Deleted(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}

		else if(deliver.equals("#ComplainSent")){

			Platform.runLater(()->{
				clientComplain.showNote();
			});
		}

		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}


	}

	public static SimpleClient getClient() {
		if (client == null) {


			client = new SimpleClient("localhost", 3121);
		}
		return client;
	}

}
