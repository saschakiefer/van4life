package de.saschakiefer.van4life.web.rest.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.saschakiefer.van4life.application.service.CampsiteService;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.web.rest.dto.CampsiteResponseDTO;
import de.saschakiefer.van4life.web.rest.mapper.CampsiteMapper;

@RestController
@RequestMapping("/api/v1/campsite")
public class CampsiteController {
	@Autowired
	CampsiteService campsiteService;

	@GetMapping("/{id}")
	public ResponseEntity<CampsiteResponseDTO> findById(@PathVariable String id) {
		Optional<Campsite> campsite = campsiteService.readCampsite(UUID.fromString(id));

		if (campsite.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CampsiteResponseDTO>(
				CampsiteMapper.CampsiteToCampsiteResponseDto(campsite.get()),
				HttpStatus.OK
		);
	}
}
