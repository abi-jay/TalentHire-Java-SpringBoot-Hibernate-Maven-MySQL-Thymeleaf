package org.abijay.talenthire.controller;

import org.abijay.talenthire.dto.ReviewDto;
import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.service.ReviewService;
import org.abijay.talenthire.service.TalentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ReviewController {
    private ReviewService reviewService;
    private TalentService talentService;
    // constructor based dependency injection


    public ReviewController(ReviewService reviewService, TalentService talentService) {
        this.reviewService = reviewService;
        this.talentService = talentService;
    }

    // handler method to handle create review form submit request
    // @ModelAttribute will bind every form data to model object that is now available to the handler method below
    // whenever a review is added, redirect to show talent handler
    @PostMapping("/{talentUrl}/reviews")
    public String createReview(@PathVariable("talentUrl") String talentUrl,
                               @Valid @ModelAttribute("review") ReviewDto reviewDto,
                               BindingResult result,
                               Model model){
        TalentDto talentDto = talentService.findTalentByUrl(talentUrl);
        if(result.hasErrors()){
            model.addAttribute("talent", talentDto);
            model.addAttribute("review", reviewDto);
            return "list/show_talent";
        }
        reviewService.createReview(talentUrl,reviewDto);
        return "redirect:/talent/" + talentUrl;
    }
}
