package de.saschakiefer.van4life.campsite.framework.adapter.input.web;

import java.util.Locale;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.saschakiefer.van4life.campsite.domain.entity.Campsite;
import de.saschakiefer.van4life.campsite.domain.vo.Address;
import de.saschakiefer.van4life.campsite.domain.vo.Position;

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
				.build();
	}
}
