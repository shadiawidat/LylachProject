package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends User implements Serializable {
    private AccountTypes accounttype;
    private Double amount;
    @OneToOne
    private Cart order;
    @OneToMany
    private List<Cart> myorders;

    @OneToMany
    private List<Complain> complains;


    private String CreditCard;




    public String getCreditCard() {
        return CreditCard;
    }


    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public Client(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, String creditCard, AccountTypes accounttype, Double amount) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, creditCard,false);
        this.accounttype = accounttype;
        this.amount = 0.0;
        this.CreditCard=creditCard;
    }

    public Client() {

    }
    public List<Complain> getComplains() {
        return complains;
    }

    public void setComplains(List<Complain> complains) {
        this.complains = complains;
    }
    public void AddOneComplain(Complain c){
        complains.add(c);
    }
    public void DeleteOneComplain(Complain c){
        complains.remove(c);
    }

    public void AddOneToCart(Cart c){
        myorders.add(c);
    }
    public void DeleteOneFromCart(Cart c){
        myorders.remove(c);
    }

    public AccountTypes getAccounttype() {
        return accounttype;
    }

    public Cart getOrder() {
        return order;
    }

    public void setOrder(Cart order) {
        this.order = order;
    }

    public void setAccounttype(AccountTypes accounttype) {
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


}
