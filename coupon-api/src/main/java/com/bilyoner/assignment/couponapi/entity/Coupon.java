package com.bilyoner.assignment.couponapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private Long userId;

    @Enumerated
    @Column(nullable = false)
    private CouponStatus status;

    @Column(nullable = false)
    private Double cost;

    @ManyToMany
    @JoinTable(name = "COUPON_SELECTION",
            joinColumns = @JoinColumn(name = "COUPON_ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    private List<Event> events;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime playDate;
}
