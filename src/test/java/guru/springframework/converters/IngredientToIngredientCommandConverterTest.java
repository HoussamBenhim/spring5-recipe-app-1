package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;

class IngredientToIngredientCommandConverterTest {
	IngredientToIngredientCommandConverter ingredientConverter;
	private final Long ID = 1L;
	private final String UOM = "UOM";
	private final String DESCRIPTION = "description";
	private final BigDecimal AMOUNT = new BigDecimal(15); 
	
	@BeforeEach
	void setUp() throws Exception {
		ingredientConverter = new IngredientToIngredientCommandConverter(new UnitOfMeasureToUnitOfMeasureCommandConverter());
	}
	
	@Test
	public void testNullParameter() {
		assertNull(ingredientConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(ingredientConverter.convert(new Ingredient()));
	}
	@Test
	public void testConvert() {
		UnitOfMeasure unitofmeasure = new UnitOfMeasure();
		unitofmeasure.setId(ID);
		unitofmeasure.setUom(UOM);
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID);
		ingredient.setAmount(AMOUNT);
		ingredient.setDescirption(DESCRIPTION);
		ingredient.setUom(unitofmeasure);
		
		IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);
		assertNotNull(ingredientCommand);
		assertEquals(ID, ingredientCommand.getId());
		assertEquals(DESCRIPTION, ingredientCommand.getDescirption());
		assertEquals(AMOUNT, ingredientCommand.getAmount());
		assertEquals(UnitOfMeasureCommand.class, ingredientCommand.getUomCommand().getClass());
		assertEquals(ID, ingredientCommand.getUomCommand().getId());
		assertEquals(UOM, ingredientCommand.getUomCommand().getUom());
	
	}

}
