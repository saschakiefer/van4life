package de.saschakiefer.van4life.application.service;

import java.util.Optional;
import java.util.UUID;

import de.saschakiefer.van4life.domain.entity.Campsite;

public interface CampsiteService {
	Optional<Campsite> readCampsite(UUID id);
}
