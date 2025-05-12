package candyBar.decorators;

import candyBar.entities.CandyBar;
import candyBar.entities.Ingredient;
import candyBar.persistence.CandyBarsDAO;

import javax.decorator.*;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
@Dependent
public abstract class CandyBarDAODecorator implements CandyBarsDAO {
    @Inject @Delegate @Any
    CandyBarsDAO candyBarsDAO;

    @Override
    public CandyBar findOne(Integer id) {
        CandyBar candyBar = candyBarsDAO.findOne(id);
        for (Ingredient ingredient: candyBar.getIngredients()) {
            if (ingredient.getName().contains("[danger]")) {
                System.out.println("WARNING: Candy bar contains dangerous ingredient – " + ingredient.getName());
            }
        }
        return candyBar;
    }
}
