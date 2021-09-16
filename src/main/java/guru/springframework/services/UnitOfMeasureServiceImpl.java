package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommandConverter;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	private final UnitOfMeasureRepository uomRepository;
	private final UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter;
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository,UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter) {
		super();
		this.uomRepository = uomRepository;
		this.uomConverter= uomConverter;
	}

	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		return  StreamSupport
				.stream(uomRepository.findAll().spliterator(), false)
				.map(uom-> uomConverter.convert(uom))
				.collect(Collectors.toSet());
	}
	
}		
