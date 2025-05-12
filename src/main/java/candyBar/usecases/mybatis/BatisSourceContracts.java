package candyBar.usecases.mybatis;

import candyBar.mybatis.dao.IngredientMapper;
import candyBar.mybatis.dao.SourceContractMapper;
import candyBar.mybatis.model.Ingredient;
import candyBar.mybatis.model.SourceContract;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class BatisSourceContracts implements Serializable {
    @Inject
    private SourceContractMapper sourceContractMapper;
    @Inject
    private IngredientMapper ingredientMapper;
    @Getter
    private List<SourceContract> allSourceContracts;

    @Getter
    private Ingredient ingredient;
    @Getter @Setter
    private SourceContract sourceContractToCreate = new SourceContract();
    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String requestParam = requestParameters.get("ingredientId");
        if (requestParam != null) {
            Integer ingredientId = Integer.parseInt(requestParam);
            this.ingredient = ingredientMapper.selectByPrimaryKey(ingredientId);
            loadAllSourceContracts(ingredient);
            sourceContractToCreate.setIngredientId(ingredient.getId());
        }
    }
    @Transactional
    public void createSourceContract(){
        sourceContractToCreate.setIngredientId(ingredient.getId());
        this.sourceContractMapper.insert(sourceContractToCreate);
    }
    private void loadAllSourceContracts(Ingredient ingredient){
        this.allSourceContracts = sourceContractMapper.selectByIngredientId(ingredient.getId());
    }

    public String redirectBackToIngredient() {
        return "/myBatis/ingredientDetails?faces-redirect=true&amp;ingredientId=" + ingredient.getId();
    }
}
