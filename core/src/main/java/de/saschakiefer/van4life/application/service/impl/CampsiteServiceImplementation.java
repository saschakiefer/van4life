package de.saschakiefer.van4life.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.saschakiefer.van4life.application.adapter.CampsiteManagementAdapter;
import de.saschakiefer.van4life.application.service.CampsiteService;
import de.saschakiefer.van4life.domain.entity.Campsite;

@Service
public class CampsiteServiceImplementation implements CampsiteService {
	@Autowired
	CampsiteManagementAdapter campsiteManagementAdapter;

	@Override
	public Optional<Campsite> findCampsiteById(UUID id) {
		return campsiteManagementAdapter.findCampsiteById(id);
	}

	@Override
	public Campsite persistCampsite(Campsite campsite) {
		return campsiteManagementAdapter.persistCampsite(campsite);
	}

	@Override
	public List<Campsite> findAllCampsites() {
		return campsiteManagementAdapter.findAllCampsites();
	}
}
