package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

class UnitOfMeasureCommandToUnitOfMeasureConverterTest {
	private final String UOM = "UOM";
	private final Long ID = new Long(1L);
	UnitOfMeasureCommandToUnitOfMeasureConverter converter;
	@BeforeEach
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasureConverter();
	}
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void testConvert() {
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(ID);
		command.setUomCommand(UOM);
		UnitOfMeasure unitOfMeasure = converter.convert(command);
		assertNotNull(unitOfMeasure);
		assertEquals(ID, unitOfMeasure.getId());
		assertEquals(UOM, unitOfMeasure.getUom());
	}

}
