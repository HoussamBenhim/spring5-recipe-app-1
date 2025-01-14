package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;

@Component
public class NotesCommandToNotesConverter implements Converter<NotesCommand, Notes>{



	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if(source == null) {
			return null;
		}
		Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setRecipeNote(source.getRecipeNote());
		return notes;
	}
	
}
