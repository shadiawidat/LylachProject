package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.MyListener;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.application.Platform;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.sun.javafx.application.PlatformImpl.runLater;

public class Catalog implements Initializable {

		@FXML
		private ResourceBundle resources;

		@FXML
		private URL location;

		@FXML
		private ImageView CartBtn;

		@FXML
		private ScrollPane ScrollPane;

		@FXML
		private Button SearchBtn;

		@FXML
		public TextField SearchField;

		@FXML
		private Label UserName;

		@FXML
		private GridPane grid;

		private MyListener myListener;

		@FXML
		void ClickSearch(MouseEvent event) throws IOException {
			List<Item> searchitem=new ArrayList<>();
			for(int i=0;i<itemsg.size();i++)
			{
				String name=itemsg.get(i).getName().toLowerCase();
				if(name.startsWith(SearchField.getText().toLowerCase()))
				{
					searchitem.add(itemsg.get(i));
				}
			}
			SearchField.setText("");
			grid.getChildren().clear();

			int column = 0;
			int row = 1;
			try {
				for (int i = 0; i < searchitem.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("ItemView.fxml"));
					AnchorPane anchorPane = fxmlLoader.load();
					ItemView itemController = fxmlLoader.getController();
					itemController.setItemView(searchitem.get(i));
					if (column == 3) {
						column = 0;
						row++;
					}
					grid.add(anchorPane, column++, row); //(child,column,row)
					//set grid width
					grid.setMinWidth(Region.USE_COMPUTED_SIZE);
					grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
					grid.setMaxWidth(Region.USE_PREF_SIZE);
					//set grid height
					grid.setMinHeight(Region.USE_COMPUTED_SIZE);
					grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
					grid.setMaxHeight(Region.USE_PREF_SIZE);
					GridPane.setMargin(anchorPane, new Insets(10));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}


		}

		@FXML
		void MenuClick(MouseEvent event) {

		}
		@FXML
		void GoToAccount(MouseEvent event) throws IOException {
			App.setRoot("Account");
		}

		@FXML
		void GoToCart(MouseEvent event) throws IOException {
			App.setRoot("Cart");
		}

		public static List<Item> itemsg=new ArrayList<>();
		public void setData() throws IOException {

		}
		private void getData() throws IOException {

			Message ms=new Message(null,"#LoadCatalog");
           SimpleClient.getClient().sendToServer(ms);



		}

		private void setCatalog(List<Item> items)
		{

		}

		@FXML
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			runLater(()-> {
				try {
					getData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			int column = 0;
			int row = 1;

			try {
				getData();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		//last
		while(SimpleClient.getLastms().equals("#CatalogReady")==false) {
		}

				try {
					for (int i = 0; i < itemsg.size(); i++) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(SimpleClient.class.getResource("ItemView.fxml"));
						AnchorPane anchorPane = fxmlLoader.load();
						ItemView itemController = fxmlLoader.getController();

						itemController.setItemView(itemsg.get(i));
						if (column == 3) {
							column = 0;
							row++;
						}
						grid.add(anchorPane, column++, row); //(child,column,row)
						//set grid width
						grid.setMinWidth(Region.USE_COMPUTED_SIZE);
						grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
						grid.setMaxWidth(Region.USE_PREF_SIZE);
						//set grid height
						grid.setMinHeight(Region.USE_COMPUTED_SIZE);
						grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
						grid.setMaxHeight(Region.USE_PREF_SIZE);
						GridPane.setMargin(anchorPane, new Insets(10));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}



		}

	}