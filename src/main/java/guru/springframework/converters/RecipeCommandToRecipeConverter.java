package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeCommandToRecipeConverter implements Converter<RecipeCommand,Recipe>{

		private final CategoryCommandToCategoryConverter categoryConverter;
		private final IngredientCommandToIngredientConverter ingredientConverter;
		private final NotesCommandToNotesConverter notesConverter;
		public RecipeCommandToRecipeConverter(CategoryCommandToCategoryConverter categoryConverter,
				IngredientCommandToIngredientConverter ingredientConverter,
				NotesCommandToNotesConverter notesConverter) {
			super();
			this.categoryConverter = categoryConverter;
			this.ingredientConverter = ingredientConverter;
			this.notesConverter = notesConverter;
		}


		@Synchronized
		@Nullable
		@Override
		public Recipe convert(RecipeCommand source) {
			if(source == null) {
				return null;
			}
			Recipe recipe = new Recipe();
			recipe.setId(source.getId());
			recipe.setCookTime(source.getCookTime());
			recipe.setDescription(source.getDescription());
			recipe.setDifficulty(source.getDifficulty());
			recipe.setDirections(source.getDirections());
			recipe.setImage(source.getImage());
			recipe.setNotes(notesConverter.convert(source.getNotesCommand()));
			recipe.setPrepTime(source.getPrepTime());
			recipe.setServings(source.getServings());
			recipe.setSource(source.getSource());
			recipe.setUrl(source.getUrl());
			
			if(source.getCategoriesCommands()!=null && source.getCategoriesCommands().size()>0) {
				source.getCategoriesCommands().stream().forEach(categoryCommand ->recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
			}
			if(source.getIngredientsCommands()!= null && source.getIngredientsCommands().size()>0) {
				source.getIngredientsCommands().stream().forEach(ingredientCommand-> recipe.getIngredient().add(ingredientConverter.convert(ingredientCommand)));
			}
			return recipe;
		}
}
