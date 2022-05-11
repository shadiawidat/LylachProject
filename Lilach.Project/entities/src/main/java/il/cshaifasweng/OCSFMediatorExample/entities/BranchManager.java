package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class BranchManager extends User {
    @OneToOne
    private Branch mybranch;
    public BranchManager() {

    }
    public Branch getMybranch() {
        return mybranch;
    }

    public void setMybranch(Branch mybranch) {
        this.mybranch = mybranch;
    }
}
