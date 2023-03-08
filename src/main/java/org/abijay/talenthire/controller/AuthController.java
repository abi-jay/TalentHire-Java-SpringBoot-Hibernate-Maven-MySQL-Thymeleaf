package org.abijay.talenthire.controller;

import org.abijay.talenthire.dto.RegistrationDto;
import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    // constructor based dependency injection
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Handler method to handle login page request
    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    // Handler method to handle user registration request
    // create a dto object and add it to the model
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create empty object that will hold the form data
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "auth/register";
    }

    // handler method to handle user registration form submission request
    // @ModelAttribute gets form data
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model){
        // Unique validation for email address
        User existingUser = userService.finByEmail(user.getEmail());
        if(existingUser != null){
            result.rejectValue("email", null,"This email address is already registered. Kindly login!");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "auth/register";
        }
        // call Service to store in mysql
        userService.saveUser(user);
        // call same register page with success message through parameter to register html pull
        return "redirect:?success";
    }
}
