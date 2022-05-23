package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.MyListener;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ItemView {

    private Item item;
    @FXML
    private ImageView AddCart;

    @FXML
    private ImageView imageid;

    @FXML
    public Label nameid;

    @FXML
    private Label price;

    @FXML
    private void click(MouseEvent mouseEvent)
    {
        myListener.onClickListener(item);
    }

    private MyListener myListener;
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
        if(item==null)
            return;
        this.item=item;
        nameid.setText(item.getName());
        price.setText("$"+item.getPrice());
        Image image=new Image(SimpleClient.class.getResourceAsStream(item.getImagesrc()));
        imageid.setImage(image);

    }
}
