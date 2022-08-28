package de.saschakiefer.van4life.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.saschakiefer.van4life.domain.entity.Campsite;

public interface CampsiteService {
	Optional<Campsite> findCampsiteById(UUID id);

	Campsite persistCampsite(Campsite campsite);

	List<Campsite> findAllCampsites();
}
