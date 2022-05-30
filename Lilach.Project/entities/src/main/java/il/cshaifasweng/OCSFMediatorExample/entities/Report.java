package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Branch.class)
    private Branch branch;
    private Date datefrom;
    private Date dateto;
    public Report() {
    }

    public Report(Date from, Date to) {
        this.datefrom = from;
        this.dateto = to;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Date getFrom() {
        return datefrom;
    }

    public void setFrom(Date from) {
        this.datefrom = from;
    }

    public Date getTo() {
        return dateto;
    }

    public void setTo(Date to) {
        this.dateto = to;
    }
}
