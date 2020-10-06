package com.bilyoner.assignment.couponapi.util;

import com.bilyoner.assignment.couponapi.entity.Event;
import com.bilyoner.assignment.couponapi.entity.EventType;
import com.bilyoner.assignment.couponapi.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBInitializerService {

    private final EventRepository eventRepository;

    /**
     * Populates sample events information for different sport and mbs types.
     */
    @PostConstruct
    private void initDb() {
        LocalDateTime now = LocalDateTime.now()
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        if (eventRepository.count() <= 0) {
            createFootballEvents(now);
            createBasketballEvents(now);
            createTennisEvents(now);
        }

    }

    private void createFootballEvents(LocalDateTime time) {
        // football mbs3
        Event football1 = Event.builder()
                .name("Beşiktaş-Fenerbahçe")
                .mbs(3)
                .type(EventType.FOOTBALL)
                .eventDate(time.plusHours(1l))
                .build();
        // football mbs2
        Event football2 = Event.builder()
                .name("Göztepe-Alanyaspor")
                .mbs(2)
                .type(EventType.FOOTBALL)
                .eventDate(time.plusHours(2l))
                .build();
        Event football3 = Event.builder()
                .name("Antalyaspor-Kayserispor")
                .mbs(2)
                .type(EventType.FOOTBALL)
                .eventDate(time.plusHours(2l))
                .build();
        Event football4 = Event.builder()
                .name("Hatayspor-Erzurumspor")
                .mbs(2)
                .type(EventType.FOOTBALL)
                .eventDate(time.minusHours(2l))
                .build();
        Event football5 = Event.builder()
                .name("Sivasspor-Denizlispor")
                .mbs(2)
                .type(EventType.FOOTBALL)
                .eventDate(time.minusHours(2l))
                .build();
        // football mbs1
        Event football6 = Event.builder()
                .name("Konyaspor-Ankaragücü")
                .mbs(1)
                .type(EventType.FOOTBALL)
                .eventDate(time.minusHours(3l))
                .build();
        Event football7 = Event.builder()
                .name("Kayserispor-Rizespor")
                .mbs(1)
                .type(EventType.FOOTBALL)
                .eventDate(time.plusHours(3l))
                .build();
        Event football8 = Event.builder()
                .name("Trabzonspor-Konyaspor")
                .mbs(1)
                .type(EventType.FOOTBALL)
                .eventDate(time.plusHours(3l))
                .build();

        eventRepository.saveAll(Arrays.asList(football1, football2, football3, football4,
                football5, football6, football7, football8));
    }

    private void createBasketballEvents(LocalDateTime time) {
        // basketball mbs3
        Event basketball1 = Event.builder()
                .name("Anadolu Efes-Galatasaray")
                .mbs(3)
                .type(EventType.BASKETBALL)
                .eventDate(time.minusHours(1l))
                .build();
        // basketball mbs2
        Event basketball2 = Event.builder()
                .name("Türk Telekom-Darüşşafaka")
                .mbs(2)
                .type(EventType.BASKETBALL)
                .eventDate(time.plusHours(2l))
                .build();
        Event basketball3 = Event.builder()
                .name("Bursaspor-Beşiktaş")
                .mbs(2)
                .type(EventType.BASKETBALL)
                .eventDate(time.minusHours(2l))
                .build();
        Event basketball4 = Event.builder()
                .name("Fenerbahçe-Ormanspor")
                .mbs(2)
                .type(EventType.BASKETBALL)
                .eventDate(time.plusHours(2l))
                .build();
        // basketball mbs1
        Event basketball5 = Event.builder()
                .name("Afyon-Gaziantep")
                .mbs(1)
                .type(EventType.BASKETBALL)
                .eventDate(time.plusHours(2l))
                .build();

        eventRepository.saveAll(Arrays.asList(basketball1, basketball2, basketball3, basketball4,
                basketball5));
    }

    private void createTennisEvents(LocalDateTime time) {
        // tennis mbs1
        Event tennis1 = Event.builder()
                .name("Başak-Berfu")
                .mbs(1)
                .type(EventType.TENNIS)
                .eventDate(time.minusHours(1l))
                .build();
        Event tennis2 = Event.builder()
                .name("Pemra-Cansu")
                .mbs(1)
                .type(EventType.TENNIS)
                .eventDate(time.plusHours(1l))
                .build();

        eventRepository.saveAll(Arrays.asList(tennis1, tennis2));
    }
}
