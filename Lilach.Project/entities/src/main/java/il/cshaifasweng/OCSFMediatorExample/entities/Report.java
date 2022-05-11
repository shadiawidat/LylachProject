package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Branch.class)
    private Branch branch;
   /* private Date from;
    private Date to;*/
    public Report() {
    }

   /* public Report(Date from, Date to) {
        this.from = from;
        this.to = to;
    }*/

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

  /*  public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }*/

    /*public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }*/
}
