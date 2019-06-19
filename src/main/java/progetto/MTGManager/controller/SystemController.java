package progetto.MTGManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.UtenteService;
import progetto.MTGManager.services.UtenteUserDetailsService;

@Controller
public class SystemController {
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	UtenteUserDetailsService utenteUserDetailsService;


	@RequestMapping(value = "/home")
	public String home(Model model){
		model.addAttribute("utente", new Utente());
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
