package com.bilyoner.assignment.couponapi.service;

import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.model.EventDTO;
import com.bilyoner.assignment.couponapi.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    public void shouldGetAllEvents() {
        //given
        EventEntity eventEntity = EventEntity.builder().id(1L).build();

        //when
        when(eventRepository.findAll()).thenReturn(Collections.singletonList(eventEntity));

        List<EventDTO> result = eventService.getAllEvents();

        //verify
        assertEquals(eventEntity.getId(), result.get(0).getId());
    }

    @Test
    public void shouldCreateEvent() {
        //given
        EventEntity eventEntity = EventEntity.builder().name("event").build();
        EventDTO eventRequest = EventDTO.builder().name("event").build();
        ArgumentCaptor<EventEntity> argumentCaptor = ArgumentCaptor.forClass(EventEntity.class);

        //when
        when(eventRepository.save(any())).thenReturn(eventEntity);

        eventService.createEvent(eventRequest);

        //verify
        verify(eventRepository).save(argumentCaptor.capture());
        assertEquals(eventRequest.getName(), argumentCaptor.getValue().getName());
    }

    @Test
    public void shouldFindByIdIn() {
        //given
        List<Long> ids = Collections.singletonList(1L);

        eventService.findByIdIn(ids);
        //verify
        verify(eventRepository).findByIdIn(ids);
    }
}
