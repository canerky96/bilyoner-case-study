package com.bilyoner.assignment.couponapi.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

	@Id
    @GeneratedValue
    private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer mbs;

	@Enumerated
	@Column(nullable = false)
	private EventType type;

	@Column(columnDefinition = "TIMESTAMP", nullable = false)
	private LocalDateTime eventDate;
}
