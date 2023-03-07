package org.abijay.talenthire.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
// to transfer data between view and controller layer
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private String name;
    private String email;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
