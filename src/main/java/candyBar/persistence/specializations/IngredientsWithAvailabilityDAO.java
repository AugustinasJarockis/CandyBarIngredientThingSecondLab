package candyBar.persistence.specializations;

import candyBar.entities.Ingredient;
import candyBar.persistence.IngredientsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

//@Specializes
@Alternative
@ApplicationScoped
public class IngredientsWithAvailabilityDAO extends IngredientsDAO {
    @Inject
    private EntityManager em;

    public void persist(Ingredient ingredient) {
        if (ingredient.getName().startsWith("[Available] "))
            ingredient.setName(ingredient.getName().substring(12));
        else if (ingredient.getName().startsWith("[Unavailable] "))
            ingredient.setName(ingredient.getName().substring(14));

        this.em.persist(ingredient);
    }

    public void update(Ingredient ingredient){
        if (ingredient.getName().startsWith("[Available] "))
            ingredient.setName(ingredient.getName().substring(12));
        else if (ingredient.getName().startsWith("[Unavailable] "))
            ingredient.setName(ingredient.getName().substring(14));

        this.em.merge(ingredient);
    }

    public List<Ingredient> loadAll() {
        List<Ingredient> ingredients = em.createQuery("SELECT i FROM Ingredient AS i", Ingredient.class).getResultList();
        for (Ingredient ingredient: ingredients) {
            if (ingredient.getSourceContracts().size() != 0 && !ingredient.getName().startsWith("[Available] ")) {
                ingredient.setName("[Available] " + ingredient.getName());
            }
            else if (ingredient.getSourceContracts().size() == 0 && !ingredient.getName().startsWith("[Unavailable] ")){
                ingredient.setName("[Unavailable] " + ingredient.getName());
            }
        }
        return ingredients;
    }
}
