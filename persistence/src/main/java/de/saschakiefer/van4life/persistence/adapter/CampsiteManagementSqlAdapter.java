package de.saschakiefer.van4life.persistence.adapter;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.saschakiefer.van4life.application.adapter.CampsiteManagementAdapter;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.persistence.repository.CampsiteRepository;
import de.saschakiefer.van4life.persistence.repository.VisitRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CampsiteManagementSqlAdapter implements CampsiteManagementAdapter {
	@Autowired
	CampsiteRepository campsiteRepository;

	@Autowired
	VisitRepository visitRepository;

	@Override
	public Optional<Campsite> findCampsiteById(UUID id) {
		return campsiteRepository.findById(id);
	}

	@Override
	public Campsite persistCampsite(Campsite campsite) {
		Campsite dbCampsite = campsiteRepository.save(campsite);
		visitRepository.saveAll(campsite.getVisits());

		return dbCampsite;
	}

	@Override
	public List<Campsite> findAllCampsites() {
		return campsiteRepository.findAll();
	}
}
