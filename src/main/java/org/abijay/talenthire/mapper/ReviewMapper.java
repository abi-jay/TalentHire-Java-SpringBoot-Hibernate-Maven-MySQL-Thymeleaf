package org.abijay.talenthire.mapper;

import org.abijay.talenthire.dto.ReviewDto;
import org.abijay.talenthire.entity.Review;

public class ReviewMapper {
    // Map review entity to review dto
    public static ReviewDto mapToReviewDto(Review review){
        return ReviewDto.builder()
                .id(review.getId())
                .name(review.getName())
                .email(review.getEmail())
                .content(review.getContent())
                .createdOn(review.getCreatedOn())
                .updatedOn(review.getUpdatedOn())
                .build();
    }
    // Map review dto to review entity
    public static Review mapToReview(ReviewDto reviewDto){
        return Review.builder()
                .id(reviewDto.getId())
                .name(reviewDto.getName())
                .email(reviewDto.getEmail())
                .content(reviewDto.getContent())
                .createdOn(reviewDto.getCreatedOn())
                .updatedOn(reviewDto.getUpdatedOn())
                .build();
    }
}
