package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ItemController {

    private Item item;

    @FXML
    private ImageView Cart;

    @FXML
    private Button ChangePrice;

    @FXML
    private ImageView MenuBtn;

    @FXML
    private TextField PriceFiled;

    @FXML
    private Button SerachBtn;

    @FXML
    private TextField SerachField;

    @FXML
    private Label color;

    @FXML
    private ImageView imgid;

    @FXML
    private Label nameid;

    @FXML
    private ImageView pressX;

    @FXML
    private Label price;

    @FXML
    private Label type;

    @FXML
    void ChangePrice(MouseEvent event) {

    }

    @FXML
    void CloseItem(MouseEvent event) throws IOException {
        App.setRoot("catalog1");
    }



    @FXML
    void SearchItem(MouseEvent event) {

    }


    public void GoToCart(MouseEvent mouseEvent) {
    }
}

//public Label getNameLabel() {
//        return NameLabel;
//        }
//
//public void setNameLabel(Label nameLabel) {
//        NameLabel = nameLabel;
//        }
//
//public ImageView getPic() {
//        return Pic;
//        }
//
//public void setPic(ImageView pic) {
//        Pic = pic;
//        }
//
//public Label getPriceLabel() {
//        return PriceLabel;
//        }
//
//public void setPriceLabel(Label priceLabel) {
//        PriceLabel = priceLabel;
//        }
//
//public Button getViewInfo() {
//        return ViewInfo;
//        }
//
//public void setViewInfo(Button viewInfo) {
//        ViewInfo = viewInfo;
//        }
