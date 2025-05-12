package candyBar.usecases;

import candyBar.persistence.IngredientsDAO;
import candyBar.entities.Ingredient;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Ingredients {
    @Inject
    private IngredientsDAO ingredientsDAO;
    @Getter
    private List<Ingredient> allIngredients;

    @Getter @Setter
    private Ingredient ingredientToCreate = new Ingredient();

    @PostConstruct
    public void init(){
        loadAllIngredients();
    }
    @Transactional
    public void createIngredient(){
        this.ingredientsDAO.persist(ingredientToCreate);
    }
    private void loadAllIngredients(){
        this.allIngredients = ingredientsDAO.loadAll();
    }
}
