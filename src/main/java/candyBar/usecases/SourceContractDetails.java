package candyBar.usecases;

import candyBar.entities.SourceContract;
import candyBar.persistence.SourceContractsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Console;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class SourceContractDetails implements Serializable {
    private SourceContract sourceContract;
    private String error = null;
    @Inject
    private SourceContractsDAO sourceContractsDAO;
    @PostConstruct
    private void init() {
        System.out.println("SourceContractDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer sourceContractId = Integer.parseInt(requestParameters.get("contractId"));
        this.sourceContract = sourceContractsDAO.findOne(sourceContractId);
    }

    @Transactional
    public void updateSourceContract() {
        error = null;
        try {
            sourceContract = sourceContractsDAO.update(sourceContract);
        }
        catch (OptimisticLockException exception) {
            System.out.println("An optimistic lock exception has occurred");
            error = exception.getMessage();
            SourceContract prevSourceContract = this.sourceContract;
            this.sourceContract = sourceContractsDAO.findOne(this.sourceContract.getId());
            this.sourceContract.setName(prevSourceContract.getName());
            this.sourceContract.setCountryOfOrigin(prevSourceContract.getCountryOfOrigin());
            this.sourceContract.setPrice(prevSourceContract.getPrice());
        }
    }

    public String redirectBackToIngredient() {
        return "ingredientDetails?faces-redirect=true&amp;ingredientId=" + sourceContract.getIngredient().getId();
    }
}