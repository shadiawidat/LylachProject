package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class App extends Application {


    public static List<Item> items = new ArrayList<>();
    private static Scene scene;
    private static Item onscreen;
    private static SimpleClient client;
    private static User user;
    private static Stage theStage;
    private Thread fxThread;

    public static Item getOnscreen() {
        return onscreen;
    }

    public static void setOnscreen(Item onscreen1) {
        onscreen = onscreen1;
    }

    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(Object msg) {
        App.items = (List<Item>) msg;
    }

    public static SimpleClient getClient() {
        return client;
    }

    public static void setClient(SimpleClient client) {
        App.client = client;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }

    public static Stage getTheStage() {
        return theStage;
    }

    public static void setTheStage(Stage theStage) {
        App.theStage = theStage;
    }

    public static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws InterruptedException {
        launch();
    }

    private void SetItemShowCon(Item item) {

    }

    @Override
    public void start(Stage stage) throws IOException {

        System.out.println(getClass().getResource(""));
        Parent root = FXMLLoader.load(getClass().getResource("Connect.fxml"));
        stage.setTitle("LyLach");
        scene = new Scene(root, 350, 150);
        stage.setScene(scene);
        stage.show();
        setTheStage(stage);

    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        if (App.getUser() != null)
            SimpleClient.getClient().sendToServer(new Message(null, "#SignOut " + App.getUser().getUsername()));
        Platform.runLater(() -> {
            exit(0);
            EventBus.getDefault().unregister(this);
            try {
                super.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    @Subscribe
    public void onWarningEvent(WarningEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    String.format("Message: %s\nTimestamp: %s\n",
                            event.getWarning().getMessage(),
                            event.getWarning().getTime().toString())
            );
            alert.show();
        });

    }

}