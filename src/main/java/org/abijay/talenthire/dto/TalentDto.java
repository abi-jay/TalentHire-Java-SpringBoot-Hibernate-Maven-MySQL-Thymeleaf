package org.abijay.talenthire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentDto {
    private Long id;
    @NotEmpty(message = "Talent should not be empty")
    private String talent;
    private String shortDescription;
    @NotEmpty(message = "Please enter few details on your talent")
    private String description;
    private LocalDate memberSince;
    private BigDecimal rate;
    private String url;
    @NotEmpty(message = "Location should not be empty")
    private String location;
    private Set<RequestDto> requests;
    private Set<FulfillDto> fulfills;
}