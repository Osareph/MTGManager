package progetto.MTGManager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import progetto.MTGManager.model.Carta;
import progetto.MTGManager.model.Utente;

/*
 * è un componente della nostra applicazione
 */
@Component
public class DBPopulation implements ApplicationRunner{

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private CartaRepository cartaRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.deleteAll();
		this.addAll();
	}
	
	private void deleteAll() {
		System.out.print("Cancello tutto");
		utenteRepository.deleteAll();
		cartaRepository.deleteAll();
	}
	
	private void addAll() {
		System.out.print("Aggiungo carte");
		Carta c1 = new Carta("Foresta","verde",1);
		Carta c2 = new Carta("Palude","nera",2);
		Carta c3 = new Carta("Isola","blu",2);
		Carta c4 = new Carta("Montagna","rossa",1);
		Carta c5 = new Carta("Pianura","bianca",0);
		Carta c6 = new Carta("Atto Blasfemo", "rossa",1);
		cartaRepository.save(c1);
		cartaRepository.save(c2);
		cartaRepository.save(c3);
		cartaRepository.save(c4);
		cartaRepository.save(c5);
		cartaRepository.save(c6);
		Utente u1 = new Utente("Gigino","pass1");
		utenteRepository.save(u1);


	}

	
	
}