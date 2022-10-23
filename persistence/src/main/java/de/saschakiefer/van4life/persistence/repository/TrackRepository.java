package de.saschakiefer.van4life.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.saschakiefer.van4life.domain.entity.Track;


@Repository
public interface TrackRepository extends JpaRepository<Track, UUID> {
}
