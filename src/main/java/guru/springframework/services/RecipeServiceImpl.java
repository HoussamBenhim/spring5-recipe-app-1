package guru.springframework.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipeConverter;
import guru.springframework.converters.RecipeToRecipeCommandConverter;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;
	private final RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository
			,RecipeCommandToRecipeConverter recipeCommandToRecipeConverter,
			RecipeToRecipeCommandConverter recipeToRecipeCommandConverter) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipeConverter = recipeCommandToRecipeConverter;
		this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipesSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);
		return recipesSet;
	}

	@Override
	public Recipe findById(Long l) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(l);
		if(!optionalRecipe.isPresent()) {
			throw new RuntimeException("No reicpe with id of : "+ l + "found!");
		}
		return optionalRecipe.get();
	}
	
	 @Override
	 @Transactional
	 public RecipeCommand saveRecipeCommand(RecipeCommand command) {
	        Recipe detachedRecipe = recipeCommandToRecipeConverter.convert(command);

	        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
	        log.debug("Saved RecipeId:" + savedRecipe.getId());
	        return recipeToRecipeCommandConverter.convert(savedRecipe);
	 }
	
}
