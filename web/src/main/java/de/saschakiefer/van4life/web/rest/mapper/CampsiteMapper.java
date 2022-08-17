package de.saschakiefer.van4life.web.rest.mapper;


import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.domain.entity.Visit;
import de.saschakiefer.van4life.web.rest.dto.CampsiteResponseDTO;

public class CampsiteMapper {
	public static final CampsiteResponseDTO CampsiteToCampsiteResponseDto(Campsite campsite) {
		ModelMapper mapper = new ModelMapper();

		Converter<Set<Visit>, Set<LocalDate>> visitsConverter = new AbstractConverter<Set<Visit>, Set<LocalDate>>() {
			@Override
			protected Set<LocalDate> convert(Set<Visit> visits) {
				return visits.stream().map(visit -> visit.getDate()).collect(Collectors.toSet());
			}
		};
		mapper.addConverter(visitsConverter);

		CampsiteResponseDTO campsiteResponseDTO = mapper.map(campsite, CampsiteResponseDTO.class);
		return campsiteResponseDTO;
	}
}
