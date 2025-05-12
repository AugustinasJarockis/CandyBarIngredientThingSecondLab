package candyBar.components;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
@Getter @Setter
public class VisitCounter implements Serializable{
    private int visitCount = 0;
    public void updateVisitCount() {
        visitCount++;
    }
}
