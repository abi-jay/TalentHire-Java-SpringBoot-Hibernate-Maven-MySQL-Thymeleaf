package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void createReview(String talentUrl, ReviewDto reviewDto);

    List<ReviewDto> findAllReviews();

    void fulfillRequest(Long requestId);
}
