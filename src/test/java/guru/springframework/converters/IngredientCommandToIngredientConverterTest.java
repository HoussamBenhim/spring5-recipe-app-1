package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;

class IngredientCommandToIngredientConverterTest {
	IngredientCommandToIngredientConverter ingredientConverter;
	private final Long ID = 1L;
	private final String UOM = "UOM";
	private final String DESCRIPTION = "description";
	private final BigDecimal AMOUNT = new BigDecimal(15); 
	
	@BeforeEach
	void setUp() throws Exception {
		ingredientConverter = new IngredientCommandToIngredientConverter(new UnitOfMeasureCommandToUnitOfMeasureConverter());
	}
	
	@Test
	public void testNullParameter() {
		assertNull(ingredientConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(ingredientConverter.convert(new IngredientCommand()));
	}
	@Test
	public void testConvert() {
		UnitOfMeasureCommand unitofmeasureCommand = new UnitOfMeasureCommand();
		unitofmeasureCommand.setId(ID);
		unitofmeasureCommand.setUomCommand(UOM);
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID);
		ingredientCommand.setAmount(AMOUNT);
		ingredientCommand.setDescirption(DESCRIPTION);
		ingredientCommand.setUomCommand(unitofmeasureCommand);
		
		Ingredient ingredient = ingredientConverter.convert(ingredientCommand);
		assertNotNull(ingredient);
		assertEquals(ID, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescirption());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(UnitOfMeasure.class, ingredient.getUom().getClass());
		assertEquals(ID, ingredient.getUom().getId());
		assertEquals(UOM, ingredient.getUom().getUom());
	
	}

}
