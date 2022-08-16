package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    private int id;
    @OneToOne(mappedBy = "mybranch", cascade = CascadeType.REMOVE)
    private BranchManager bmanager;
    @ManyToOne(fetch = FetchType.LAZY)
    private CoroporationManager cmanager;
    @ManyToMany(mappedBy = "mybranches", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "branch")
    private List<Complain> complains = new ArrayList<>();
    @OneToMany(mappedBy = "branch")
    private List<Report> reports = new ArrayList<>();
    @OneToMany(mappedBy = "myBranch", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Cart> myCarts = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    public Branch() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cart> getMyCarts() {
        return myCarts;
    }

    public void setMyCarts(List<Cart> myCarts) {
        this.myCarts = myCarts;
    }

    public void AddOneComplain(Complain c) {
        complains.add(c);
    }

    public void DeleteOneComplain(Complain c) {
        complains.remove(c);
    }

    public void AddOneReport(Report r) {
        reports.add(r);
    }

    public void DeleteOneReport(Report r) {
        reports.remove(r);
    }

    public BranchManager getBmanager() {
        return bmanager;
    }

    public void setBmanager(BranchManager bmanager) {
        this.bmanager = bmanager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoroporationManager getCmanager() {
        return cmanager;
    }

    public void setCmanager(CoroporationManager cmanager) {
        this.cmanager = cmanager;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Complain> getComplains() {
        return complains;
    }

    public void setComplains(List<Complain> complains) {
        this.complains = complains;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
