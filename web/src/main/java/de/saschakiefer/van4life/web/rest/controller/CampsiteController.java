package de.saschakiefer.van4life.web.rest.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.saschakiefer.van4life.application.service.CampsiteService;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.web.rest.dto.CampsiteCreationRequestDTO;
import de.saschakiefer.van4life.web.rest.dto.CampsiteResponseDTO;
import de.saschakiefer.van4life.web.rest.mapper.CampsiteMapper;

@RestController
@RequestMapping("/api/v1/campsites")
@Validated
public class CampsiteController {
	@Autowired
	CampsiteService campsiteService;

	@GetMapping("/{id}")
	public ResponseEntity<CampsiteResponseDTO> findById(@PathVariable String id) {
		Optional<Campsite> campsite = campsiteService.findCampsiteById(UUID.fromString(id));

		if (campsite.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
				CampsiteMapper.CampsiteToCampsiteResponseDto(campsite.get()),
				HttpStatus.OK
		);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CampsiteResponseDTO> create(@RequestBody final @Valid CampsiteCreationRequestDTO campsiteRequest) {
		Campsite campsite = CampsiteMapper.CampsiteCreationRequestDTOToCampsite(campsiteRequest);

		campsite = campsiteService.persistCampsite(campsite);

		return new ResponseEntity<>(CampsiteMapper.CampsiteToCampsiteResponseDto(campsite),
				HttpStatus.CREATED);
	}
}
