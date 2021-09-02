package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
@Component
public class CategoryToCategoryCommandConverter implements Converter<Category, CategoryCommand>{
	
	private final RecipeToRecipeCommandConverter recipeConverter;
	
	public CategoryToCategoryCommandConverter(RecipeToRecipeCommandConverter recipeConverter) {
		super();
		this.recipeConverter = recipeConverter;
	}

	@Synchronized
	@Nullable	
	@Override
	public CategoryCommand convert(Category source) {
		if(source == null) {
			return null;
		}
		CategoryCommand catCommand = new CategoryCommand();
		catCommand.setId(source.getId());
		catCommand.setDescription(source.getDescription());
		if(source.getRecipes()!=null && source.getRecipes().size()>0) {
			source.getRecipes().stream().forEach(recipe-> catCommand.getRecipesCommand().add(recipeConverter.convert(recipe)));
		}
		return catCommand;
	}
	
}
