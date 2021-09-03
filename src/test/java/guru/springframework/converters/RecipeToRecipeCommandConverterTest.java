package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;

class RecipeToRecipeCommandConverterTest {

	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
	public static final Integer PREP_TIME = Integer.valueOf("7");
	public static final String DESCRIPTION = "My Recipe";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Integer SERVINGS = Integer.valueOf("3");
	public static final String SOURCE = "Source";
	public static final String URL = "Some URL";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;
	RecipeToRecipeCommandConverter recipeConverter;

	@BeforeEach
	void setUp() throws Exception {
		recipeConverter = new RecipeToRecipeCommandConverter(new CategoryToCategoryCommandConverter(),
				new IngredientToIngredientCommandConverter(new UnitOfMeasureToUnitOfMeasureCommandConverter()),
				new NotesToNotesCommandConverter());
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(recipeConverter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(recipeConverter.convert(new Recipe()));
	}

	@Test
	void testConvert() {
		// given
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID);
		recipe.setCookTime(COOK_TIME);
		recipe.setPrepTime(PREP_TIME);
		recipe.setDescription(DESCRIPTION);
		recipe.setDifficulty(DIFFICULTY);
		recipe.setDirections(DIRECTIONS);
		recipe.setServings(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);

		Notes notes = new Notes();
		notes.setId(NOTES_ID);

		recipe.setNotes(notes);

		Category category = new Category();
		category.setId(CAT_ID_1);

		Category category2 = new Category();
		category2.setId(CAT_ID2);

		recipe.getCategories().add(category);
		recipe.getCategories().add(category2);

		Ingredient ingredient = new Ingredient();
		ingredient.setId(INGRED_ID_1);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(INGRED_ID_2);

		recipe.getIngredient().add(ingredient);
		recipe.getIngredient().add(ingredient2);

		// when
		RecipeCommand recipeCommand = recipeConverter.convert(recipe);

		assertNotNull(recipeCommand);
		assertEquals(RECIPE_ID, recipeCommand.getId());
		assertEquals(COOK_TIME, recipeCommand.getCookTime());
		assertEquals(PREP_TIME, recipeCommand.getPrepTime());
		assertEquals(DESCRIPTION, recipeCommand.getDescription());
		assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
		assertEquals(DIRECTIONS, recipeCommand.getDirections());
		assertEquals(SERVINGS, recipeCommand.getServings());
		assertEquals(SOURCE, recipeCommand.getSource());
		assertEquals(URL, recipeCommand.getUrl());
		assertEquals(NOTES_ID, recipeCommand.getNotesCommand().getId());
		assertEquals(2, recipeCommand.getCategoriesCommands().size());
		assertEquals(2, recipeCommand.getIngredientsCommands().size());

	}

}
