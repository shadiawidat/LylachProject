package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends User  {
    private int accounttype;
    private Double amount;
    @OneToMany
    private List<Cart> myorders;
    @ManyToMany
    private List<Branch> mybranches;
    private Boolean freezed;

    public Client(int accounttype, Double amount, Boolean freezed) {
        super();
        this.accounttype = accounttype;
        this.amount = amount;
        this.freezed = freezed;
    }

    public Client() {

    }
   /* public void AddOneCart(Cart c){
        myorders.add(c);
    }
    public void DeleteOneCart(Cart c){
        myorders.remove(c);
    }*/
    public void AddOneBranch(Branch b){
        mybranches.add(b);
    }
    public void DeleteOneBranch(Branch b){
        mybranches.remove(b);
    }
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

  /*  public List<Cart> getMyorders() {
        return myorders;
    }

    public void setMyorders(List<Cart> myorders) {
        this.myorders = myorders;
    }
*/
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
