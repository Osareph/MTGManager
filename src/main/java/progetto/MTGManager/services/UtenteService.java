package progetto.MTGManager.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	public Utente aggiornamentoUtente(Utente utente) {
		return utenteRepository.save(utente);
	}
}
