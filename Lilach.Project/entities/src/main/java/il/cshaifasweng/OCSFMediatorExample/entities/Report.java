package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "report")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Branch.class)
    private Branch branch;
    private Date datefrom;
    private Date dateto;
    private ReportType reportType;

    public Report() {
    }

    public Report(Date from, Date to, Branch branch, ReportType reportType) {
        this.datefrom = from;
        this.dateto = to;
        this.branch = branch;
        this.reportType = reportType;
    }

    public Date getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }

    public Date getDateto() {
        return dateto;
    }

    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
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
