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
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Please enter few details on the Client")
    private String introduction;
    @NotEmpty(message = "Talent should not be empty")
    private String talent;
    @NotEmpty(message = "Location should not be empty")
    private String location;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String url;
    private LocalDate memberSince;
    private BigDecimal rate;
    private Set<ReviewDto> reviews;
}
