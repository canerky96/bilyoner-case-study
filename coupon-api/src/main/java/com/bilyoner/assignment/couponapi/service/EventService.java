package com.bilyoner.assignment.couponapi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bilyoner.assignment.couponapi.entity.Event;
import com.bilyoner.assignment.couponapi.model.EventDTO;
import com.bilyoner.assignment.couponapi.repository.EventRepository;
import com.bilyoner.assignment.couponapi.config.HazelcastCacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;
	private final HazelcastInstance hazelcastInstance;

	public List<EventDTO> getAllEvents() {
		return getEvents();
	}

	public List<EventDTO> getEvents() {
		return (List<EventDTO>) hazelcastInstance.getMap(HazelcastCacheConfig.EVENT_LIST)
				.computeIfAbsent("ALL", event -> {
					return eventRepository.findAll().stream()
							.map(EventDTO::mapToEventDTO)
							.collect(Collectors.toList());
				});
	}

	@Transactional
	public EventDTO createEvent(EventDTO eventRequest) {

		Event createdEvent = eventRepository.save(Event.builder()
				.name(eventRequest.getName())
				.mbs(eventRequest.getMbs())
				.type(eventRequest.getType())
				.eventDate(eventRequest.getEventDate())
				.build());

		EventDTO response = EventDTO.mapToEventDTO(createdEvent);
		putEvent(response.getId(), response);

		return response;
	}

	public EventDTO putEvent(Long key, EventDTO event) {
		// evict event list
		hazelcastInstance.getMap(HazelcastCacheConfig.EVENT_LIST).evictAll();

		IMap<Long, EventDTO> map = hazelcastInstance.getMap(HazelcastCacheConfig.EVENTS);
		return map.put(key, event);
	}

}
