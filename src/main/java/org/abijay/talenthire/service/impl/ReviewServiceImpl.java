package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.ReviewDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Review;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.mapper.ReviewMapper;
import org.abijay.talenthire.repository.FulfillRepository;
import org.abijay.talenthire.repository.ReviewRepository;
import org.abijay.talenthire.repository.TalentRepository;
import org.abijay.talenthire.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    // inject dependencies
    private ReviewRepository reviewRepository;
    private TalentRepository talentRepository;
    private FulfillRepository fulfillRepository;
    // Spring bean with single parameterized constructor- constructor based dependency injection
    public ReviewServiceImpl(ReviewRepository reviewRepository, TalentRepository talentRepository, FulfillRepository fulfillRepository) {
        this.reviewRepository = reviewRepository;
        this.talentRepository = talentRepository;
        this.fulfillRepository = fulfillRepository;
    }

    @Override
    public void createReview(String talentUrl, ReviewDto reviewDto) {
        // one to many talent to review relationship
        Talent talent = talentRepository.findByUrl(talentUrl).get();
        Review review = ReviewMapper.mapToReview(reviewDto);
        review.setTalent(talent);
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(ReviewMapper::mapToReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public Fulfill fulfillRequest(Long requestId) {
        Review request = reviewRepository.findById(requestId).get();
        reviewRepository.deleteById(requestId);
        System.out.println("Fest: "+request.getContent()+request.getTalent());
        Fulfill fulfill = new Fulfill();
        fulfill.setId(request.getId());
        fulfill.setContent(request.getContent());
        fulfill.setCreatedOn(request.getCreatedOn());
        fulfill.setEmail(request.getEmail());
        fulfill.setName(request.getName());
        fulfill.setUpdatedOn(request.getUpdatedOn());
        fulfill.setTalent(request.getTalent());
        fulfillRepository.save(fulfill);
        return fulfill;
    }
}
