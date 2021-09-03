package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Category;

class CategoryCommandToCategoryConverterTest {
	
	private final Long ID= 1L;
	private final String DESCRIPTION = "description";
	CategoryCommandToCategoryConverter categoryConverter;
	
	@BeforeEach
	public void setUp() throws Exception {
		categoryConverter = new CategoryCommandToCategoryConverter();
	}
	@Test
	public void testNullParameter() {
		assertNull(categoryConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(categoryConverter.convert(new CategoryCommand()));
	}
	@Test
	public void testConvert() {
		CategoryCommand catCommand = new CategoryCommand();
		catCommand.setId(ID);
		catCommand.setDescription(DESCRIPTION);
		Category category = categoryConverter.convert(catCommand);
		assertNotNull(category);
		assertEquals(ID, category.getId());
		assertEquals(DESCRIPTION, category.getDescription());
		
	}

}
