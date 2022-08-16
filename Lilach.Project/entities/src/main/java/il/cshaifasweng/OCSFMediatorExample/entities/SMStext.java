package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SMStext")
public class SMStext implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private boolean SMSread = false;

    @ManyToOne
    @JoinColumn(name = "Client_ID")
    private Client client;

    public SMStext(String text) {
        this.text = text;
        this.SMSread = false;
    }

    public SMStext() {
    }

    public boolean isSMSread() {
        return SMSread;
    }

    public void setSMSread(boolean SMSread) {
        this.SMSread = SMSread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
