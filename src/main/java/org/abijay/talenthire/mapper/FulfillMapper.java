package org.abijay.talenthire.mapper;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.entity.Fulfill;

public class FulfillMapper {
    public static FulfillDto mapToFulfillDto(Fulfill fulfill){
        return FulfillDto.builder()
                .id(fulfill.getId())
                .name(fulfill.getName())
                .email(fulfill.getEmail())
                .requestMessage(fulfill.getRequestMessage())
                .createdOn(fulfill.getCreatedOn())
                .updatedOn(fulfill.getUpdatedOn())
                .build();

    }
    // Map review dto to review entity
    public static Fulfill mapToFulfill(FulfillDto fulfillDto){
        return Fulfill.builder()
                .id(fulfillDto.getId())
                .name(fulfillDto.getName())
                .email(fulfillDto.getEmail())
                .requestMessage(fulfillDto.getRequestMessage())
                .createdOn(fulfillDto.getCreatedOn())
                .updatedOn(fulfillDto.getUpdatedOn())
                .build();
    }
}
