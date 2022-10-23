package de.saschakiefer.van4life.web.rest.controller;

import de.saschakiefer.van4life.application.service.TrackService;
import de.saschakiefer.van4life.domain.entity.Track;
import de.saschakiefer.van4life.web.rest.dto.TrackCreationRequestDTO;
import de.saschakiefer.van4life.web.rest.dto.TrackResponseDTO;
import de.saschakiefer.van4life.web.rest.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tracks")
@Validated
public class TrackController {
    @Autowired
    TrackService trackService;

    @GetMapping("/{id}")
    public ResponseEntity<TrackResponseDTO> findById(@PathVariable String id) {
        Optional<Track> track = trackService.findTrackById(UUID.fromString(id));

        if (track.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                TrackMapper.TrackToTrackResponseDto(track.get()),
                HttpStatus.OK
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackResponseDTO> create(@RequestBody final @Valid TrackCreationRequestDTO trackRequest) {
        Track track = TrackMapper.TrackCreationRequestDTOToTrack(trackRequest);

        track = trackService.persistTrack(track);

        return new ResponseEntity<>(TrackMapper.TrackToTrackResponseDto(track), HttpStatus.CREATED);
    }
}
