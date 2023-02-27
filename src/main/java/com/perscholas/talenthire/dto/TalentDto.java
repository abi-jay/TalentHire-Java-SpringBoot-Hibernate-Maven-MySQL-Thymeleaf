package com.perscholas.talenthire.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TalentDto {
    private Long id;
    private String name;
    private String introduction;
    private String talent;
    private Integer location;
    private String email;
    private LocalDate memberSince;
    private BigDecimal rate;
}
