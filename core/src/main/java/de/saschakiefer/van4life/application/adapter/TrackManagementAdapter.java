package de.saschakiefer.van4life.application.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.saschakiefer.van4life.domain.entity.Track;

public interface TrackManagementAdapter {
	Optional<Track> findTrackById(UUID id);

	Track persistTrack(Track track);

	List<Track> findAllTracks();
}
