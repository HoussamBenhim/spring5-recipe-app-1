package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;


@Component
public class UnitOfMeasureToUnitOfMeasureCommandConverter implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
			if(source ==null) {
				return null;
			}
			UnitOfMeasureCommand  uomc = new UnitOfMeasureCommand();
			uomc.setId(source.getId());
			uomc.setUomCommand(source.getUom());
		return uomc;
	}
	
}
