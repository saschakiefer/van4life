package de.saschakiefer.van4life.application.service.impl;

import de.saschakiefer.van4life.application.adapter.TrackManagementAdapter;
import de.saschakiefer.van4life.application.service.TrackService;
import de.saschakiefer.van4life.domain.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrackServiceImplementation implements TrackService {
    @Autowired
    TrackManagementAdapter trackManagementAdapter;

    @Override
    public Track persistTrack(Track track) {
        return trackManagementAdapter.persistTrack(track);
    }

    @Override
    public List<Track> findAllTracks() {
        return trackManagementAdapter.findAllTracks();
    }

    @Override
    public Optional<Track> findTrackById(UUID id) {
        return trackManagementAdapter.findTrackById(id);
    }
}
