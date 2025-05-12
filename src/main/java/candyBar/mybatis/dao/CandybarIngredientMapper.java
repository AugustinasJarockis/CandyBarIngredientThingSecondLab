package candyBar.mybatis.dao;

import candyBar.mybatis.model.Candybar;
import candyBar.mybatis.model.Ingredient;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface CandybarIngredientMapper {
    int deleteIngredient(int candyBarId, int ingredientId);
    int addIngredient(@Param("candybarId") int candybarId,
                      @Param("ingredientsId") int ingredientsId);
    List<Ingredient> getIngredients(int candybarId);
    List<Candybar> getCandybars(int ingredientId);

    List<Candybar> getCandybarsWithIngredients();
}
