package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.tools.sjavac.Log;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipes/show";
	}
	@GetMapping
	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe",new RecipeCommand());
		return "recipes/recipeForm";
	}
	@GetMapping
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@ModelAttribute RecipeCommand recipeCommand,  Model model) {
		model.addAttribute("recipe",recipeService.findCommandById(recipeCommand.getId()) );
		return "recipes/recipeForm";
	}
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
		
	}
	
	@GetMapping
	@RequestMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id, Model model) {
		log.debug("Deleting id : "+ id);
		recipeService.deletById(Long.valueOf(id));
		return "redirect:/";
	}
}
