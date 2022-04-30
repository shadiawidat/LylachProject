package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ItemviewController {

    private Item item=new Item("Lavender", 11, "flower", "purple", "/img/orchid_transparent.png");
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
    void AddToCart(MouseEvent event) {

    }

    @FXML
    void ShowItem(MouseEvent event) throws IOException {
        ItemShowController.SetItem(item);
        App.setRoot("ItemShow");
    }

    public void setItem(Item item) {
        if(item==null)
            return;
        this.item=item;
        nameid.setText(item.getName());
        price.setText("$"+item.getPrice());

//        imageid.setImage(new Image(il.cshaifasweng.OCSFMediatorExample.client.App.class.getResourceAsStream(item.getImagesrc())));

    }
}
