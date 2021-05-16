package com.learn.microservices.v2.limitsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitsValue {

    private int min;
    private int max;

}
