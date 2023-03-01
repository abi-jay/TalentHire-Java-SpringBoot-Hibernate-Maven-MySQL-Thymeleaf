package com.perscholas.talenthire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentDto {
    private Long id;
    private String name;
    private String introduction;
    private String talent;
    private String location;
    private String email;
    private LocalDate memberSince;
    private BigDecimal rate;
}
