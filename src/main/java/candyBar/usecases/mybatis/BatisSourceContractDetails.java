package candyBar.usecases.mybatis;

import candyBar.mybatis.dao.SourceContractMapper;
import candyBar.mybatis.model.SourceContract;
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
@Getter
@Setter
public class BatisSourceContractDetails implements Serializable {
    private SourceContract sourceContract;
    @Inject
    private SourceContractMapper sourceContractMapper;
    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer sourceContractId = Integer.parseInt(requestParameters.get("contractId"));
        this.sourceContract = sourceContractMapper.selectByPrimaryKey(sourceContractId);
    }

    public String redirectBackToIngredient() {
        return "/myBatis/ingredientDetails?faces-redirect=true&amp;ingredientId=" + sourceContract.getIngredientId();
    }
}