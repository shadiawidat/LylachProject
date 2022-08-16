package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class BranchManager extends User implements Serializable {
    @OneToOne(cascade = CascadeType.REMOVE)
    private Branch mybranch;

    public BranchManager() {

    }

    public BranchManager(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, String creditCard, boolean Freeze, Branch mybranch) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, creditCard, Freeze);
    }

    public BranchManager(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, boolean Freeze, Branch mybranch) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, Freeze);
        this.mybranch = mybranch;
    }

    public Branch getMybranch() {
        return mybranch;
    }

    public void setMybranch(Branch mybranch) {
        this.mybranch = mybranch;
    }
}
