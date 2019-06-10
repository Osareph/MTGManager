package progetto.MTGManager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.UtenteService;
import progetto.MTGManager.services.UtenteValidator;

@Controller
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private UtenteValidator utenteValidator;
	
	@RequestMapping(value = "/registrazione", method = RequestMethod.POST)
	public String registrazioneUtente(@Valid @ModelAttribute("utente") Utente utente, Model model, BindingResult bindingResult) {
		
		this.utenteValidator.validate(utente, bindingResult);
		if(!bindingResult.hasErrors()) {
			utenteService.aggiungiUtente(utente);
			model.addAttribute("utente",this.utenteService.utentePerUsername(utente));
			return "confermaRegistrazione.html";
		} else {
			return "singUp.html";
		}
	}
}
