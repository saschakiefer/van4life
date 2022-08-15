package de.saschakiefer.van4life.persistence.mapper;

import java.util.TreeSet;
import java.util.stream.Collectors;

import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import de.saschakiefer.van4life.domain.vo.Visit;
import de.saschakiefer.van4life.persistence.dao.CampsiteDao;

public class CampsiteMapper {

	public static Campsite campsiteDataToDomain(CampsiteDao campsiteData) {
		TreeSet<Visit> visits = new TreeSet<>(campsiteData.getVisits()
				.stream()
				.map(VisitMapper::visitDataToDomain)
				.collect(Collectors.toSet()));

		return Campsite.builder()
				.id(campsiteData.getId())
				.name(campsiteData.getName())
				.address(new Address(
						campsiteData.getAddress().getStreet(),
						campsiteData.getAddress().getZip(),
						campsiteData.getAddress().getCity(),
						campsiteData.getAddress().getCountry()))
				.homepage(campsiteData.getHomepage())
				.position(new Position(
						campsiteData.getPosition().getLatitude(),
						campsiteData.getPosition().getLongitude()))
				.visits(visits)
				.creationDateTime(campsiteData.getCreationDateTime())
				.updateDateTime(campsiteData.getUpdateDateTime())
				.build();
	}
}
