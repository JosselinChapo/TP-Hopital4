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

import hopital.model.Patient;
import hopital.repository.PatientRepository;
import quest.exception.FormateurException;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientRepository patientRepo;
	
	
	@GetMapping("")
	public String list(Model model){
		List<Patient> patients = patientRepo.findAll();
		model.addAttribute("patients",patients);
		
	return "patient/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("patient", new Patient());

		return "patient/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Integer id, Model model) {
		
		Patient patient = patientRepo.findById(id).orElseThrow(RuntimeException::new);
		
		model.addAttribute("patient", patient);

		return "patient/form";
	}

	
	
	@PostMapping("")
	public String save(@Valid @ModelAttribute Patient patient, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "patient/form";
		}
		
		if (patient.getId() == null) {
			patientRepo.save(patient);
		} else {
			patientRepo.save(patient);
		}
		return "redirect:/patient";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		patientRepo.deleteById(id);
		return "redirect:/patient";
	
	}
}
