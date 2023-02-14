package hopital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hopital.model.Secretaire;
import hopital.repository.SecretaireRepository;
import hopital.service.SecretaireService;


@Controller
@RequestMapping("/secretaire")
public class SecretaireController {
	@Autowired
	private SecretaireService SecretaireSrv;

	@GetMapping("")
	public String list(Model model) {
		List<Secretaire> secretaires = SecretaireSrv.findAll();

		model.addAttribute("secretaires", secretaires);

		return "secretaire/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goForm(model, new Secretaire());
	}

	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		return goForm(model, SecretaireSrv.findById(id));
	}

	private String goForm(Model model, Secretaire secretaire) {
		model.addAttribute("secretaire", secretaire);
		return "secretaire/edit";
	}

	@PostMapping("")
	public String save( @ModelAttribute Secretaire secretaire) {
		//new SecretaireValidator().validate(secretaire, result);
		

		
		if (secretaire.getId() == null) {
			SecretaireSrv.create(secretaire);
		} else {
			SecretaireSrv.update(secretaire);
		}
		return "redirect:/secretaire";
	}



	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		SecretaireSrv.delete(id);
		return "redirect:/secretaire";
	}

	

	
	
}


