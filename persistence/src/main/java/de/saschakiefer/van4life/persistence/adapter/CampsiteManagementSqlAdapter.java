package de.saschakiefer.van4life.persistence.adapter;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.saschakiefer.van4life.application.adapter.CampsiteManagementAdapter;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.persistence.repository.CampsiteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CampsiteManagementSqlAdapter implements CampsiteManagementAdapter {
	@Autowired
	CampsiteRepository campsiteRepository;

	@Override
	public Optional<Campsite> readCampsite(UUID id) {
		return campsiteRepository.findById(id);
	}
}
