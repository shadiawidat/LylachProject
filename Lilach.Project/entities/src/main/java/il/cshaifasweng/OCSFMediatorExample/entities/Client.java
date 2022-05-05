package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends User implements Serializable {
    private int accounttype;
    private Double amount;
    private List<Cart> myorders;
    private List<Branch> mybranches;
    private Boolean freezed;

    public int getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(int accounttype) {
        this.accounttype = accounttype;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Cart> getMyorders() {
        return myorders;
    }

    public void setMyorders(List<Cart> myorders) {
        this.myorders = myorders;
    }

    public List<Branch> getMybranches() {
        return mybranches;
    }

    public void setMybranches(List<Branch> mybranches) {
        this.mybranches = mybranches;
    }

    public Boolean getFreezed() {
        return freezed;
    }

    public void setFreezed(Boolean freezed) {
        this.freezed = freezed;
    }
}
