package com.perscholas.talenthire.controller;

import com.perscholas.talenthire.dto.TalentDto;
import com.perscholas.talenthire.service.TalentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
