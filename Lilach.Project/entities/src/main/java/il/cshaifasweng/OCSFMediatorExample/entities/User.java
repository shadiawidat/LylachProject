package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private Date birthday;
    private String address;
    private permissions permission;
    private String ID;

    public String getID() {
        return ID;
    }


    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Branch> getMybranches() {
        return mybranches;
    }

    public void setMybranches(List<Branch> mybranches) {
        this.mybranches = mybranches;
    }

    @ManyToMany
    private List<Branch> mybranches=new ArrayList<>();
//    private Client client;
//
//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }

    private boolean Freeze;
    public User() {
    }

    public boolean isFreeze() {
        return Freeze;
    }

    public void setFreeze(boolean freeze) {
        Freeze = freeze;
    }

    //Worker's Contructor
    public User(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID,boolean Freeze) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.birthday = birthday;
        this.address = address;
        this.permission = permission;
        this.ID=ID;
        this.Freeze=Freeze;
    }

    public User(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission, String ID, String creditCard,boolean Freeze) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.birthday = birthday;
        this.address = address;
        this.permission = permission;
        this.ID=ID;
        this.Freeze=Freeze;
    }




    public void AddOneBranch(Branch b){
        mybranches.add(b);
    }
    public void DeleteOneBranch(Branch b){
        mybranches.remove(b);
    }
    public User(User guest) {
        this.username = guest.username;
        this.password = guest.password;
        this.firstname = guest.firstname;
        this.lastname = guest.lastname;
        this.email = guest.email;
        this.phonenumber = guest.phonenumber;
        this.birthday = guest.birthday;
        this.address = guest.address;
        this.permission = guest.permission;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addres) {
        this.address = addres;
    }

    public permissions getPermission() {
        return permission;
    }

    public void setPermission(permissions permission) {
        this.permission = permission;
    }
}
