package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.ReviewDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Review;

import java.util.List;

public interface ReviewService {
    void createReview(String talentUrl, ReviewDto reviewDto);

    List<ReviewDto> findAllReviews();

    Fulfill fulfillRequest(Long requestId);
}
