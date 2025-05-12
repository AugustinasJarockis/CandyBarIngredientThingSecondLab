package candyBar.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SourceContractDto {
    private String name;

    private String countryOfOrigin;

    private String price;

    private Integer ingredientId;
}
