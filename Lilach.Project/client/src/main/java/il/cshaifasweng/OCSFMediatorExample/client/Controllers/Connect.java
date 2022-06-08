package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.client.App;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Connect {

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
        if(Server.isSelected()) {
            String[] temp = new String[1];
            temp[0] = Port.getText();
            il.cshaifasweng.OCSFMediatorExample.server.App.main(temp);
        }
        if(Client.isSelected())
        {
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
    }

}
