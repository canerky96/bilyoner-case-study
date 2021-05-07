package com.bilyoner.assignment.couponapi.validation;

import com.bilyoner.assignment.couponapi.entity.EventEntity;
import com.bilyoner.assignment.couponapi.exception.CouponApiException;
import com.bilyoner.assignment.couponapi.exception.ErrorCodeEnum;
import com.bilyoner.assignment.couponapi.model.enums.EventTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventValidator {

    public void validate(List<EventEntity> events) {
        validateMBS(events);
        validateType(events);
        validateDate(events);
    }

    private void validateMBS(List<EventEntity> events) {
        int maxMBS = events.stream()
                .mapToInt(EventEntity::getMbs)
                .min()
                .orElseThrow(NoSuchElementException::new);

        if (events.size() < maxMBS) {
            throw new CouponApiException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
        }
    }

    private void validateType(List<EventEntity> events) {

        boolean isTennisExists = events.stream()
                .anyMatch(event -> EventTypeEnum.TENNIS.equals(event.getType()));
        boolean isFootballExists = events.stream()
                .anyMatch(event -> EventTypeEnum.FOOTBALL.equals(event.getType()));

        if (isTennisExists && isFootballExists) {
            throw new CouponApiException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
        }

    }

    private void validateDate(List<EventEntity> events) {
        LocalDateTime now = LocalDateTime.now()
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        boolean isPassedEventExists = events.stream()
                .anyMatch(event -> now.isAfter(event.getEventDate()));

        if (isPassedEventExists) {
            throw new CouponApiException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
        }
    }

}
