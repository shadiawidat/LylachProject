package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.MyListener;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class ItemView {

    @FXML
    public Label nameid;
    private Item item;
    @FXML
    private ImageView AddCart;
    @FXML
    private ImageView imageid;
    @FXML
    private ImageView SaleImage;
    @FXML
    private Label price;
    private MyListener myListener;

    public ImageView getSaleImage() {
        return SaleImage;
    }

    public void setSaleImage(ImageView saleImage) {
        SaleImage = saleImage;
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(item);
    }

    @FXML
    void AddToCart(MouseEvent event) throws IOException {
        App.items.add(item);
//        SimpleClient.getClient().sendToServer("#AddtoCart "+item.getId()+" "+SimpleClient.getClient().getUsername());
//        System.out.println("AddtoCart "+item.getId()+" "+SimpleClient.getClient().getUsername());
        TilePane r = new TilePane();

        // create a alert
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.CONFIRMATION);

        a.setContentText("Item was added to Cart.");

        a.showAndWait();

    }

    @FXML
    void ShowItem(MouseEvent event) throws Exception {
        App.setOnscreen(item);
        App.setRoot("ItemShow");
    }

    public void setItemView(Item item) throws IOException {
        if (item == null)
            return;
        this.item = item;
        nameid.setText(item.getName());
        price.setText("$" + item.getPrice());
        Image image = new Image(SimpleClient.class.getResourceAsStream(item.getImagesrc()));
        imageid.setImage(image);

    }
}
