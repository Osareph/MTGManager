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
import progetto.MTGManager.model.Utente;
import progetto.MTGManager.services.CartaService;
import progetto.MTGManager.services.CartaValidator;
import progetto.MTGManager.services.UtenteService;
import progetto.MTGManager.services.UtenteValidator;

@Controller
public class CartaController {
	
	@Autowired
	private UtenteValidator usernameValidator;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private CartaService cartaService;
	
	@Autowired
	private CartaValidator cartaValidator;
	
	@RequestMapping(value = "/collezione")
	public String collezione(Model model) {
		model.addAttribute("carte",cartaService.cartaPerUtente_id(null));
		return "collezione";
	}
	
	@RequestMapping(value = "/collezioniUtenti")
	public String collezionePersonale(Model model) {
		model.addAttribute("utenti", this.utenteService.tutti());
		return "collezioniUtenti";
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
	@RequestMapping(value = "/carta/{id}", method = RequestMethod.GET)
	public String getCarta(@PathVariable ("id") Long id,  Model model) {
		if(id!=null) {
			model.addAttribute("utente", new Utente());
			model.addAttribute("carta", this.cartaService.cartaPerId(id));
			model.addAttribute("error", 0);
			return "carta";
		}else {
			model.addAttribute("carte", this.cartaService.tutti());
			return "collezione";
		}
	}
	
	@RequestMapping(value = "/moveCarta/{id}", method = RequestMethod.POST)
	public String moveCarta(@PathVariable ("id") Long id,@Valid @ModelAttribute("utente") Utente utente, Model model, BindingResult bindingResult) {
		this.usernameValidator.validate(utente, bindingResult);
		Utente tmp= new Utente();
		tmp= this.utenteService.utentePerUsername(utente);
			if(tmp!=null) {
				this.cartaService.cartaPerId(id).reduceQuantita();
				Carta cartaTmp= this.cartaService.cartaPerId(id);
				Carta newCarta = new Carta(cartaTmp.getNome(),cartaTmp.getColore(),1);
				newCarta.setUtente(tmp);
				this.cartaService.aggiungiCarta(newCarta);
				tmp.addCarta(newCarta);
				this.utenteService.aggiungiUtente(tmp);
				model.addAttribute("carta", this.cartaService.cartaPerId(id));
				model.addAttribute("utente", tmp);
				model.addAttribute("error", 0);
			}else {
				model.addAttribute("utente", new Utente());
				model.addAttribute("carta", this.cartaService.cartaPerId(id));
				model.addAttribute("error",1);
			}
			return "carta";
	}
}
