package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class indexControlerTest {
	@Mock
	RecipeService reciepService;
	@Mock
	Model model;
	indexControler controller;
	@Before
	public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);	
		controller = new indexControler(reciepService);
	}
	@Test
	public void testMockMVC() throws Exception{
		MockMvc mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
		mockMVC.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	

	@Test
	public void getIndexPage() {
		
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		when(reciepService.getRecipes()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		String viewName = controller.getIndexPage(model);
		assertEquals("index", viewName);
		verify(reciepService, times(1)).getRecipes();
		//verify(model, times(1)).addAttribute("recipes", argumentCaptor.capture());
		
	}

}
