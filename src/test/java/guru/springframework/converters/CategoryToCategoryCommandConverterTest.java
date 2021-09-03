package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;

class CategoryToCategoryCommandConverterTest {
	private final Long ID= 1L;
	private final String DESCRIPTION = "description";
	CategoryToCategoryCommandConverter categoryConverter;
	@BeforeEach
	void setUp() throws Exception {
		categoryConverter = new CategoryToCategoryCommandConverter();
	}
	@Test
	public void testNullParameter() {
		assertNull(categoryConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(categoryConverter.convert(new Category()));
	}
	@Test
	void testConvert() {
		Category category = new Category();
		category.setId(ID);
		category.setDescription(DESCRIPTION);
		CategoryCommand catCommand = categoryConverter.convert(category);
		assertNotNull(catCommand);
		assertEquals(ID, catCommand.getId());
		assertEquals(DESCRIPTION, catCommand.getDescription());
	}

}
