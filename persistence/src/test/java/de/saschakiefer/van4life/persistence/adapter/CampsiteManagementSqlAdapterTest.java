package de.saschakiefer.van4life.persistence.adapter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.saschakiefer.van4life.Application;
import de.saschakiefer.van4life.application.adapter.CampsiteManagementAdapter;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.domain.entity.Visit;
import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.CampsiteType;
import de.saschakiefer.van4life.domain.vo.Position;
import de.saschakiefer.van4life.persistence.repository.CampsiteRepository;
import de.saschakiefer.van4life.persistence.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = Application.class)
@Transactional
//@ActiveProfiles("psql")
class CampsiteManagementSqlAdapterTest {
	@Resource
	CampsiteRepository campsiteRepository;

	@Resource
	VisitRepository visitRepository;

	@Autowired
	CampsiteManagementAdapter campsiteManagement;

	@Test
	void readCampsite_campsiteExists_returnsCampsite() {
		Campsite campsiteDb = Campsite.builder()
				.name("Test Campsite")
				.address(new Address("Doe Stree", "12345", "Doe City", "DE"))
				.homepage("https://www.example.com")
				.position(new Position(1.234, 4.321))
				.updateDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.creationDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.comment("TestComment")
				.rating(3)
				.build();

		campsiteDb.addToVisits(Visit.builder().date(LocalDate.of(2022, 1, 1)).build());
		campsiteDb.addToVisits(Visit.builder().date(LocalDate.of(2022, 2, 1)).build());

		campsiteRepository.save(campsiteDb);
		visitRepository.saveAll(campsiteDb.getVisits());
		log.info("Test campsite with if '{}' generated", campsiteDb.getId());

		Optional<Campsite> campsite = campsiteManagement.findCampsiteById(campsiteDb.getId());
		assertThat(campsite.isPresent(), is(true));

		assertThat(campsite.get().getVisits().size(), is(2));
		assertThat(campsite.get().getComment(), is("TestComment"));

		assertThat(campsite.get().getType(), is(CampsiteType.CS));
		assertThat(campsite.get().getRating(), is(3));

		assertThat(campsite.get().getCreationDateTime(), is(notNullValue()));
		assertThat(campsite.get().getUpdateDateTime(), is(notNullValue()));
	}
}
