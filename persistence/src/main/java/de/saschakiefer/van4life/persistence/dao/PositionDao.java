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
public final class PositionDao {
	private  double latitude;
	private  double longitude;
}