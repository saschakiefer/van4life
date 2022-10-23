package de.saschakiefer.van4life.application.service;

import de.saschakiefer.van4life.domain.entity.Track;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrackService {
    Track persistTrack(Track track);

    List<Track> findAllTracks();

    Optional<Track> findTrackById(UUID id);
}
