package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.server.SimpleServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CartItem implements Initializable {

    private Item ITEM;

    @FXML
    private Label Discount;

    @FXML
    private Label DiscountLB;

    @FXML
    private ImageView Image;

    @FXML
    private Label Name;

    public Spinner<Integer> getQuantity() {
        return Quantity;
    }

    public void setQuantity(Spinner<Integer> quantity) {
        Quantity = quantity;
    }

    @FXML
    private Label Price;

    @FXML
    private Spinner<Integer> Quantity;

    @FXML
    private Label Subtotal;


    @FXML
    void RemoveItem(MouseEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message(ITEM,"#RemoveFromCart "+App.getUser().getUsername()));
    }

    @FXML
    void ShowItem(MouseEvent event) throws IOException {
        App.setOnscreen(ITEM);
        App.setRoot("ItemShow");
    }

    public void setItemView(Item item,int i) {

        if (item == null)
            return;
        ITEM = item;
        Name.setText(item.getName());
        Price.setText("$" + item.getPrice());
        javafx.scene.image.Image image = new Image(SimpleClient.class.getResourceAsStream(item.getImagesrc()));
        Image.setImage(image);
        Subtotal.setText(Double.toString(item.getPrice()*i) + "$");
        Discount.setText(item.getDiscount()+"%");
        if(item.getDiscount()==0)
        {
            Discount.setVisible(false);
            DiscountLB.setVisible(false);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory<Integer>() {
            @Override
            public void decrement(int i) {
                if(this.getValue()>1) {

                    this.setValue(this.getValue() - 1);
                    final DecimalFormat df = new DecimalFormat("0.00");
                    Subtotal.setText(Double.toString(ITEM.getPrice() * this.getValue())+"$");
                    SimpleClient.getClient().cartControl.subTotalG-=ITEM.getPrice();
                    double temp=SimpleClient.getClient().cartControl.subTotalG;
                    SimpleClient.getClient().cartControl.Total.setText(df.format(temp)+"$");
                    SimpleClient.getClient().cartControl.subTotalD-=ITEM.getPrice()*(ITEM.getDiscount())/100;
                    temp=SimpleClient.getClient().cartControl.subTotalD;
                    SimpleClient.getClient().cartControl.Saved.setText(df.format(temp)+"$");
                    SimpleClient.getClient().cartControl.Tax.setText(df.format(((SimpleClient.getClient().cartControl.subTotalG-temp)/1.17)*0.17)+"$");
                    try {
                        SimpleClient.getClient().sendToServer(new Message(ITEM,"#RemoveOneFromCart "+App.getUser().getUsername()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void increment(int i) {
                if(this.getValue()<20) {
                    this.setValue(this.getValue() + 1);
                    final DecimalFormat df = new DecimalFormat("0.00");
                    Subtotal.setText(Double.toString(ITEM.getPrice() * this.getValue())+"$");

                    SimpleClient.getClient().cartControl.subTotalG+=ITEM.getPrice();
                    double temp=SimpleClient.getClient().cartControl.subTotalG;
                    SimpleClient.getClient().cartControl.Total.setText(df.format(temp)+"$");
                    SimpleClient.getClient().cartControl.subTotalD+=ITEM.getPrice()*(ITEM.getDiscount())/100;
                    temp=SimpleClient.getClient().cartControl.subTotalD;
                    SimpleClient.getClient().cartControl.Saved.setText(df.format(temp)+"$");
                    SimpleClient.getClient().cartControl.Tax.setText(df.format(((SimpleClient.getClient().cartControl.subTotalG-temp)/1.17)*0.17)+"$");
                    try {
                        SimpleClient.getClient().sendToServer(new Message(ITEM,"#AddToCart "+App.getUser().getUsername()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


        };
        valueFactory.setValue(1);
        Quantity.setValueFactory(valueFactory);
        Quantity.setPromptText("0");
    }
}
