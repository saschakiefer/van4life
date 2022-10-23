package de.saschakiefer.van4life.web.rest.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrackCreationRequestDTO {
	@NotNull(message = "Campsite name is required")
	private String name;
	@NotNull(message = "Timestamp is required")
	private String timestamp;
	@NotNull(message = "GeoJSON representation is required")
	private String geojson;
	@NotNull(message = "GPX file content is required")
	private String gpx;
}
