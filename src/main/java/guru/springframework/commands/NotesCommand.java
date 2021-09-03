package guru.springframework.commands;

import javax.persistence.Lob;

import guru.springframework.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private String recipeNote;
	
}
