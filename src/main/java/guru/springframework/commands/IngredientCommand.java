package guru.springframework.commands;

import java.math.BigDecimal;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	private Long id;
	private String descirption;
	private BigDecimal amount;
	private RecipeCommand recipeCommand;
	private UnitOfMeasureCommand uomCommand;
}
