package de.saschakiefer.van4life.domain.vo;

import java.time.LocalDate;
import java.util.UUID;

public record Visit(UUID id, LocalDate date) implements Comparable<Visit>{
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Visit visit = (Visit) o;

		return date.equals(visit.date);
	}

	@Override
	public int hashCode() {
		return date.hashCode();
	}

	@Override
	public int compareTo(Visit v) {
		return date.compareTo(v.date);
	}
}
