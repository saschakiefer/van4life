package de.saschakiefer.van4life.persistence.dao;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public final class AddressDao {
	private String street;
	private String zip;
	private String city;
	private String country;
}
