package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Branch")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BranchManager bmanager;
    private CoroporationManager cmanager;
    private List<Client> client;
    private List<User> workers;
    private List<Complain> complains;
    private List<Report> reports;

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

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
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
