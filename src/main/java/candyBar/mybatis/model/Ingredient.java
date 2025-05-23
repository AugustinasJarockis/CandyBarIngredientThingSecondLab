package candyBar.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.INGREDIENT.ID
     *
     * @mbg.generated Sun Apr 06 16:09:44 EEST 2025
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.INGREDIENT.NAME
     *
     * @mbg.generated Sun Apr 06 16:09:44 EEST 2025
     */
    private String name;
    private List<Ingredient> presentIn = new ArrayList<>();
    private List<SourceContract> sourceContracts = new ArrayList<>();

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.INGREDIENT.ID
     *
     * @return the value of PUBLIC.INGREDIENT.ID
     *
     * @mbg.generated Sun Apr 06 16:09:44 EEST 2025
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.INGREDIENT.ID
     *
     * @param id the value for PUBLIC.INGREDIENT.ID
     *
     * @mbg.generated Sun Apr 06 16:09:44 EEST 2025
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.INGREDIENT.NAME
     *
     * @return the value of PUBLIC.INGREDIENT.NAME
     *
     * @mbg.generated Sun Apr 06 16:09:44 EEST 2025
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.INGREDIENT.NAME
     *
     * @param name the value for PUBLIC.INGREDIENT.NAME
     *
     * @mbg.generated Sun Apr 06 16:09:44 EEST 2025
     */
    public void setName(String name) {
        this.name = name;
    }
    public List<SourceContract> getSourceContracts() {
        return this.sourceContracts;
    }
}