package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity
public class CoroporationManager extends User implements Serializable{
    @OneToMany
    private List<Branch> mybranches;

    public CoroporationManager() {

    }
    public void AddOneBranch(Branch b){
        mybranches.add(b);
    }
    public void DeleteOneBranch(Branch b){
        mybranches.remove(b);
    }
    public List<Branch> getMybranchs() {
        return mybranches;
    }

    public void setMybranchs(List<Branch> mybranchs) {
        this.mybranches = mybranchs;
    }
}
