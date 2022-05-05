package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class CoroporationManager extends User implements Serializable {
    private List<Branch> mybranchs;

    public List<Branch> getMybranchs() {
        return mybranchs;
    }

    public void setMybranchs(List<Branch> mybranchs) {
        this.mybranchs = mybranchs;
    }
}
