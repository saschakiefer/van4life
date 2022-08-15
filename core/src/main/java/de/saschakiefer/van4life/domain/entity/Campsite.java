package de.saschakiefer.van4life.domain.entity;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import de.saschakiefer.van4life.domain.vo.Visit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Campsite extends BaseEntity{
	private UUID id;

	private String name;

	private Address address;

	private Position position;

	private String homepage;

	@Builder.Default
	private Set<Visit> visits = new TreeSet<>();
}
