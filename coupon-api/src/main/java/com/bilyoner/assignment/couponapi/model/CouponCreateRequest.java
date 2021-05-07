package com.bilyoner.assignment.couponapi.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponCreateRequest {

    @NotEmpty(message = "You should select at least on event id")
    private List<Long> eventIds;
}
