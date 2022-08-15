package de.saschakiefer.van4life.web.rest.controller;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import de.saschakiefer.van4life.domain.vo.Visit;

@RestController
@RequestMapping("/api/v1/campsite")
public class CampsiteController {

	@GetMapping("/{id}")
	public Campsite findById(@PathVariable String id){
		return Campsite.builder()
				.id(UUID.randomUUID())
				.name("HalloCamping")
				.address(new Address("Entenhausener Weg 26", "12345", "Entenhausen", Locale.GERMANY.getCountry()))
				.position(new Position(123.45, 543.21))
				.visits(Set.of(new Visit(UUID.randomUUID(), LocalDate.now())))
				.build();
	}
}
