package de.saschakiefer.van4life.application.service.impl;

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
	public Optional<Campsite> readCampsite(UUID id) {
		return campsiteManagementAdapter.readCampsite(id);
	}
}
