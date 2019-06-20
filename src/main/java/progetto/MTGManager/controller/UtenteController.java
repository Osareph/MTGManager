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


import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.CartaService;
import progetto.MTGManager.services.UtenteService;
import progetto.MTGManager.services.UtenteValidator;

@Controller
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private CartaService cartaService;
	
	@Autowired
	private UtenteValidator utenteValidator;
	
	@RequestMapping(value = "/registrazione" , method = RequestMethod.POST)
	public String registrazione(@Valid @ModelAttribute("utente") Utente utente, Model model, BindingResult bindingResult) {
		String nextPage;
		utenteValidator.validate(utente, bindingResult);
		if(!bindingResult.hasErrors()) {
			utente.setRole("GUEST");
			utente.setParolaSegreta(new BCryptPasswordEncoder().encode(utente.getParolaSegreta()));
			utenteService.aggiungiUtente(utente);
			model.addAttribute("utente", utente);
			nextPage="confermaRegistrazione";
		} else {
			nextPage="singUp";
		}
		return nextPage;
	}
	
	@RequestMapping(value = "/utente/{id}", method = RequestMethod.GET)
	public String getUtente(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("utente", this.utenteService.utentePerId(id));
			model.addAttribute("carte", this.cartaService.cartaPerUtente_id(id));
			return "collezioneUtente";
		}else {
			model.addAttribute("utenti", this.utenteService.tutti());
			return "collezioniUtenti";
		}
	}
	
	@RequestMapping(value = "/singUp")
	public String singUp(Model model) {
		model.addAttribute("utente", new Utente());
		return "singUp.html";
	}
	
	@RequestMapping(value = "/diventaAdmin")
	public String admin(Model model) {
		model.addAttribute("utente", new Utente());
		return "admin";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String seiUnAdmin(@Valid @ModelAttribute ("utente") Utente utente, Model model){
		String nextPage;
		if(utenteService.utentePerUsername(utente) != null) {
			utente = utenteService.aggiornamentoUtenteAdmin(utente);
			model.addAttribute("utente", utente);
			nextPage = "home";
		} else {
			nextPage = "admin";
		}
		return nextPage;
	}
}
