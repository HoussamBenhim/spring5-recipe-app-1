package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasureConverter implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand command) {
		if(command ==null) {
			return null;
		}
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(command.getId());
		uom.setUom(command.getUom());
		return uom;
	}
	
	
}
