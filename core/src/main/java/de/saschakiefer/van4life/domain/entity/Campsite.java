package de.saschakiefer.van4life.domain.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Campsite extends BaseEntity{
	private UUID id;

	private String name;

	private Address address;

	private Position position;

	@Builder.Default
	private Set<LocalDate> visited = new TreeSet<>();

}
