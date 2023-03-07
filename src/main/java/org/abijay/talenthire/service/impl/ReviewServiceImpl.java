package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.ReviewDto;
import org.abijay.talenthire.entity.Review;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.mapper.ReviewMapper;
import org.abijay.talenthire.repository.ReviewRepository;
import org.abijay.talenthire.repository.TalentRepository;
import org.abijay.talenthire.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    // inject dependencies
    private ReviewRepository reviewRepository;
    private TalentRepository talentRepository;
    // Spring bean with single parameterized constructor- constructor based dependency injection
    public ReviewServiceImpl(ReviewRepository reviewRepository, TalentRepository talentRepository) {
        this.reviewRepository = reviewRepository;
        this.talentRepository = talentRepository;
    }

    @Override
    public void createReview(String talentUrl, ReviewDto reviewDto) {
        // one to many talent to review relationship
        Talent talent = talentRepository.findByUrl(talentUrl).get();
        Review review = ReviewMapper.mapToReview(reviewDto);
        review.setTalent(talent);
        reviewRepository.save(review);
    }
}
