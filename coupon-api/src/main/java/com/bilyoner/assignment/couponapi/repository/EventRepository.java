package com.bilyoner.assignment.couponapi.repository;

import com.bilyoner.assignment.couponapi.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {

}
