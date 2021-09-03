package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;


class UnitOfMeasureToUnitOfMeasureCommandConverterTest {
	UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter;
	private final Long ID = 1l;
	private final String DESCRIPTION = "description";
	@BeforeEach
	void setUp() throws Exception {
		uomConverter = new UnitOfMeasureToUnitOfMeasureCommandConverter();
	}
	@Test
	public void testNullParameter() {
		assertNull(uomConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(uomConverter.convert(new UnitOfMeasure()));
	}
	@Test
	void testConvert() {
		UnitOfMeasure uom =new UnitOfMeasure();
		uom.setId(ID);
		uom.setUom(DESCRIPTION);
		UnitOfMeasureCommand uomCommand = uomConverter.convert(uom);
		assertNotNull(uomCommand);
		assertEquals(ID, uom.getId());
		assertEquals(DESCRIPTION , uomCommand.getUom());
	}

}
