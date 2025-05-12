package candyBar.persistence.alternatives;

import candyBar.entities.CandyBar;
import candyBar.persistence.CandyBarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Alternative
public class PergaleCandyBarsDAO implements CandyBarsDAO {
    @Inject
    private EntityManager em;
    public void merge(CandyBar candyBar) {
        if (!candyBar.getName().startsWith("[per]"))
            candyBar.setName("[per] " + candyBar.getName());
        this.em.merge(candyBar);
    }
    public void persist(CandyBar candyBar){
        if (!candyBar.getName().startsWith("[per]"))
            candyBar.setName("[per] " + candyBar.getName());
        this.em.persist(candyBar);
    }
    public CandyBar findOne(Integer id){
        CandyBar candyBar = em.find(CandyBar.class, id);
        if (!candyBar.getName().startsWith("[per]")) {
            return null;
        }
        return candyBar;
    }
    public List<CandyBar> loadAll() {
        return em.createQuery(
                "SELECT c FROM CandyBar AS c WHERE SUBSTRING(c.name, 1, 5) = '[per]'",
                CandyBar.class
        ).getResultList();
    }
}
