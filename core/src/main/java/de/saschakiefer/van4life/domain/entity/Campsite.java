package de.saschakiefer.van4life.domain.entity;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import de.saschakiefer.van4life.domain.vo.Address;
import de.saschakiefer.van4life.domain.vo.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "campsite")
public class Campsite extends BaseEntity{
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private UUID id;

	@Column(name = "name")
	private String name;

	@Embedded
	private Address address;

	@Embedded
	private Position position;

	@Column(name = "homepage")
	private String homepage;

	@Builder.Default
	@OneToMany(mappedBy = "campsite", fetch = FetchType.EAGER)
	private Set<Visit> visits = new TreeSet<>();

	public void addToVisits(Visit visit){
		visit.setCampsite(this);
		this.visits.add(visit);
	}
}
