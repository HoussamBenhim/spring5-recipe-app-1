package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

class NotesCommandToNotesConverterTest {

	private final Long ID = 1L;
	private final String NOTES = "notes";
	 NotesCommandToNotesConverter notesConverter;
	@BeforeEach
	void setUp() throws Exception {
		notesConverter = new NotesCommandToNotesConverter();
	}
	@Test
	public void testNullParameter() {
		assertNull(notesConverter.convert(null));
	}
	@Test
	public void testEmptyObject() {
		assertNotNull(notesConverter.convert(new NotesCommand()));
	}
	@Test
	void testConvert() {
		NotesCommand  notesCommand = new NotesCommand();
		notesCommand.setId(ID);
		notesCommand.setRecipeNote(NOTES);
		
		Notes notes = notesConverter.convert(notesCommand);
		assertNotNull(notes);
		assertEquals(ID, notes.getId());
		assertEquals(NOTES, notes.getRecipeNote());

	}


}
