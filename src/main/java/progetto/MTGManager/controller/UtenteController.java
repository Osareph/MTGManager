package progetto.MTGManager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.CartaService;
import progetto.MTGManager.services.UtenteService;
import progetto.MTGManager.services.UtenteValidator;

@Controller
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private UtenteValidator utenteValidator;
	
	@RequestMapping(value = "/registrazione" , method = RequestMethod.POST)
	public String registrazione(@Valid @ModelAttribute("utente") Utente utente, Model model, BindingResult bindingResult) {
		String nextPage;
		utenteValidator.validate(utente, bindingResult);
		if(!bindingResult.hasErrors()) {
			utente.setParolaSegreta(new BCryptPasswordEncoder().encode(utente.getParolaSegreta()));
			utente.setRole("GUEST");
			utenteService.aggiungiUtente(utente);
			model.addAttribute("utente", utente);
			nextPage="confermaRegistrazione";
		} else {
			nextPage="singUp";
		}
		
		return nextPage;
	}
	
	@RequestMapping(value = "/singUp")
	public String singUp(Model model) {
		model.addAttribute("utente", new Utente());
		return "singUp.html";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/logIn")
	public String logIn(Model model) {
		model.addAttribute("utente", new Utente());
		return "logIn";
	}
	
	@RequestMapping(value = "/logOut")
	public String logOut() {
		return "logOut";
	}

}
