package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientCommandToIngredientConverter implements Converter<IngredientCommand, Ingredient>{
	
	private final UnitOfMeasureCommandToUnitOfMeasureConverter uomConverter;
	
	public IngredientCommandToIngredientConverter(
			UnitOfMeasureCommandToUnitOfMeasureConverter uom
			) {
		super();
		this.uomConverter= uom;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if(source == null) {
			return null;
		}
		Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setAmount(source.getAmount());
		ingredient.setDescirption(source.getDescirption());
		ingredient.setUom(uomConverter.convert(source.getUomCommand()));
		return ingredient;
	}
	
}
