package org.abijay.talenthire.controller;

import org.abijay.talenthire.dto.ReviewDto;
import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.service.TalentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// constructor based dependency injection to inject talentservice
// spring will automatically inject dependency whenever it sees spring bean with single parameterized constructor
@Controller
public class TalentListController {
    private TalentService talentService;

    public TalentListController(TalentService talentService) {
        this.talentService = talentService;
    }

    // handler method to handle http://localhost:8080/ root request
    @GetMapping("/")
    public String viewListTalents(Model model){
        List<TalentDto> talentsResponse = talentService.findAllTalents();
        model.addAttribute("talentsResponse", talentsResponse);
        return "list/view_talents";
    }

    // handler method to handle show talent request
    @GetMapping("/talent/{talentUrl}")
    private String showTalents(@PathVariable("talentUrl") String talentUrl,
                               Model model){
        TalentDto talentDto = talentService.findTalentByUrl(talentUrl);
        ReviewDto reviewDto = new ReviewDto();
        model.addAttribute("talent", talentDto);
        model.addAttribute("review", reviewDto);
        return "list/show_talent";
    }

    // handler method to handle talent search request
    // http://localhost:8080/page/search?query=guitar
    @GetMapping("/page/search")
    public String searchTalents(@RequestParam(value = "query") String query,
                                Model model){
        List<TalentDto> talentsResponse = talentService.searchTalents(query);
        System.out.println("Query: " + query);
        System.out.println("talentsResponse: "+talentsResponse);
        model.addAttribute("talentsResponse",talentsResponse);
        return "list/view_talents";
    }

}
