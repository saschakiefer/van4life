package de.saschakiefer.van4life.application.adapter;

import java.util.Optional;
import java.util.UUID;

import de.saschakiefer.van4life.domain.entity.Campsite;

public interface CampsiteManagementAdapter {
	Optional<Campsite> readCampsite(UUID id);
}
