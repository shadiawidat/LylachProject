package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity
public class ComplainReport extends Report implements Serializable{
    @OneToMany
    private List<Complain> complains;
    private int complainsnum;

    public ComplainReport(int complainsnum) {
        super();
        this.complainsnum = complainsnum;
    }

    public ComplainReport() {

    }
    public void AddOneComplain(Complain c){
        complains.add(c);
    }
    public void DeleteOneComplain(Complain c){
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
