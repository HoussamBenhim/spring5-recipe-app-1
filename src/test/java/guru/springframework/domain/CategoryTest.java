package guru.springframework.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() throws Exception {
		category= new Category();
	}

	@Test
	public void getId() throws Exception{
			Long idValue = 4L;
			category.setId(4L);
			assertEquals(idValue, category.getId());
	}

}
