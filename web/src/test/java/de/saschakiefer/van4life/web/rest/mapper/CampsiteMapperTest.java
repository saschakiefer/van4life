package de.saschakiefer.van4life.web.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.domain.entity.Visit;
import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.CampsiteType;
import de.saschakiefer.van4life.domain.vo.Position;
import de.saschakiefer.van4life.web.rest.dto.CampsiteResponseDTO;

class CampsiteMapperTest {
	@Test
	void CampsiteToCampsiteResponseDto_returnsValidDto() {
		Campsite campsite = Campsite.builder()
				.id(UUID.fromString("c1e0e90b-a9af-4a80-8ce3-dae881af97c4"))
				.name("Test Campsite")
				.address(new Address("Doe Stree", "12345", "Doe City", "DE"))
				.homepage("https://www.example.com")
				.position(new Position(1.234, 4.321))
				.updateDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.creationDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.comment("TestComment")
				.rating(3)
				.build();

		campsite.addToVisits(Visit.builder().date(LocalDate.of(2022, 1, 1)).build());
		campsite.addToVisits(Visit.builder().date(LocalDate.of(2022, 2, 1)).build());

		CampsiteResponseDTO campsiteResponseDTO = CampsiteMapper.CampsiteToCampsiteResponseDto(campsite);
		assertThat(campsiteResponseDTO, is(notNullValue()));
		assertThat(campsiteResponseDTO.getVisits().size(), is(2));

		assertThat(campsiteResponseDTO.getType(), is(CampsiteType.CS));
		assertThat(campsiteResponseDTO.getRating(), is(3));
	}

}