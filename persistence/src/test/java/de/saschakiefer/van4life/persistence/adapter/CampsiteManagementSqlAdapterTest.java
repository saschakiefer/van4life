package de.saschakiefer.van4life.persistence.adapter;

import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.test.context.ActiveProfiles;

import de.saschakiefer.van4life.Application;
import de.saschakiefer.van4life.application.adapter.CampsiteManagement;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.domain.entity.Visit;
import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import de.saschakiefer.van4life.persistence.repository.CampsiteRepository;
import de.saschakiefer.van4life.persistence.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("psql")
class CampsiteManagementSqlAdapterTest {
	@Resource
	CampsiteRepository campsiteRepository;

	@Resource
	VisitRepository visitRepository;

	@Autowired
	CampsiteManagement campsiteManagement;

	@Test
	void readCampsite() {
		Campsite campsiteDao = Campsite.builder()
				.name("Test Campsite")
				.address(new Address("Doe Stree", "12345", "Doe City", "DE"))
				.homepage("https://www.example.com")
				.position(new Position(1.234, 4.321))
				.updateDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.creationDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.build();

		campsiteDao.addToVisits(Visit.builder().date(LocalDate.of(2022, 1, 1)).build());
		campsiteDao.addToVisits(Visit.builder().date(LocalDate.of(2022, 2, 1)).build());

		campsiteRepository.save(campsiteDao);
		visitRepository.saveAll(campsiteDao.getVisits());
		log.info("Test campsite with if '{}' generated", campsiteDao.getId());

		Optional<Campsite> campsite = campsiteManagement.readCampsite(campsiteDao.getId());
		assertThat(campsite.isPresent(), is(true));

		assertThat(campsite.get().getVisits().size(), is(2));
	}
}