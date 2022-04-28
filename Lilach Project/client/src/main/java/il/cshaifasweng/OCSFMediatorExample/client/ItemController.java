package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import il.cshaifasweng.OCSFMediatorExample.server.Item;

import java.io.IOException;

public class ItemController {


    public Label getNameLabel() {
        return NameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        NameLabel = nameLabel;
    }

    public ImageView getPic() {
        return Pic;
    }

    public void setPic(ImageView pic) {
        Pic = pic;
    }

    public Label getPriceLabel() {
        return PriceLabel;
    }

    public void setPriceLabel(Label priceLabel) {
        PriceLabel = priceLabel;
    }

    public Button getViewInfo() {
        return ViewInfo;
    }

    public void setViewInfo(Button viewInfo) {
        ViewInfo = viewInfo;
    }

    @FXML
    private Label NameLabel;

    @FXML
    private ImageView Pic;

    @FXML
    private Label PriceLabel;

    @FXML
    private Button ViewInfo;
    private Item item;
    @FXML
    void ViewInfoButton(ActionEvent event) throws IOException {
        App.setRoot("itemview");
    }
    public void setData(Item item){
        this.item=item;
        NameLabel.setText(item.getName());
        PriceLabel.setText("$" + String.valueOf(item.getPrice()));
        Image image=new Image(getClass().getResourceAsStream(item.getImagesrc()));
        Pic.setImage(image);
    }



}
