package guru.springframework.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
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

	
}
