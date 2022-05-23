package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;

public class Message implements Serializable {

    private Object object;
    private String string;

    public Message(Object object, String string) {
        this.object = object;
        this.string = string;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
