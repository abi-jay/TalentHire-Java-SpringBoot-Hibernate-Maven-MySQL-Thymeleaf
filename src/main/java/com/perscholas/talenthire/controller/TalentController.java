package com.perscholas.talenthire.controller;

import com.perscholas.talenthire.dto.TalentDto;
import com.perscholas.talenthire.service.TalentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String talents(Model model) {
        List<TalentDto> talents = talentService.findAllTalents();
        model.addAttribute("talents", talents);
        return "/talent/myclients";
    }

    // Handler method to handle new Client HTTP request
    @GetMapping("/talent/myclients/newclient")
    public String newClientForm(Model model) {
        TalentDto talentDto = new TalentDto();
        model.addAttribute("talent", talentDto);
        return "talent/new_client";
    }

    // Handler method to handle form submit POST request
    // ModelAttribute annotation will read data from form and set the values to the fields of model object
    // Binding result class is used to check the error and return it to the view
    @PostMapping("/talent/myclients")
    public String createClient(@Valid @ModelAttribute("talent") TalentDto talentDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            // return to the same calling page, newclient in case of error in validation
            model.addAttribute("talent", talentDto);
            return "talent/new_client";
        }
        // no error, then display myclients to showcase the newly added row
        talentService.createClient(talentDto);
        return "redirect:/talent/myclients";
    }

    // Handler method to handle edit Client request
    @GetMapping("/talent/myclients/{clientId}/edit")
    public String editClientForm(@PathVariable("clientId") Long clientId,
                                 Model model) {
        TalentDto talentDto = talentService.findClientById(clientId);
        model.addAttribute("client",talentDto);
        return "talent/edit_client";
    }

    // Handler method to handle edit client form submit request
    //Post Mapping handles HTTP POST requests
    @PostMapping("/talent/myclients/{clientId}")
    public String updateClient(@PathVariable("clientId") Long clientId,
                               @ModelAttribute("client") TalentDto talentDto,
                               BindingResult result,
                               Model model){
        if (result.hasErrors()){
            model.addAttribute("client",talentDto);
            return "talent/edit_client";
        }
        talentDto.setId(clientId);
        talentService.updateClient(talentDto);
        return "redirect:/talent/myclients";
    }

    // Handler method to handle client Delete request
    // PathVariable annotation gets the clientId value from the url
    // After deletion redirect to list of clients page
    @GetMapping("/talent/myclients/{clientId}/delete")
    public String deleteClient(@PathVariable("clientId") Long clientId){
        talentService.deleteClient(clientId);
        return "redirect:/talent/myclients";
    }

    // Handler method to handle search clients request
    // url - localhost:8080/talent/myclients/search?query=Sirius
    // RequestParam annotation to retrieve value from the Query parameter
    @GetMapping("/talent/myclients/search")
    public String searchClients(@RequestParam(value = "query") String query,
                                Model model){
        List<TalentDto> talents = talentService.searchClients(query);
        // pass the talentdto object to model
        model.addAttribute("talents",talents);
        return "talent/myclients";
    }
}
