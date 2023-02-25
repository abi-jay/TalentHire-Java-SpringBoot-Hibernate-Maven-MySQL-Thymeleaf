package com.perscholas.talenthire.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TalentDto {
    private Long id;
    private String name;
    private String introduction;
    private String talent;
    private String location;
    private String email;
    private LocalDateTime memberSince;
    private BigDecimal rate;
}
