package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ComplainReport extends Report implements Serializable {
    @OneToMany
    private List<Complain> complains = new ArrayList<>();
    private int complainsnum;

    public ComplainReport(int complainsnum) {
        super();
        this.complainsnum = complainsnum;
    }

    public ComplainReport() {
        this.setReportType(ReportType.COMPLAIN);
    }

    public void AddOneComplain(Complain c) {
        complains.add(c);
    }

    public void DeleteOneComplain(Complain c) {
        complains.remove(c);
    }

    public List<Complain> getComplains() {
        return complains;
    }

    public void setComplains(List<Complain> complains) {
        this.complains = complains;
    }

    public int getComplainsnum() {
        return complainsnum;
    }

    public void setComplainsnum(int complainsnum) {
        this.complainsnum = complainsnum;
    }
}
