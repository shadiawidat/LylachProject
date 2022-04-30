package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ItemShowController {

    @FXML
    private ImageView CartBtn;

    @FXML
    private Button ChangeBtn;

    @FXML
    private AnchorPane ItemHolder;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private TextField PriceFiled;

    @FXML
    private Button SearchBtn;

    @FXML
    private TextField SerachField;

    @FXML
    private Label UserName;

    @FXML
    private static Label color;

    @FXML
    private static ImageView imgid;

    @FXML
    private static Label nameid;

    @FXML
    private ImageView pressX;

    @FXML
    private static Label price;

    @FXML
    private static Label type;

    public static void SetItem(Item item) {
        if(item==null)
            return;
        color.setText(item.getColor());
        price.setText("$"+item.getPrice());
        nameid.setText(item.getName());
        type.setText(item.getType());
           imgid.setImage(new Image(il.cshaifasweng.OCSFMediatorExample.client.App.class.getResourceAsStream(item.getImagesrc())));
    }

    @FXML
    void ChangePrice(MouseEvent event) {

    }

    @FXML
    void CloseItem(MouseEvent event) throws IOException {
        App.setRoot("catalog");
    }

    @FXML
    void GoToAccount(MouseEvent event) {

    }

    @FXML
    void GoToCart(MouseEvent event) throws IOException {
        App.setRoot("Cart");
    }

    @FXML
    void OpenMenu(MouseEvent event) {

    }

    @FXML
    void SearchItem(MouseEvent event) {

    }

}
