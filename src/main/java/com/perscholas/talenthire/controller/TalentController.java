package com.perscholas.talenthire.controller;

import com.perscholas.talenthire.dto.TalentDto;
import com.perscholas.talenthire.service.TalentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TalentController {
    // Interface is recommended to use for DI. Because it provides Loose coupling.
    // At run-time, able to use any implementation if we inject Interface
    private TalentService talentService;
    // Constructor based DI

    public TalentController(TalentService talentService) {
        this.talentService = talentService;
    }
    // Handler method to handle HTTP GET request and return model and view
    @GetMapping("/talent/myclients")
    public  String talents(Model model){
        List<TalentDto> talents = talentService.findAllTalents();
        model.addAttribute("talents", talents);
        return "/talent/myclients";
    }
    // Handler method to handle new Client HTTP request
    @GetMapping("/talent/clients/newclient")
    public String newClientForm(Model model){
        TalentDto talentDto = new TalentDto();
        model.addAttribute("talent",talentDto);
        return "talent/new_client";
    }
    // Handler method to handle form submit POST request
    // ModelAttribute annotation will read data from form and set the values to the fields of model object
    // Binding result class is used to check the error and return to the view
    @PostMapping("/talent/myclients")
    public String createClient(@Valid @ModelAttribute("talent") TalentDto talentDto,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            // return to the calling page, newclient
            model.addAttribute("talent", talentDto);
            return ("talent/new_client");
        }
        talentService.createClient(talentDto);
        return "redirect:/talent/myclients";
    }


}
