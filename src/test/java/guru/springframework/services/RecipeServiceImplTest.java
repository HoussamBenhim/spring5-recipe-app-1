package guru.springframework.services;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipeConverter;
import guru.springframework.converters.RecipeToRecipeCommandConverter;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.mockito.Matchers;

public class RecipeServiceImplTest {
	private final Long ID = 1L;
	private final String DESCRIPTION = "description";
	RecipeServiceImpl recipeService;
	@Mock
	RecipeRepository recipeRepository;
	@Mock
	RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;
	@Mock
	RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipeConverter,
				recipeToRecipeCommandConverter);

	}

	@Test
	public void getRecipes() {
		Recipe recipe = new Recipe();
		HashSet recipeData = new HashSet();
		recipeData.add(recipe);
		when(recipeRepository.findAll()).thenReturn(recipeData);
		Set<Recipe> recipes = (Set<Recipe>) recipeRepository.findAll();
		assertEquals(1, recipes.size());
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	public void testfindById() {
		Long id = 1l;
		Recipe recipe = new Recipe();
		recipe.setId(id);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		when(recipeRepository.findById(id)).thenReturn(recipeOptional);
		Recipe returnedRecipe = recipeService.findById(id);
		assertNotNull(returnedRecipe);
		verify(recipeRepository, times(1)).findById(id);
		verify(recipeRepository, never()).findAll();
	}
	@Test
	public void testSaveRecipeCommand() {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(ID);
		recipeCommand.setDescription(DESCRIPTION);
		Recipe recipe = new Recipe();
		recipe.setId(ID);
		recipe.setDescription(DESCRIPTION);
		
	
	}
	
	@Test
	public void testDeleteById() {
		Long idToDelete = Long.valueOf(2l);
		recipeService.deletById(idToDelete);
		verify(recipeRepository, times(1)).deleteById(idToDelete);
		
	}
}
