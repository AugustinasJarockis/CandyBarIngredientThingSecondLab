package candyBar.services;

import candyBar.entities.CandyBar;
import candyBar.entities.Ingredient;
import candyBar.entities.SourceContract;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Getter
@Setter
@Named
public class PriceCounter {
    public double CalculatePrice(CandyBar candyBar) {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {}

        double totalPrice = 0;
        for (Ingredient ingredient: candyBar.getIngredients()) {
            double minPrice = Double.POSITIVE_INFINITY;
            for (SourceContract contract: ingredient.getSourceContracts()) {
                double price = Double.POSITIVE_INFINITY;
                try {
                    price = Double.parseDouble(contract.getPrice());
                }
                catch (Exception exception) {}
                if (minPrice > price) {
                    minPrice = price;
                }
            }
            totalPrice += minPrice;
        }
        return totalPrice;
    }
}
