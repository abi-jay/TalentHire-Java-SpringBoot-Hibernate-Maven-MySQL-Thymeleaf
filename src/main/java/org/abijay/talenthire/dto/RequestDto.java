package org.abijay.talenthire.dto;

import lombok.*;
import java.time.LocalDateTime;
// to transfer data between view and controller layer
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDto {
    private Long id;
    private String name;
    private String email;
    private String requestMessage;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}