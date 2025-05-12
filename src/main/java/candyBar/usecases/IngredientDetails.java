package candyBar.usecases;

import candyBar.entities.Ingredient;
import candyBar.persistence.IngredientsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class IngredientDetails implements  Serializable{
    private Ingredient ingredient;

    @Inject
    private IngredientsDAO ingredientDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer ingredientId = Integer.parseInt(requestParameters.get("ingredientId"));
        this.ingredient = ingredientDAO.findOne(ingredientId);
    }
}
