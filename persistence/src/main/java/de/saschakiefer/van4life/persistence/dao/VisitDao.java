package de.saschakiefer.van4life.persistence.dao;

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
public class VisitDao implements Comparable<VisitDao>{
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "campsite_id")
	private CampsiteDao campsiteDao;

	@Column(name = "date")
	LocalDate date;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VisitDao visitDao = (VisitDao) o;

		return date.equals(visitDao.date);
	}

	@Override
	public int hashCode() {
		return date.hashCode();
	}

	@Override
	public int compareTo(VisitDao o) {
		return date.compareTo(o.date);
	}
}