package org.abijay.talenthire.controller;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.service.FulfillService;
import org.abijay.talenthire.service.RequestService;
import org.abijay.talenthire.service.TalentService;
import org.abijay.talenthire.util.ROLE;
import org.abijay.talenthire.util.SecurityUtils;
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
    private RequestService requestService;
    private FulfillService fulfillService;
    // Constructor based DI


    public TalentController(TalentService talentService, RequestService requestService, FulfillService fulfillService) {
        this.talentService = talentService;
        this.requestService = requestService;
        this.fulfillService = fulfillService;
    }

    // Handler method to handle HTTP GET request and return model and view
    @GetMapping("/talent/myclients")
    public String clients(Model model) {
        List<FulfillDto> fulfills = fulfillService.findAllFulfills();
        model.addAttribute("fulfills", fulfills);
        return "/talent/myclients";
    }
    // Handler method to handle HTTP GET request and return model and view
    @GetMapping("/talent/mytalents")
    public String talents(Model model) {
        // Get the role of logged in user
        String role = SecurityUtils.getRole();
        List<TalentDto> talents = null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            talents = talentService.findAllTalents();
        }
        else {
            talents = talentService.findTalentsByUser();
        }
        model.addAttribute("talents", talents);
        return "/talent/mytalents";
    }

    // Handler method to handle new Talent HTTP request
    @GetMapping("/talent/mytalents/newtalent")
    public String newTalentForm(Model model) {
        TalentDto talentDto = new TalentDto();
        model.addAttribute("talent", talentDto);
        return "talent/new_talent";
    }

    // Handler method to handle form submit POST request
    // ModelAttribute annotation will read data from form and set the values to the fields of model object
    // Binding result class is used to check the error and return it to the view
    @PostMapping("/talent/mytalents")
    public String createTalent(@Valid @ModelAttribute("talent") TalentDto talentDto,
                               BindingResult result,
                               Model model) {
        talentDto.setUrl(getUrl(talentDto.getTalent()));
        if (result.hasErrors()) {
            // return to the same calling page, newclient in case of error in validation
            model.addAttribute("talent", talentDto);
            return "talent/new_talent";
        }
        // no error, then display myclients to showcase the newly added row
        talentService.createTalent(talentDto);
        return "redirect:/talent/mytalents";
    }

    // Handler method to handle edit Talent request
    @GetMapping("/talent/mytalents/{talentId}/edit")
    public String editTalentForm(@PathVariable("talentId") Long talentId,
                                 Model model) {
        TalentDto talentDto = talentService.findTalentById(talentId);
        model.addAttribute("talent",talentDto);
        return "talent/edit_talent";
    }

    // Handler method to handle edit talent form submit request
    // Post Mapping handles HTTP POST requests
    @PostMapping("/talent/mytalents/{talentId}")
    public String updateTalent(@PathVariable("talentId") Long talentId,
                               @ModelAttribute("talent") TalentDto talentDto,
                               BindingResult result,
                               Model model){
        if (result.hasErrors()){
            model.addAttribute("talent",talentDto);
            return "talent/edit_talent";
        }
        talentDto.setId(talentId);
        talentService.updateTalent(talentDto);
        return "redirect:/talent/mytalents";
    }

    // Handler method to handle client Delete request
    // PathVariable annotation gets the clientId value from the url
    // After deletion redirect to list of clients page
    @GetMapping("/talent/myclients/{clientId}/delete")
    public String deleteClient(@PathVariable("clientId") Long clientId){
        fulfillService.deleteClient(clientId);
        return "redirect:/talent/myclients";
    }
    // Handler method to handle Talent Delete request
    // PathVariable annotation gets the talentId value from the url
    // After deletion redirect to list of talents page
    @GetMapping("/talent/mytalents/{talentId}/delete")
    public String deleteTalent(@PathVariable("talentId") Long talentId){
        talentService.deleteTalent(talentId);
        return "redirect:/talent/mytalents";
    }


    // Handler method to handle view talent request
    @GetMapping("/talent/mytalents/{talentUrl}/view")
    public String viewTalent(@PathVariable("talentUrl") String talentUrl,
                             Model model){
        TalentDto talentDto = talentService.findTalentByUrl(talentUrl);
        model.addAttribute("talent", talentDto);
        return "talent/view_talent";
    }

    // Handler method to handle search clients request
    // url - localhost:8080/talent/myclients/search?query=Sirius
    // RequestParam annotation to retrieve value from the Query parameter
    // Handler method to handle search clients request
    // url - localhost:8080/talent/myclients/searchbyname?query=Sirius
    // RequestParam annotation to retrieve value from the Query parameter
    @GetMapping("/talent/myclients/searchbyname")
    public String searchClientsByName(@RequestParam(value = "query") String query,
                                      Model model){
        List<FulfillDto> fulfills = fulfillService.searchClientsByName(query);
        // pass the talentdto object to model
        model.addAttribute("fulfills",fulfills);
        return "talent/myclients";
    }


    // Handler method to handle list requests method
    @GetMapping("/talent/mytalents/requests")
    public String talentRequest(Model model){
        String role = SecurityUtils.getRole();
        List<RequestDto> requests = null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            requests = requestService.findAllRequests();
        }
        else{
            requests = requestService.findRequestsByTalent();
        }
        model.addAttribute("requests",requests);
        return "talent/requests";
    }


    // Handler method to handle fulfill Talent service request
    @GetMapping("/talent/mytalents/requests/{requestId}")
    public String fulfillRequestById(@PathVariable("requestId") Long requestId, Model model){
        Fulfill fulfill = requestService.fulfillRequest(requestId);
        model.addAttribute("fulfill",fulfill);
        return  "redirect:/talent/mytalents/requests";
    }
    @GetMapping("/talent/mytalents/requests/{requestId}/delete")
    public String deleteRequestById(@PathVariable("requestId") Long requestId, Model model){
        requestService.deleteRequest(requestId);
        return  "redirect:/talent/mytalents/requests";
    }

    private static String getUrl(String talentTitle){
        // private-guitar-lessons
        // Private Guitar lessons
        String title = talentTitle.toLowerCase();
        // replace space with hyphen
        String url = title.replaceAll("\\s+", "-");
        // replace symbol with hyphen
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;

    }
}

