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

@ViewScoped
@Named
@Getter @Setter
public class BatisIngredientDetails implements  Serializable {
    private Ingredient ingredient;

    @Inject
    private IngredientMapper ingredientMapper;
    @Inject
    private SourceContractMapper sourceContractMapper;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer ingredientId = Integer.parseInt(requestParameters.get("ingredientId"));
        this.ingredient = ingredientMapper.selectByPrimaryKey(ingredientId);
    }

    @Transactional
    public List<SourceContract> getIngredientSourceContracts() {
        return sourceContractMapper.selectByIngredientId(this.ingredient.getId());
    }
}
