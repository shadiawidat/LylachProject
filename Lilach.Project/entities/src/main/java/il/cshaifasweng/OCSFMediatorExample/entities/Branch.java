package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Branch(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "mybranch")
    private BranchManager bmanager;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoroporationManager cmanager;

    @ManyToMany
    @JoinTable(
            name = "User_Branch",
            joinColumns = { @JoinColumn(name = "User_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Branch_ID") }
    )
    private List<User> users=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(
            name = "Branch_Client",
            joinColumns = { @JoinColumn(name = "Branch_id") },
            inverseJoinColumns = { @JoinColumn(name = "Client_id") }
    )
    private List<Client> clients=new ArrayList<>();


    @OneToMany(mappedBy = "branch")
    private List<Complain> complains=new ArrayList<>();

    @OneToMany(mappedBy = "branch")
    private List<Report> reports=new ArrayList<>();
    public Branch() {

    }
//    public void AddOneClient(Client c){
//        client.add(c);
//    }
//    public void DeleteOneClient(Client c){
//        client.remove(c);
//    }
//    public void AddIOneWorker(User u){
//        workers.add(u);
//    }
//    public void DeleteOneWorker(User u){
//        workers.remove(u);
//    }
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void AddOneClient(Client client){
        clients.add(client);
    }
    public void DeleteOneClient(Client client){
        clients.remove(client);
    }
//    public List<Client> getClient() {
//        return client;
//    }
//
//    public void setClient(List<Client> client) {
//        this.client = client;
//    }
//
//    public List<User> getWorkers() {
//        return workers;
//    }
//
//    public void setWorkers(List<User> workers) {
//        this.workers = workers;
//    }

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
