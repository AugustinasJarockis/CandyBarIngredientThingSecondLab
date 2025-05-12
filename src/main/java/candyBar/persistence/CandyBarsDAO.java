package candyBar.persistence;

import candyBar.entities.CandyBar;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public interface CandyBarsDAO {
    public void merge(CandyBar candyBar);
    public void persist(CandyBar candyBar);
    public CandyBar findOne(Integer id);
    public List<CandyBar> loadAll();
}
