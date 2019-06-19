package progetto.MTGManager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.UtenteService;

@Controller
public class SystemController {
	
	@Autowired
	UtenteService utenteService;


	@RequestMapping(value = "/home")
	public String home(@Valid @ModelAttribute("utente")Utente utente,Model model){
		Utente tmp = this.utenteService.utentePerUsername(utente);
		model.addAttribute("utente",tmp);
		return "home";	
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value ="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value ="/logout")
	public String logout() {
		return "logout";
	}
}
