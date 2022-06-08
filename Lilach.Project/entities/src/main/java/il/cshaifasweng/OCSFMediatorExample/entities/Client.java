package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends User implements Serializable {

    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private List<Cart> myorders=new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Complain> complains=new ArrayList<>();

    private AccountTypes accounttype;
    private Double amount;

    private String CreditCard;

    private LocalDateTime MemberShipt;

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public Client(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, String creditCard, AccountTypes accounttype, Double amount) {
        super(username, password, firstname, lastname, email, phonenumber, birthday, address, permission, ID, creditCard,false);
        this.accounttype = accounttype;
        this.amount = amount;
        this.CreditCard=creditCard;
        MemberShipt=LocalDateTime.now();
    }

    public LocalDateTime getMemberShipt() {
        return MemberShipt;
    }

    public void setMemberShipt(LocalDateTime memberShipt) {
        MemberShipt = memberShipt;
    }

    public Client() {
        MemberShipt=LocalDateTime.now();
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
