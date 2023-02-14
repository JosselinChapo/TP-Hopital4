package hopital.controller;

import hopital.model.Medecin;
import hopital.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medecin")
public class MedecinController {

    @Autowired private MedecinRepository medecinRepository;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("medecins", medecinRepository.findAll());
        return "medecin/list";
    }

    @PostMapping("")
    public String save(@ModelAttribute Medecin medecin) {
        medecinRepository.save(medecin);
        return "redirect:/medecin";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        medecinRepository.deleteById(id);
        return "redirect:/medecin";
    }

    @GetMapping("/add")
    public String ajout(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "medecin/form";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model) {
        Medecin m = medecinRepository.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("medecin", m);
        return "medecin/form";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "forward:/medecin";
    }
}
