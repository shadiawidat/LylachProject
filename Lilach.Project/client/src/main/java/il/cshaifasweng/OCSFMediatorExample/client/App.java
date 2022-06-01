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


    private void SetItemShowCon(Item item){

    }
    private static Scene scene;

    public static Item getOnscreen() {
        return onscreen;
    }

    public static void setOnscreen(Item onscreen1) {
        onscreen = onscreen1;
    }

    private static Item onscreen;

    public static List<Item> items=new ArrayList<>();

    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(Object msg) {
        App.items = (List<Item>)msg;
    }

    private SimpleClient client;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }

    private static User user;

    @Override
    public void start(Stage stage) throws IOException {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();

        client.openConnection();
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage.setTitle("LyLach");
        scene=new Scene(root, 920, 720);
        stage.setScene(scene);
        stage.show();
    }


    public static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }



    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        SimpleClient.getClient().sendToServer(new Message(null,"#SignOut "+App.getUser().getUsername()));
        exit(0);
        EventBus.getDefault().unregister(this);
        super.stop();

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

    public static void main(String[] args) {
        launch();
    }

}