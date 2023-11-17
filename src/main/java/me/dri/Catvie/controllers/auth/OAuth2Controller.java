package me.dri.Catvie.controllers.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {


    @GetMapping("/auth/github/login")
    public String redirectToGithubOauth() {
        return "redirect:/oauth2/authorization/github";
    }
}
