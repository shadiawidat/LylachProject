package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "mybranch")
    private BranchManager bmanager;
    @ManyToOne(fetch = FetchType.LAZY)
    private CoroporationManager cmanager;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Client> client;            //needs to check
    /*@OneToMany
    private List<User> workers; */              //needs to check
    @OneToMany(mappedBy = "branch")
    private List<Complain> complains;
    @OneToMany(mappedBy = "branch")
    private List<Report> reports;
    public Branch() {

    }
    public void AddOneClient(Client c){
        client.add(c);
    }
    public void DeleteOneClient(Client c){
        client.remove(c);
    }
  /*  public void AddIOneWorker(User u){
        workers.add(u);
    }
    public void DeleteOneWorker(User u){
        workers.remove(u);
    }*/
    public void AddOneComplain(Complain c){
        complains.add(c);
    }
    public void DeleteOneComplain(Complain c){
        complains.remove(c);
    }
    public void AddOneReport(Report r){
        reports.add(r);
    }
    public void DeleteOneReport(Report r){
        reports.remove(r);
    }
    public BranchManager getBmanager() {
        return bmanager;
    }

    public void setBmanager(BranchManager bmanager) {
        this.bmanager = bmanager;
    }

    public CoroporationManager getCmanager() {
        return cmanager;
    }

    public void setCmanager(CoroporationManager cmanager) {
        this.cmanager = cmanager;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    /*public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }*/

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
