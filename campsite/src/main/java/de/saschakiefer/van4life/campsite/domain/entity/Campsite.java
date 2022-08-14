package de.saschakiefer.van4life.campsite.domain.entity;

import java.util.UUID;

import de.saschakiefer.van4life.campsite.domain.vo.Address;
import de.saschakiefer.van4life.campsite.domain.vo.Position;
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

}
