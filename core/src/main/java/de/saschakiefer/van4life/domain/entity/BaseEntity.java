package de.saschakiefer.van4life.domain.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseEntity {
	private Timestamp creationDateTime;
	private Timestamp updateDateTime;
}
