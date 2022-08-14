package de.saschakiefer.van4life.framework.adapter.output.sql.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.saschakiefer.van4life.framework.adapter.output.sql.data.CampsiteData;

public interface CampsiteRepository extends JpaRepository<CampsiteData, UUID> {
}
