package de.saschakiefer.van4life.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
	@CreationTimestamp
	@Column(name = "creation_datetime")
	private Timestamp creationDateTime;

	@UpdateTimestamp
	@Column(name = "update_datetime")
	private Timestamp updateDateTime;
}
