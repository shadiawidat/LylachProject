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
		private TextField SerachField;

		@FXML
		private Label UserName;

		@FXML
		private static GridPane grid=new GridPane();

		private MyListener myListener;
		@FXML
		void CartClick(MouseEvent event) throws IOException {
			App.setRoot("Cart");
		}

		@FXML
		void ClickSearch(MouseEvent event) {

		}

		@FXML
		void MenuClick(MouseEvent event) {

		}
		@FXML
		void GoToAccount(MouseEvent event) throws IOException {
			App.setRoot("Account");
		}


		private List<Item> itemsg=new ArrayList<>();
		public void setData(List<Item> it)
		{
			itemsg=it;
		}
		private List<Item> getData(){
			List<Item> items=new ArrayList<>();
			Item item;
			 item = new Item("Orchid" , 87, "flower", "white", "/img/orchid_transparent.png");
			items.add(item);
			 item = new Item("Lily", 15, "flower", "white", "/img/orchid_transparent.png");
			items.add(item);
			 item = new Item("Tulip", 13, "flower", "orange", "/img/orchid_transparent.png");
			items.add(item);
			 item = new Item( "Rose", 7, "flower", "red","/img/orchid_transparent.png");
			items.add(item);
			 item = new Item("Dahlia", 9, "flower", "pink", "/img/orchid_transparent.png");
			items.add(item);
			 item = new Item("Lavender", 11, "flower", "purple", "/img/orchid_transparent.png");
			items.add(item);

			return items;
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


			itemsg=getData();
			int column = 0;
			int row = 1;

			try {
				for (int i = 0; i < itemsg.size(); i++) {

					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(App.class.getResource("itemview.fxml"));

					AnchorPane anchorPane = fxmlLoader.load();

					ItemviewController itemController = fxmlLoader.getController();

					itemController.setItem( itemsg.get(i));

				
					if (column == 3) {
						column = 0;
						row++;
					}

					grid.add(anchorPane, column++, row); //(child,column,row)
//					//set grid width
//					grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//					grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//					grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//					//set grid height
//					grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//					grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//					grid.setMaxHeight(Region.USE_PREF_SIZE);
					GridPane.setMargin(anchorPane, new Insets(10));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}