package candyBar.usecases;

import candyBar.entities.Ingredient;
import candyBar.entities.SourceContract;
import candyBar.persistence.IngredientsDAO;
import candyBar.persistence.SourceContractsDAO;
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
public class SourceContracts implements Serializable {
    @Inject
    private SourceContractsDAO sourceContractsDAO;
    @Inject
    private IngredientsDAO ingredientsDAO;
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
            this.ingredient = ingredientsDAO.findOne(ingredientId);
            loadAllSourceContracts(ingredient);
            sourceContractToCreate.setIngredient(ingredient);
        }
    }
    @Transactional
    public void createSourceContract(){
        this.sourceContractsDAO.persist(sourceContractToCreate);
        this.ingredient = ingredientsDAO.findOne(ingredient.getId());
        ingredient.getSourceContracts().add(sourceContractToCreate);
        ingredientsDAO.update(ingredient);
    }
    private void loadAllSourceContracts(Ingredient ingredient){
        this.allSourceContracts = sourceContractsDAO.loadAll(ingredient);
    }

    public String redirectBackToIngredient() {
        return "ingredientDetails?faces-redirect=true&amp;ingredientId=" + ingredient.getId();
    }
}
