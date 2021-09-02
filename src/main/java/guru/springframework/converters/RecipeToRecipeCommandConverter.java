package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
@Component
public class RecipeToRecipeCommandConverter implements Converter<Recipe, RecipeCommand>{
	
	private final CategoryToCategoryCommandConverter categoryConverter;
	private final IngredientToIngredientCommandConverter ingredientConverter;
	private final NotesToNotesCommandConverter notesConverter;
		
	public RecipeToRecipeCommandConverter(CategoryToCategoryCommandConverter categoryConverter,
			IngredientToIngredientCommandConverter ingredientConverter, NotesToNotesCommandConverter notesConverter) {
		super();
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}


	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if(source == null) {
			return null;
		}
		RecipeCommand recipeCom = new RecipeCommand();
		recipeCom.setId(source.getId());
		
		recipeCom.setCookTime(source.getCookTime());
		recipeCom.setDescription(source.getDescription());
		recipeCom.setDifficulty(source.getDifficulty());
		recipeCom.setDirections(source.getDirections());
		recipeCom.setImage(source.getImage());
		recipeCom.setNotesCommand(notesConverter.convert(source.getNotes()));
		recipeCom.setPrepTime(source.getPrepTime());
		recipeCom.setServings(source.getServings());
		recipeCom.setSource(source.getSource());
		recipeCom.setUrl(source.getUrl());
		if(source.getCategories()!=null && source.getCategories().size()>0) {
			source.getCategories().stream().forEach(category ->recipeCom.getCategoriesCommands().add(categoryConverter.convert(category)));
		}
		if(source.getIngredient()!= null && source.getIngredient().size()>0) {
			source.getIngredient().stream().forEach(ingredient-> recipeCom.getIngredientsCommands().add(ingredientConverter.convert(ingredient)));
		}
		return recipeCom;
	}
	
}
