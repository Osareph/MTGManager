package progetto.MTGManager.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.UtenteService;

@Controller
public class SystemController {
	
	@Autowired
	UtenteService utenteService;


	@RequestMapping(value = "/home")
	public String home(Model model, @ModelAttribute ("utente") Utente utente){
	
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
}
