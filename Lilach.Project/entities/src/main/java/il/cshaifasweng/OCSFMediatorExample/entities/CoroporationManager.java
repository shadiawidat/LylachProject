package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity

public class CoroporationManager extends User implements Serializable{

    @OneToMany
    private List<Branch> mybranches;

    public CoroporationManager() {

    }

    public CoroporationManager(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, String creditCard, boolean Freeze, List<Branch> mybranches) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, creditCard, Freeze);
        this.mybranches = mybranches;
    }

    public void AddOneBranch(Branch b){
        mybranches.add(b);
    }
    public void DeleteOneBranch(Branch b){
        mybranches.remove(b);
    }

    public List<Branch> getMybranchs() {
        return mybranches;
    }

    public void setMybranchs(List<Branch> mybranchs) {
        this.mybranches = mybranchs;
    }
}
