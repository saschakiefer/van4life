package de.saschakiefer.van4life.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.saschakiefer.van4life.persistence.data.CampsiteData;

public interface CampsiteRepository extends JpaRepository<CampsiteData, UUID> {
}
