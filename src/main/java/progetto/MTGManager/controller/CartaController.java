package progetto.MTGManager.controller;

import java.util.List;

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
		for (Utente element: this.utenteService.tutti()) {
			element.setRandom(Math.floor(Math.random()*10));
			this.utenteService.aggiungiUtente(element);
		}	
		model.addAttribute("utenti", this.utenteService.tutti());
		return "collezioniUtenti";
	}
	
	@RequestMapping(value= "aggiungiCarta")
	public String aggiungiCarta(Model model) {
		model.addAttribute("carta", new Carta());
		return "addCarta";
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
			model.addAttribute("error", 0);  //questo attributo permette alla vista di decidere se mostrare la form con avviso di errore o meno
			return "carta";
		}else {
			model.addAttribute("carte", this.cartaService.tutti());
			return "collezione";
		}
	}
	
	//ho usato 2 path variable per tenere traccia sia dell'utente che della carta, conoscere l'utete di riferimento sarà essenziale per la riuscita dello step successivo: removeCarta
	@RequestMapping(value = "/cartaP/{id}/{id_u}", method = RequestMethod.GET)
	public String getCartaP(@PathVariable ("id") Long id,@PathVariable("id_u") Long id_u, Utente utente,  Model model) {
		if(id!=null) {
			model.addAttribute("trueUtente", this.utenteService.utentePerId(id_u));
			model.addAttribute("carta", this.cartaService.cartaPerId(id));
			model.addAttribute("utente", new Utente());
			model.addAttribute("error", 0);
			return "cartaP";
		}else {
			model.addAttribute("carte", this.cartaService.tutti());
			model.addAttribute("utente", this.utenteService.utentePerId(id_u));
			return "collezioneUtente";
		}
	}
	
	//questo metodo confronta la password inserita nella form della vista con quella dell'utente du cui si è tenuto traccia
	//se l'esito è positivo procede ad eliminare l'istanza della carta collegata all'utente nel DB e ad aumentare la quantità disponibile della stessa nella collezione condivisa
	@RequestMapping(value = "/removeCarta/{id}/{id_u}", method = RequestMethod.POST)
	public String removeCarta(@PathVariable ("id") Long id, @PathVariable ("id_u") Long id_u, @Valid @ModelAttribute("utente") Utente utente, Model model, BindingResult bindingResult) {
		Utente trueUtente= new Utente();
		trueUtente= this.utenteService.utentePerId(id_u);
		String provaPsw = utente.getParolaSegreta();
		if(new BCryptPasswordEncoder().matches(provaPsw, trueUtente.getParolaSegreta())) {
				String tmpNome= this.cartaService.cartaPerId(id).getNome();
				this.cartaService.cartaPerNomeAndUtente_id(tmpNome, null).improoveQuantita();
				this.cartaService.aggiungiCarta(this.cartaService.cartaPerNomeAndUtente_id(tmpNome, null));
				this.cartaService.cancellaPerId(id);
				
	
				model.addAttribute("carte", this.cartaService.cartaPerUtente_id(id_u));
				model.addAttribute("utente", trueUtente);
				return "collezioneUtente";
			}else {
	
				model.addAttribute("utente", utente);
				model.addAttribute("carta", this.cartaService.cartaPerId(id));
				model.addAttribute("trueUtente", trueUtente);
				model.addAttribute("error",1);
				return "cartaP";
			}
			
	}
	//questo metodo si serve dell'username inserito nella form della vista per rintracciare l'utente a cui assegnare la carta
	//l'assegnazione consiste nel generare una  nuova istanza della carta collegandola all'utente in questione, mentre alla versione "originale" della carta viene diminuita la quantità disponibile
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
