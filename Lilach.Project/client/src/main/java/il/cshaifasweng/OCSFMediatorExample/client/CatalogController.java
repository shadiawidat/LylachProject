package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
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

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


	public class CatalogController implements Initializable {

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
		private TextField SearchField;

		@FXML
		private Label UserName;

		@FXML
		private GridPane grid;

		private MyListener myListener;
		@FXML
		void CartClick(MouseEvent event) throws IOException {
			App.setRoot("Cart");
		}

		@FXML
		void ClickSearch(MouseEvent event) throws IOException {
			App.setRoot("Cart");
		}

		@FXML
		void MenuClick(MouseEvent event) {

		}
		@FXML
		void GoToAccount(MouseEvent event) throws IOException {
			App.setRoot("Account");
		}


		private List<Item> itemsg=new ArrayList<>();
		public void setData() throws IOException {
			SimpleClient.getClient().sendToServer("#getItems");

			itemsg.addAll(SimpleClient.getClient().getRecievedmsg());
			System.out.println(itemsg.size());
		}
		private List<Item> getData() throws IOException {
//            ));

//			items= (List<Item>) SimpleClient.getRecievedmsg();
//			Item item;
//			 item = new Item("Orchid" , 87, "flower", "white", "/img/");
//			items.add(item);
//			 item = new Item("Tulip", 15, "flower", "white", "/img/");
//			items.add(item);
//			 item = new Item("Lavender", 13, "flower", "orange", "/img/");
//			items.add(item);
//			 item = new Item( "Lily", 7, "flower", "red","/img/");
//			items.add(item);
//			 item = new Item("Orchid", 9, "flower", "pink", "/img/");
//			items.add(item);
//			item = new Item( "Lily", 7, "flower", "red","/img/");
//			items.add(item);
//			item = new Item("Orchid", 9, "flower", "pink", "/img/");
//			items.add(item);
//			item = new Item( "Lily", 7, "flower", "red","/img/");
//			items.add(item);
//			item = new Item("Orchid", 9, "flower", "pink", "/img/");
//			items.add(item);
//			item = new Item( "Lily", 7, "flower", "red","/img/");
//			items.add(item);
//			item = new Item("Orchid", 9, "flower", "pink", "/img/");
//			items.add(item);

			return itemsg;
		}
		@FXML
		@Override
		public void initialize(URL location, ResourceBundle resources) {
//			assert CartBtn != null : "fx:id=\"CartBtn\" was not injected: check your FXML file 'catalog.fxml'.";
//			assert ScrollPane != null : "fx:id=\"ScrollPane\" was not injected: check your FXML file 'catalog.fxml'.";
//			assert SearchBtn != null : "fx:id=\"SearchBtn\" was not injected: check your FXML file 'catalog.fxml'.";
//			assert SerachField != null : "fx:id=\"SerachField\" was not injected: check your FXML file 'catalog.fxml'.";
//			assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'catalog.fxml'.";
//			assert grid != null : "fx:id=\"grid\" was not injected: check your FXML file 'catalog.fxml'.";


			try {
				setData();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println();
			int column = 0;
			int row = 1;

			try {
				for (int i = 0; i < itemsg.size(); i++) {

					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("itemview.fxml"));

					AnchorPane anchorPane = fxmlLoader.load();

					ItemviewController itemController = fxmlLoader.getController();

					itemController.setItemView(itemsg.get(i));

					if (column == 3) {
						column = 0;
						row++;
					}

					grid.add(anchorPane, column++, row); //(child,column,row)
					//set grid width
					grid.setMinWidth(anchorPane.getMinWidth());
					grid.setPrefWidth(anchorPane.getPrefWidth());
					grid.setMaxWidth(anchorPane.getMaxWidth());

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