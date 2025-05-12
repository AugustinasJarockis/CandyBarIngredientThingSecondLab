package candyBar.persistence;

import candyBar.entities.Ingredient;
import candyBar.entities.SourceContract;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SourceContractsDAO {
    @Inject
    private EntityManager em;
    public void persist(SourceContract sourceContract){
        this.em.persist(sourceContract);
    }
    public SourceContract findOne(Integer id){
        return em.find(SourceContract.class, id);
    }
    public SourceContract update(SourceContract updatedContract) {return em.merge(updatedContract);}
    public List<SourceContract> loadAll(Ingredient ingredient) {
        return em.createQuery("SELECT c FROM SourceContract AS c WHERE c.ingredient = :ingredient", SourceContract.class)
                .setParameter("ingredient", ingredient)
                .getResultList();
    }
}
