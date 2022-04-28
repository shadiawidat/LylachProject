package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ItemviewController {

    @FXML
    private TextField ChangePriceTextField;

    @FXML
    private Label ColorLabel;

    @FXML
    private Button EscLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private ImageView PhotoLabel;

    @FXML
    private Label PriceLabel;

    @FXML
    private Button Send;

    @FXML
    private Label TypeLabel;

    @FXML
    void Back_to_Catalog(ActionEvent event) throws IOException {
        App.setRoot("item");
    }

    @FXML
    void SendButton(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(getChangePriceTextField());
    }

    public TextField getChangePriceTextField() {
        return ChangePriceTextField;
    }

    public void setChangePriceTextField(TextField changePriceTextField) {
        ChangePriceTextField = changePriceTextField;
    }

    public Label getColorLabel() {
        return ColorLabel;
    }

    public void setColorLabel(Label colorLabel) {
        ColorLabel = colorLabel;
    }

    public Button getEscLabel() {
        return EscLabel;
    }

    public void setEscLabel(Button escLabel) {
        EscLabel = escLabel;
    }

    public Label getNameLabel() {
        return NameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        NameLabel = nameLabel;
    }

    public ImageView getPhotoLabel() {
        return PhotoLabel;
    }

    public void setPhotoLabel(ImageView photoLabel) {
        PhotoLabel = photoLabel;
    }

    public Label getPriceLabel() {
        return PriceLabel;
    }

    public void setPriceLabel(Label priceLabel) {
        PriceLabel = priceLabel;
    }

    public Button getSend() {
        return Send;
    }

    public void setSend(Button send) {
        Send = send;
    }

    public Label getTypeLabel() {
        return TypeLabel;
    }

    public void setTypeLabel(Label typeLabel) {
        TypeLabel = typeLabel;
    }
}
