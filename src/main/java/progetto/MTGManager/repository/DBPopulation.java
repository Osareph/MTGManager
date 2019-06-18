package progetto.MTGManager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import progetto.MTGManager.model.Carta;

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
	}
	
	private void addAll() {
		System.out.print("Aggiungo utenti");
		Carta c1 = new Carta("foresta","verde");
		Carta c2 = new Carta("palude","nera");
		Carta c3 = new Carta("isola","blu");
		Carta c4 = new Carta("montagna","rossa");
		Carta c5 = new Carta("pianura","bianca");
		cartaRepository.save(c1);
		cartaRepository.save(c2);
		cartaRepository.save(c3);
		cartaRepository.save(c4);
		cartaRepository.save(c5);

	}

	
	
}