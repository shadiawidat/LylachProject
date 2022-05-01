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


		public static List<Item> itemsg=new ArrayList<>();
		public void setData() throws IOException {

			itemsg.addAll(SimpleClient.getClient().getRecievedmsg());

		}
		private void getData() throws IOException {
           SimpleClient.getClient().sendToServer("#getItems");

		}
		@FXML
		@Override
		public void initialize(URL location, ResourceBundle resources) {

			try {
				getData();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			int column = 0;
			int row = 1;
			while(itemsg.size()<10) {
				try {
					setData();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				if(itemsg.size()==7) {
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
					break;
				}
			}

		}

	}