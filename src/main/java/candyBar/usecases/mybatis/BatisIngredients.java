package candyBar.usecases.mybatis;

import candyBar.mybatis.dao.IngredientMapper;
import candyBar.mybatis.model.Ingredient;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class BatisIngredients {
    @Inject
    private IngredientMapper ingredientsMapper;
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
        this.ingredientsMapper.insert(ingredientToCreate);
    }
    private void loadAllIngredients(){
        this.allIngredients = ingredientsMapper.selectAll();
    }
}
