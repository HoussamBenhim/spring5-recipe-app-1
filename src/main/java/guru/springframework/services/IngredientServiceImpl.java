package guru.springframework.services;

import java.lang.StackWalker.Option;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredientConverter;
import guru.springframework.converters.IngredientToIngredientCommandConverter;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
	private final RecipeRepository recipeRepository;
	private final IngredientToIngredientCommandConverter ingredientConverter;
	private final IngredientCommandToIngredientConverter ingredientCommandConverter;
	private final UnitOfMeasureRepository uomRepository;
	public IngredientServiceImpl(UnitOfMeasureRepository uomRepository,RecipeRepository recipeRepository,
			IngredientToIngredientCommandConverter ingredientConverter,IngredientCommandToIngredientConverter ingredientCommandConverter) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientConverter = ingredientConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.uomRepository = uomRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> returnedRecipe = recipeRepository.findById(recipeId);
		if (!returnedRecipe.isPresent()) {
			throw new RuntimeException("No recipe with id : " + recipeId + "is present!");
		}
		Recipe recipe = returnedRecipe.get();

		Optional<IngredientCommand> optionalIngredient = recipe.getIngredient().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientConverter.convert(ingredient)).findFirst();
		if (!optionalIngredient.isPresent()) {
			throw new RuntimeException("No ingredient whith id : " + ingredientId + "is present!");
		}
		return optionalIngredient.get();
	}

	@Transactional
	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		if (!recipeOptional.isPresent()) {
			log.error("Recipe not found for id: " + command.getRecipeId());
			return new IngredientCommand();
		}
		Recipe recipe = recipeOptional.get();
		
		Optional<Ingredient> ingredientOptional= recipe.getIngredient().stream().filter(ingredient-> ingredient.getId().equals(command.getId())).findFirst();
		
		if(!ingredientOptional.isPresent()) {
			recipe.addIngredient(ingredientCommandConverter.convert(command));
		}else {
			Ingredient ingredientFound = ingredientOptional.get();
			ingredientFound.setAmount(command.getAmount());
			ingredientFound.setDescirption(command.getDescirption());
			ingredientFound.setUom(uomRepository.findById(command.getUomCommand().getId()).orElseThrow(()-> new RuntimeException("NOT FOUND!")));
			
		}
		
		Recipe recipeSaved = recipeRepository.save(recipe);

		return ingredientConverter.convert( recipeSaved.getIngredient().stream().filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst().get());
	}

}
