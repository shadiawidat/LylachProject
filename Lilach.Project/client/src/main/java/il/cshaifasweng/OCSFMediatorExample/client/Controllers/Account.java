package il.cshaifasweng.OCSFMediatorExample.client.Controllers;

import il.cshaifasweng.OCSFMediatorExample.entities.Client;
import javafx.fxml.FXML;

public class Account {


    public static Client client;



    public static Client getClient() {
        return client;
    }

    public static void setClient(Client cli) {
        client = cli;
    }
}
