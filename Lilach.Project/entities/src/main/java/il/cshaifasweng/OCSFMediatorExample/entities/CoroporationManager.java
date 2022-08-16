package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class CoroporationManager extends User implements Serializable {

    @OneToMany(mappedBy = "cmanager", cascade = CascadeType.REMOVE)
    private List<Branch> corpmybranches = new ArrayList<>();

    public CoroporationManager() {

    }

    public CoroporationManager(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, boolean Freeze) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, Freeze);

    }

    public void AddOneBranch(Branch b) {
        corpmybranches.add(b);
    }

    public void DeleteOneBranch(Branch b) {
        corpmybranches.remove(b);
    }

    public List<Branch> getCorpmybranches() {
        return corpmybranches;
    }

    public void setCorpmybranches(List<Branch> corpmybranches) {
        this.corpmybranches = corpmybranches;
    }
}
