package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartItem implements Initializable {

    private Item ITEM;

    @FXML
    private Label Discount;

    @FXML
    private ImageView Image;

    @FXML
    private Label Name;

    @FXML
    private Label Price;

    @FXML
    private Spinner<?> Quantity;

    @FXML
    private Label Subtotal;


    @FXML
    void RemoveItem(MouseEvent event) throws IOException {

    }

    @FXML
    void ShowItem(MouseEvent event) throws IOException {
        App.setOnscreen(ITEM);
        App.setRoot("ItemShow");
    }

    public void setItemView(Item item) {

        if (item == null)
            return;
        ITEM = item;
        Name.setText(item.getName());
        Price.setText("$" + item.getPrice());
        javafx.scene.image.Image image = new Image(SimpleClient.class.getResourceAsStream(item.getImagesrc()));
        Image.setImage(image);
        Subtotal.setText(Double.toString(item.getPrice()) + "$");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Quantity.setPromptText("0");
    }
}
