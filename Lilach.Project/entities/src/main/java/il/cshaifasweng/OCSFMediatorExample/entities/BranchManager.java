package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class BranchManager extends User implements Serializable {
    @OneToOne
    private Branch mybranch;

    public BranchManager() {

    }

    public BranchManager(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, String creditCard, boolean Freeze, Branch mybranch) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, creditCard, Freeze);
        this.mybranch = mybranch;
        this.mybranch.setBmanager(this);
    }

    public Branch getMybranch() {
        return mybranch;
    }

    public void setMybranch(Branch mybranch) {
        this.mybranch = mybranch;
    }
}
