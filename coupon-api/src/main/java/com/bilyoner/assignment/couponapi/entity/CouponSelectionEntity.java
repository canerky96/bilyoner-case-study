package com.bilyoner.assignment.couponapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CouponSelectionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = CouponEntity.class, cascade = CascadeType.ALL)
    private CouponEntity coupon;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "coupon_selection_event_entity",
            joinColumns = {@JoinColumn(name = "coupon_selection_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<EventEntity> events;
}
