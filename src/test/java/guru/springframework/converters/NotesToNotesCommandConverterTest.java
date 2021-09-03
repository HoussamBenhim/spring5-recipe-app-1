package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;

class NotesToNotesCommandConverterTest {
	private final Long ID = 1L;
	private final String NOTES = "notes";
	 NotesToNotesCommandConverter notesConverter;
	@BeforeEach
	void setUp() throws Exception {
		notesConverter = new NotesToNotesCommandConverter();
	}
	@Test
	public void testNullParameter() {
		assertNull(notesConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(notesConverter.convert(new Notes()));
	}
	@Test
	void testConvert() {
		Notes  notes = new Notes();
		notes.setId(ID);
		notes.setRecipeNote(NOTES);
		
		NotesCommand notesCommand = notesConverter.convert(notes);
		assertNotNull(notesCommand);
		assertEquals(ID, notesCommand.getId());
		assertEquals(NOTES, notesCommand.getRecipeNote());

	}

}
