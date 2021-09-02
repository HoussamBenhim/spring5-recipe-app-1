package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationListenerMethodAdapter;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final CategoryRepository categoryRepository ;
	private final RecipeRepository  recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("data loaded");
	}
	
	private List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<>();
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUom("each");
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByUom("TableSpoon");
		if(!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		Optional<UnitOfMeasure> TeaspoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
		if(!TeaspoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		Optional<UnitOfMeasure> DashOptional = unitOfMeasureRepository.findByUom("Dash");
		if(!DashOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		Optional<UnitOfMeasure> PintOptional = unitOfMeasureRepository.findByUom("Pint");
		if(!DashOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		Optional<UnitOfMeasure> OunceOptional = unitOfMeasureRepository.findByUom("Ounce");
		if(!OunceOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found");
		}
		// get Optionals
		
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure TeaSpoonUom = TeaspoonUomOptional.get();
		UnitOfMeasure DashUom = DashOptional.get();
		UnitOfMeasure PintUom = PintOptional.get();
		UnitOfMeasure OunceUom = OunceOptional.get();
		
		//get Categories
		
		Optional<guru.springframework.domain.Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected category :");
		}
		guru.springframework.domain.Category americanCategory = americanCategoryOptional.get();
		Optional<guru.springframework.domain.Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected category :");
		}
		guru.springframework.domain.Category mexicanCategory = mexicanCategoryOptional.get();
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(5);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\r\n" + 
				"Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\r\n" + 
				"\r\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.");
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNote("Be careful handling chilis! If using, it\'s best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards");
		guacRecipe.setNotes(guacNotes);
		guacRecipe.addIngredient(new Ingredient("Rripe avocados", new BigDecimal(2), guacRecipe, eachUom));
		guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(5), guacRecipe, TeaSpoonUom));
		guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), guacRecipe, tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), guacRecipe, tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), guacRecipe, eachUom));
		guacRecipe.addIngredient(new Ingredient("Cliantro", new BigDecimal(2), guacRecipe,tableSpoonUom));
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		recipes.add(guacRecipe);
		Recipe rellenoRecipe = new Recipe();
		rellenoRecipe.setDescription("Chile Relleno Casserole");
		rellenoRecipe.setPrepTime(15);
		rellenoRecipe.setCookTime(10);
		rellenoRecipe.setDifficulty(Difficulty.EASY);
		rellenoRecipe.setDirections("Classic chiles rellenos are green chiles, blackened and outer skin removed, stuffed with either cheese or a pork sausage picadillo, dipped in batter, fried in oil, and often served with a thin tomato sauce.");
		Notes rellenoNotes = new Notes();
		rellenoNotes.setRecipeNote("Make-Ahead Skillet Baked Spaghetti! Assemble up to a day ahead and bake when ready. Made with yellow squash, cherry tomatoes, Parmesan, fontina cheese, and basil. Yum!");
		rellenoRecipe.setNotes(rellenoNotes);
		rellenoRecipe.addIngredient(new Ingredient("extra virgin olive oil",new BigDecimal(1),rellenoRecipe,tableSpoonUom ));
		rellenoRecipe.addIngredient(new Ingredient("chopped onion",new BigDecimal(1),rellenoRecipe, TeaSpoonUom ));
		rellenoRecipe.addIngredient(new Ingredient("garlic, minced",new BigDecimal(4),rellenoRecipe,PintUom ));
		rellenoRecipe.addIngredient(new Ingredient("tomatoes, whole or diced",new BigDecimal(1),rellenoRecipe,OunceUom ));
		rellenoRecipe.getCategories().add(americanCategory);
		recipes.add(rellenoRecipe);
		
		
		Recipe SpaghettiRecipe = new Recipe();
		SpaghettiRecipe.setDescription("Make-Ahead Skillet Baked Spaghetti");
		SpaghettiRecipe.setPrepTime(19);
		SpaghettiRecipe.setCookTime(20);
		SpaghettiRecipe.setDifficulty(Difficulty.MODERATE);
		SpaghettiRecipe.setDirections("Prep this skillet pasta in the morning before you leave – or even the night before -- and pop it in the oven when you're home from work, your kid's soccer practice, the last beach run of the season, or whatever activities have filled your day.");
		Notes SpaghettiNotes = new Notes();
		SpaghettiNotes.setRecipeNote("Our chile relleno casserole recipe is full of flavor thanks to Cotija cheese, chorizo, blackened green chiles, and a light tomato sauce. It’s a lot like the chiles rellenos you know and love...only easier!");
		SpaghettiRecipe.setNotes(SpaghettiNotes);
		SpaghettiRecipe.addIngredient(new Ingredient("spaghetti",new BigDecimal(1),rellenoRecipe,tableSpoonUom ));
		SpaghettiRecipe.addIngredient(new Ingredient("olive oil",new BigDecimal(1),rellenoRecipe, TeaSpoonUom ));
		SpaghettiRecipe.addIngredient(new Ingredient("Parmesan",new BigDecimal(4),rellenoRecipe,PintUom ));
		SpaghettiRecipe.addIngredient(new Ingredient("salt, divided",new BigDecimal(1),rellenoRecipe,OunceUom ));
		SpaghettiRecipe.getCategories().add(mexicanCategory);
		recipes.add(SpaghettiRecipe);
		
		return recipes;
	}

	
}
