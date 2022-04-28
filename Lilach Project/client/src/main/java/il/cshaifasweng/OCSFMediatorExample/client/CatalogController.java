package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.server.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CatalogController implements Initializable {

	@FXML
	private GridPane Grid;

	@FXML
	private ScrollPane ScrollPane;
	private List<Item> items=new ArrayList<>();
	private List<Item> getData(){
		List<Item> items = new ArrayList<>();
		Item item;
		for(int i = 0;i<6;i++){
			item=new Item();
			item.setName("rose");
			item.setPrice(5.68);
			item.setColor("red");
			item.setType("flower");
			item.setImagesrc("img/rose.jpg");
		}
		return items;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		items.addAll(getData());
		int column = 0;
        int row = 1;

        for (int i = 0; i < items.size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/views/item"));
			//ItemController.setData(items.get(i));
		}
	}
}
