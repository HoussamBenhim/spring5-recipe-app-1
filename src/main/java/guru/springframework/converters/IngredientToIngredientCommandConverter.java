package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientToIngredientCommandConverter implements Converter<Ingredient, IngredientCommand>{

	private final UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter;
	
	public IngredientToIngredientCommandConverter(
			UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if(source == null) {
			return null;
		}
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(source.getId());
		if(source.getRecipe() != null) {
			ingredientCommand.setRecipeId(source.getRecipe().getId());
		}
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setDescirption(source.getDescirption());
		ingredientCommand.setUomCommand(uomConverter.convert(source.getUom()));
		return ingredientCommand;
	}
	
}
