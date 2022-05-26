package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private String addres;
    private permissions permission;
    public User() {
    }

    public User(String username, String password, String firstname, String lastname, String email, String phonenumber, Date birthday, String address, permissions permission) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.birthday = birthday;
        this.addres = address;
        this.permission = permission;
    }


    public User(User guest) {
        this.username = guest.username;
        this.password = guest.password;
        this.firstname = guest.firstname;
        this.lastname = guest.lastname;
        this.email = guest.email;
        this.phonenumber = guest.phonenumber;
        this.birthday = guest.birthday;
        this.addres = guest.addres;
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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public permissions getPermission() {
        return permission;
    }

    public void setPermission(permissions permission) {
        this.permission = permission;
    }
}
