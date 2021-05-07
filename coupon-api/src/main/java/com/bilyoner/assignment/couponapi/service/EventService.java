package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.model.EventDTO;
import com.bilyoner.assignment.couponapi.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> getAllEvents() {
        return getEvents();
    }

    public List<EventDTO> getEvents() {
        return eventRepository.findAll()
                .stream()
                .map(EventDTO::mapToEventDTO)
                .collect(Collectors.toList());
    }

    public EventDTO createEvent(EventDTO eventRequest) {
        final EventEntity createdEventEntity = eventRepository.save(EventEntity.builder()
                .name(eventRequest.getName())
                .mbs(eventRequest.getMbs())
                .type(eventRequest.getType())
                .eventDate(eventRequest.getEventDate())
                .build());

        final EventDTO response = EventDTO.mapToEventDTO(createdEventEntity);

        return response;
    }

    public List<EventEntity> findByIdIn(Collection<Long> ids) {
        return eventRepository.findByIdIn(ids);
    }

}
