package guru.springframework.commands;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Byte[] image;
	private Difficulty difficulty;
	private NotesCommand notesCommand;
	private Set<IngredientCommand> ingredientsCommands = new HashSet<>();
	private Set<CategoryCommand> categoriesCommands = new HashSet<>();
}
