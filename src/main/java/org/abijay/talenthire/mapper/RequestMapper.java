package org.abijay.talenthire.mapper;

import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.entity.Request;

public class RequestMapper {
    // Map request entity to request dto
    public static RequestDto mapToReviewDto(Request request){
        return RequestDto.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .requestMessage(request.getRequestMessage())
                .createdOn(request.getCreatedOn())
                .updatedOn(request.getUpdatedOn())
                .build();

    }
    // Map review dto to review entity
    public static Request mapToReview(RequestDto requestDto){
        return Request.builder()
                .id(requestDto.getId())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .requestMessage(requestDto.getRequestMessage())
                .createdOn(requestDto.getCreatedOn())
                .updatedOn(requestDto.getUpdatedOn())
                .build();
    }
}
