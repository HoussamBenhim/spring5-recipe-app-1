package guru.springframework.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.swing.text.View;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

class RecipeControllerTest {
	
	@Mock
	RecipeService recipeService;
	@InjectMocks
	RecipeController recipeController;
	
	MockMvc mockMcv;
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMcv = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	void testShowById() throws Exception {
		Long id=1L;
		Recipe recipe = new Recipe();
		recipe.setId(id);
		when(recipeService.findById(id)).thenReturn(recipe);
		mockMcv.perform(get("/recipe/show/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipes/show"));
	}

}
