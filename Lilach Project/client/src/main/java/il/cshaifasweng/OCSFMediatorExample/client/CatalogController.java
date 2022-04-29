package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CatalogController implements Initializable {

	@FXML
	private GridPane grid;

	@FXML
	private ScrollPane ScrollPane;
	private List<Item> itemsg=new ArrayList<>();
	private List<Item> getData(){
		List<Item> items=new ArrayList<>();
		Item item1 = new Item("Orchid" , 87, "flower", "white","img/orchid.jpg");
		items.add(item1);
		Item item2 = new Item("Lily", 15, "flower", "white","img/orchid.jpg");
		items.add(item2);
		Item item3 = new Item("Tulip", 13, "flower", "orange","img/orchid.jpg");
		items.add(item3);
		Item item4 = new Item( "Rose", 7, "flower", "red","img/orchid.jpg");
		items.add(item4);
		Item item5 = new Item("Dahlia", 9, "flower", "pink","img/orchid.jpg");
		items.add(item5);
		Item item6 = new Item("Lavender", 11, "flower", "purple","img/orchid.jpg");
		items.add(item6);
		return items;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//		grid=new GridPane(sa);
		itemsg=getData();
		int column = 0;
        int row = 1;

		try {
			for (int i = 0; i < itemsg.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(CatalogController.class.getResource("itemview1.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemviewController itemController = fxmlLoader.getController();
				itemController.setData( itemsg.get(i));

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

