package de.saschakiefer.van4life.web.rest.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.CampsiteType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CampsiteResponseDTO {
	private UUID id;
	private String name;
	private Address address;
	private PositionResponseDTO position;
	private String homepage;
	private String comment;
	private Set<LocalDate> visits = new TreeSet<>();
	private CampsiteType type;
	private int rating;
	private Timestamp creationDateTime;
	private Timestamp updateDateTime;
}
