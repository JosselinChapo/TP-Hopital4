package hopital.controller;

import hopital.model.Medecin;
import hopital.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @Autowired private MedecinRepository medecinRepository;
    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
