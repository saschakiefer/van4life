package de.saschakiefer.van4life.persistence.adapter;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.saschakiefer.van4life.application.adapter.TrackManagementAdapter;
import de.saschakiefer.van4life.domain.entity.Track;
import de.saschakiefer.van4life.persistence.repository.TrackRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class TrackManagementSqlAdapter implements TrackManagementAdapter {
	@Autowired
	TrackRepository trackRepository;

	@Override
	public Optional<Track> findTrackById(UUID id) {
		return trackRepository.findById(id);
	}

	@Override
	public Track persistTrack(Track track) {
		return trackRepository.save(track);
	}

	@Override
	public List<Track> findAllTracks() {
		return trackRepository.findAll();
	}
}
