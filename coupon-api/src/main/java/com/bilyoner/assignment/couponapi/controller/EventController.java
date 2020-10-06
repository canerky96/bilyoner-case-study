package com.bilyoner.assignment.couponapi.controller;

import com.bilyoner.assignment.couponapi.model.EventDTO;
import com.bilyoner.assignment.couponapi.service.EventService;
import com.bilyoner.assignment.couponapi.model.ApiBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ApiBaseResponse<List<EventDTO>> getAllEvents() {
        return ApiBaseResponse.<List<EventDTO>>builder()
                .operationResultData(eventService.getAllEvents())
                .build();
    }

    @PostMapping
    public ApiBaseResponse<EventDTO> createEvent(@RequestBody @Valid EventDTO eventRequest) {
        return ApiBaseResponse.<EventDTO>builder()
                .operationResultData(eventService.createEvent(eventRequest))
                .build();
    }

}
