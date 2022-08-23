package de.saschakiefer.van4life.web.rest.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampsiteCreationRequestDTO {
	private UUID id;
	@NotNull(message = "Campsite name is required")
	private String name;
	private Address address = new Address();
	private Position position = new Position();
	private String homepage;
	private String comment;
	@NotNull(message = "Campsite type is required")
	@Pattern(regexp = "^(CS|SP)", message = "Campsite type must be one of [CS,SP]")
	private String type;
	private int rating;
	private Set<LocalDate> visits = new TreeSet<>();
}
