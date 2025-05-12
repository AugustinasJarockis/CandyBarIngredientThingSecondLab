package candyBar.usecases.mybatis;

import candyBar.mybatis.dao.CandybarIngredientMapper;
import candyBar.mybatis.dao.CandybarMapper;
import candyBar.mybatis.dao.IngredientMapper;
import candyBar.mybatis.model.Candybar;
import candyBar.mybatis.model.Ingredient;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class BatisCandyBarDetails implements Serializable {
    private Candybar candyBar;
    private ArrayList<SelectItem> addableIngredients = new ArrayList<>();
    @Inject
    private CandybarMapper candyBarsMapper;
    @Inject
    private IngredientMapper ingredientMapper;
    @Inject
    private CandybarIngredientMapper candybarIngredientMapper;
    @Getter @Setter
    private String SelectedIngredient;
    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer candyBarId = Integer.parseInt(requestParameters.get("candyBarId"));
        this.candyBar = candyBarsMapper.selectByPrimaryKey(candyBarId);

        List<Ingredient> allIngredients = ingredientMapper.selectAll();
        List<Ingredient> candyBarIngredient = candybarIngredientMapper.getIngredients(candyBarId);
        for (Ingredient ingredient : allIngredients) {
            boolean alreadyPartOf = false;
            for (Ingredient existingIngredient : candyBarIngredient) {
                if (existingIngredient.getId().equals(ingredient.getId())) {
                    alreadyPartOf = true;
                    break;
                }
            }
            if (!alreadyPartOf) {
                addableIngredients.add(new SelectItem(ingredient.getId(), ingredient.getName()));
            }
        }
    }

    @Transactional
    public void addIngredient(){
        int selectedId = Integer.parseInt(this.SelectedIngredient);
        Ingredient ingredientToAdd = ingredientMapper.selectByPrimaryKey(selectedId);
        candybarIngredientMapper.addIngredient(candyBar.getId(), ingredientToAdd.getId());
    }

    @Transactional
    public List<Ingredient> getIngredients() {
        return candybarIngredientMapper.getIngredients(candyBar.getId());
    }

    public String redirectBackToCandyBar() {
        return "/myBatis/candyBarDetails?faces-redirect=true&amp;candyBarId=" + candyBar.getId();
    }
}