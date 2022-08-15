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

import de.saschakiefer.van4life.Application;
import de.saschakiefer.van4life.application.adapter.CampsiteManagement;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.persistence.dao.AddressDao;
import de.saschakiefer.van4life.persistence.dao.CampsiteDao;
import de.saschakiefer.van4life.persistence.dao.PositionDao;
import de.saschakiefer.van4life.persistence.dao.VisitDao;
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
	CampsiteManagement campsiteManagement;

	@Test
	void readCampsite() {
		CampsiteDao campsiteDao = CampsiteDao.builder()
				.name("Test Campsite")
				.address(new AddressDao("Doe Stree", "12345", "Doe City", "DE"))
				.homepage("https://www.example.com")
				.position(new PositionDao(1.234, 4.321))
				.updateDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.creationDateTime(Timestamp.valueOf(LocalDateTime.now()))
				.build();

		campsiteDao.addToVisits(VisitDao.builder().date(LocalDate.of(2022,1,1)).build());
		campsiteDao.addToVisits(VisitDao.builder().date(LocalDate.of(2022,2,1)).build());

		campsiteRepository.save(campsiteDao);
		visitRepository.saveAll(campsiteDao.getVisits());
		log.info("Test campsite with if '{}' generated", campsiteDao.getId());

		Optional<Campsite> campsite = campsiteManagement.readCampsite(campsiteDao.getId());
		assertThat(campsite.isPresent(), is(true));

		assertThat(campsite.get().getVisits().size(), is(2));

	}
}