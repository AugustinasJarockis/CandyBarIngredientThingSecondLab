package candyBar.persistence;

import candyBar.entities.Ingredient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class IngredientsDAO {
    @Inject
    private EntityManager em;
    public void persist(Ingredient ingredient){
        this.em.persist(ingredient);
    }
    public void update(Ingredient ingredient){
        this.em.merge(ingredient);
    }
    public Ingredient findOne(Integer id){
        return em.find(Ingredient.class, id);
    }
    public List<Ingredient> loadAll() {
        return em.createQuery("SELECT i FROM Ingredient AS i", Ingredient.class).getResultList();
    }
}
