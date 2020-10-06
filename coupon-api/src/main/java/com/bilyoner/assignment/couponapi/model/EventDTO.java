package com.bilyoner.assignment.couponapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.bilyoner.assignment.couponapi.entity.Event;
import com.bilyoner.assignment.couponapi.entity.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventDTO implements Serializable {

    private Long id;

    @NotNull
	private String name;

	@NotNull
	private Integer mbs;

	@NotNull
	private EventType type;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime eventDate;

	public static EventDTO mapToEventDTO(Event event) {
		return EventDTO.builder()
				.id(event.getId())
				.name(event.getName())
				.mbs(event.getMbs())
				.type(event.getType())
				.eventDate(event.getEventDate())
				.build();
	}
}
