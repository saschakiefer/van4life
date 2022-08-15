package de.saschakiefer.van4life.persistence.dao;

import java.sql.Timestamp;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "campsite")
public class CampsiteDao {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private UUID id;

	@Column(name = "name")
	private String name;

	@Embedded
	private AddressDao address;

	@Column(name = "homepage")
	private String homepage;

	@Embedded
	private PositionDao position;

	@OneToMany(mappedBy = "campsiteDao", fetch = FetchType.EAGER)
	@Builder.Default
	private Set<VisitDao> visits = new TreeSet<>();

	@CreationTimestamp
	@Column(name = "creation_datetime")
	private Timestamp creationDateTime;

	@UpdateTimestamp
	@Column(name = "update_datetime")
	private Timestamp updateDateTime;

	public void addToVisits(VisitDao visit){
		visit.setCampsiteDao(this);
		this.visits.add(visit);
	}
}
