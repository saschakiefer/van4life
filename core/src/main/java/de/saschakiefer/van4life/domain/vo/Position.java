package de.saschakiefer.van4life.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public final class Position {
	private Double latitude;
	private Double longitude;

	@Column(columnDefinition = "geometry(Point,4326)")
	private Point coordinates;

	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		updateCoordinate();
	}

	@PrePersist
	@PreUpdate
	public void updateCoordinate() {
		GeometryFactory geometryFactory = new GeometryFactory();

		if (latitude == null || longitude == null) {
			this.coordinates = null;
		} else {
			this.coordinates = geometryFactory.createPoint(new Coordinate(longitude, latitude));
		}
	}
}
