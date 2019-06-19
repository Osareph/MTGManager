package progetto.MTGManager.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import progetto.MTGManager.model.Utente;

@Controller
public class SystemController {

	@RequestMapping(value = "/home")
	public String home(Model model){
		model.addAttribute("utente", new Utente());
		return "home";	
	}
	
	@RequestMapping(value = "/logIn")
	public String logIn(Model model) {
		model.addAttribute("utente", new Utente());
		return "home";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
}
