package progetto.MTGManager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		UserDetails userDetails = this.utenteUserDetailsService.loadUserByUsername("osareph");
		String usarmane = userDetails.getUsername();
		Utente tmp = new Utente();
		tmp.setUsername(usarmane);
		Utente utente = this.utenteService.utentePerUsername(tmp);
		model.addAttribute("utente", utente);
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
