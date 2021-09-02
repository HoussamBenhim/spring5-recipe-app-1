package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand, Category>{
	private final RecipeCommandToRecipeConverter recipeConverter;
	
	
	public CategoryCommandToCategoryConverter(RecipeCommandToRecipeConverter recipeConverter) {
		super();
		this.recipeConverter = recipeConverter;
	}


	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if(source == null) {
			return null;
		}
		Category cat = new Category();
		cat.setId(source.getId());
		cat.setDescription(source.getDescription());
		if(source.getRecipesCommand()!=null && source.getRecipesCommand().size()>0) {
			source.getRecipesCommand().stream().forEach(recipeCommand-> cat.getRecipes().add(recipeConverter.convert(recipeCommand)));
		}
		return cat;
	}
	
}
