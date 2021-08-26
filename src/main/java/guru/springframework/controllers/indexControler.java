package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class indexControler {
	private final RecipeService reciepService;

	public indexControler(RecipeService reciepService) {
		super();
		this.reciepService = reciepService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {
		log.debug("recipes controller");
		model.addAttribute("recipes", reciepService.getRecipes());
		return "index";

	}

}
