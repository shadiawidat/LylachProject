package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class Connect implements Initializable {

    @FXML
    private CheckBox Server;

    @FXML
    private CheckBox Client;

    @FXML
    private TextField IP;

    @FXML
    private TextField Port;

    @FXML
    void RunIt(MouseEvent event) throws Exception {
        App.setClient(SimpleClient.getClient());
        App.getClient().setPort(Integer.parseInt(Port.getText()));
        App.getClient().setHost(IP.getText());
        try {
            App.getClient().openConnection();
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Connection Failed");
            a.showAndWait();
            return;

        }
        App.getTheStage().setWidth(920);
        App.getTheStage().setMinWidth(920);
        App.getTheStage().setMaxWidth(920);

        App.getTheStage().setMinHeight(750);
        App.getTheStage().setHeight(750);
        App.getTheStage().setMaxHeight(750);

        App.getTheStage().setX(200);
        App.getTheStage().setY(50);
        App.setRoot("LogIn");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            MenuItem item = new MenuItem();
            item.setText(socket.getLocalAddress().getHostAddress());
//            IP.getItems().add(item);

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
//        for(MenuItem menuItem: IP.getItems()){
//            menuItem.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent e) {
//                    IP.setText(menuItem.getText());
//                }
//            });
//        }
    }
}
