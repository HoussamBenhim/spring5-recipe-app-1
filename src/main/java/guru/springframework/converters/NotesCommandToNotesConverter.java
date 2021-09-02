package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;

@Component
public class NotesCommandToNotesConverter implements Converter<NotesCommand, Notes>{
	
	private final RecipeCommandToRecipeConverter recipeConverter;
	
	public NotesCommandToNotesConverter(RecipeCommandToRecipeConverter recipeConverter) {
		super();
		this.recipeConverter = recipeConverter;
	}

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
		notes.setRecipe(recipeConverter.convert(source.getRecipeCommand()));
		return notes;
	}
	
}
