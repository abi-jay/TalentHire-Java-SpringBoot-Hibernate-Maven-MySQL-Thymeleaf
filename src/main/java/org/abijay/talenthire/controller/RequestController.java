package org.abijay.talenthire.controller;

import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.service.RequestService;
import org.abijay.talenthire.service.TalentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RequestController {
    private RequestService requestService;
    private TalentService talentService;
    // constructor based dependency injection


    public RequestController(RequestService requestService, TalentService talentService) {
        this.requestService = requestService;
        this.talentService = talentService;
    }

    // handler method to handle create review form submit request
    // @ModelAttribute will bind every form data to model object that is now available to the handler method below
    // whenever a review is added, redirect to show talent handler
    @PostMapping("/{talentUrl}/requests")
    public String createRequest(@PathVariable("talentUrl") String talentUrl,
                               @Valid @ModelAttribute("request") RequestDto requestDto,
                               BindingResult result,
                               Model model){
        TalentDto talentDto = talentService.findTalentByUrl(talentUrl);
        System.out.println("1");
        if(result.hasErrors()){
            System.out.println("11");
            model.addAttribute("talent", talentDto);
            model.addAttribute("request", requestDto);
            return "list/show_talent";
        }
        System.out.println("111");
        requestService.createRequest(talentUrl, requestDto);
        return "redirect:/talent/" + talentUrl;
    }
}
