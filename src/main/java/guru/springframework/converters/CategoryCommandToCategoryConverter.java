package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand, Category>{
	


	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if(source == null) {
			return null;
		}
		Category cat = new Category();
		cat.setId(source.getId());
		cat.setDescription(source.getDescription());

		return cat;
	}
	
}
