package progetto.MTGManager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import progetto.MTGManager.model.Carta;
import progetto.MTGManager.model.Utente;
import progetto.MTGManager.repository.UtenteRepository;

@Component
public class UtenteService {
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Transactional
	public Utente aggiungiUtente(Utente utente) {
		return utenteRepository.save(utente);
	}
	
	@Transactional
	public Utente utentePerUsername(Utente utente) {
		return this.utenteRepository.findByUsername(utente.getUsername());
	}
	
	@Transactional
	public Utente aggiornamentoUtenteAdmin(Utente utente) {
		Utente tmp = utenteRepository.findByUsername(utente.getUsername());
		tmp.setRole("ADMIN");
		return utenteRepository.save(tmp);
	}
	
	@Transactional
	public Utente aggiornamentoUtenteGuest(Utente utente) {
		Utente tmp = utenteRepository.findByUsername(utente.getUsername());
		tmp.setRole("GUEST");
		return utenteRepository.save(tmp);
	}
	
	@Transactional
	public List<Utente> tutti(){
		return (List<Utente>) this.utenteRepository.findAll();
	}
}
