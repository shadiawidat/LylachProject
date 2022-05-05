package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class ComplainReport extends Report implements Serializable {
    private List<Complain> complains;
    private int complainsnum;

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
