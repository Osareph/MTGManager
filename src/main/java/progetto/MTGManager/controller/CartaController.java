package progetto.MTGManager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import progetto.MTGManager.model.Carta;
import progetto.MTGManager.services.CartaService;
import progetto.MTGManager.services.CartaValidator;

@Controller
public class CartaController {
	
	@Autowired
	private CartaService cartaService;
	
	@Autowired
	private CartaValidator cartaValidator;
	
	@RequestMapping(value = "/home")
	public String home(){
		return "home";	
	}
	
	@RequestMapping(value = "/collezione")
	public String collezione(Model model) {
		model.addAttribute("carte",cartaService.tutti());
		return "collezione";
	}
	
	@RequestMapping(value = "/collezionePersonale")
	public String collezionePersonale() {
		return "collezionePersonale";
	}
	
	@RequestMapping(value= "aggiungiCarta")
	public String aggiungiCarta(Model model) {
		model.addAttribute("carta", new Carta());
		return "addCarta";
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
	@RequestMapping(value="/addCarta", method = RequestMethod.POST)
	public String addCarta(@Valid @ModelAttribute("carta") Carta carta, Model model, BindingResult bindingResult) {
		this.cartaValidator.validate(carta, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			model.addAttribute("carta", carta);
			this.cartaService.aggiungiCarta(carta);
			return "addCartaOK";
		}
		else {
			return "addCarta";
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
