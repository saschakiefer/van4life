package de.saschakiefer.van4life.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "visit")
public class Visit implements Comparable<Visit> {
	@Column(name = "date")
	LocalDate date;
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "campsite_id")
	private Campsite campsite;

	@Override
	public int compareTo(Visit visit) {
		return date.compareTo(visit.date);
	}
}
