package progetto.MTGManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import progetto.MTGManager.services.CartaService;

@Controller
public class CartaController {
	
	@Autowired
	private CartaService cartaService;
	
	@RequestMapping(value = "/home")
	public String home(){
		return "home";	
	}
	
	@RequestMapping(value = "/collezione")
	public String collezione() {
		return "collezione";
	}
	
	@RequestMapping(value = "/collezionePersonale")
	public String collezionePersonale() {
		return "collezionePersonale";
	}
	
	@RequestMapping(value = "/collezionePersonale/{id}", method = RequestMethod.GET)
	public String getCartaPersonale(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("carta", this.cartaService.cartaPerId(id));
			return "cartaPersonale";
		}else {
			model.addAttribute("carte", this.cartaService.tutti());
			return "collezionePersonale";
		}
	}
	
	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
	public String getCarta(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("carta", this.cartaService.cartaPerId(id));
			return "carta";
		}else {
			model.addAttribute("carte", this.cartaService.tutti());
			return "collezione";
		}
	}
}
