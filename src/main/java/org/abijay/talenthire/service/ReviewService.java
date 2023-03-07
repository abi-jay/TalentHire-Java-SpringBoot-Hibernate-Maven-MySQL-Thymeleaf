package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.ReviewDto;

public interface ReviewService {
    void createReview(String talentUrl, ReviewDto reviewDto);
}
