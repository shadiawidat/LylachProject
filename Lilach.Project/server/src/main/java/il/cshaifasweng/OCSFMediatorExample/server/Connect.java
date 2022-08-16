package il.cshaifasweng.OCSFMediatorExample.server;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.*;
import java.util.ResourceBundle;

public class Connect implements Initializable {

    @FXML
    private TextField IP;

    @FXML
    private TextField Port;

    @FXML
    private Button RunBtn;

    @FXML
    private Button StopBtn;

    @FXML
    void RunIt(MouseEvent event) throws Exception {

        try {
            App.server = new SimpleServer(Integer.parseInt(Port.getText()));
            App.server.isRunning = true;
            App.server.timerthread.start();
            App.generateItems();
            App.server.listen();
            if (App.server.isListening()) {
                RunBtn.setVisible(false);
                StopBtn.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();


            Alert a = new Alert(Alert.AlertType.ERROR);

            a.setContentText(e.getMessage());
            a.showAndWait();

        }
    }

    @FXML
    void StopIt(MouseEvent event) throws Exception {
        RunBtn.setVisible(true);
        StopBtn.setVisible(false);
        App.server.close();
        App.server.stopListening();
        App.server = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            IP.setText(socket.getLocalAddress().getHostAddress());
            IP.setOpacity(Double.MAX_VALUE);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
