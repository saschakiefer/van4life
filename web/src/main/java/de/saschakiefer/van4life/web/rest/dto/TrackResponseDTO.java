package de.saschakiefer.van4life.web.rest.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrackResponseDTO {
	private UUID id;
	private String name;
	private Timestamp timestamp;
	private String geojson;
	private String gpx;
	private Timestamp creationDateTime;
	private Timestamp updateDateTime;
}
