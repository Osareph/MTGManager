package progetto.MTGManager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		Carta c6 = new Carta("Guglie Cineree", "rossa",1);
		Carta c7 = new Carta("Nuova Benalia","bianca",3);
		Carta c8 = new Carta("Ponte di Mosswort", "verde", 2);
		cartaRepository.save(c1);
		cartaRepository.save(c2);
		cartaRepository.save(c3);
		cartaRepository.save(c4);
		cartaRepository.save(c5);
		cartaRepository.save(c6);
		cartaRepository.save(c7);
		cartaRepository.save(c8);
		String password1 = new BCryptPasswordEncoder().encode("admin");
		String password2 = new BCryptPasswordEncoder().encode("psw1");
		String password3 = new BCryptPasswordEncoder().encode("psw2");
		String password4 = new BCryptPasswordEncoder().encode("psw3");
		Utente u1 = new Utente("Admin",password1,"ADMIN");
		Utente u2 = new Utente("Ale",password2,"GUEST");
		Utente u3 = new Utente("Manuel",password3,"GUEST");
		Utente u4 = new Utente("Gigino",password4,"GUEST");
		utenteRepository.save(u1);
		utenteRepository.save(u2);
		utenteRepository.save(u3);
		utenteRepository.save(u4);
		c1= new Carta("Foresta","verde",1,u2);
		c2= new Carta("Palude","nera",1,u2);
		c3= new Carta("Pianura","bianca",1,u2);
		c4= new Carta("Palude","nera",1,u2);
		c5= new Carta("Nuova Benalia","bianca",1,u2);
		cartaRepository.save(c1);
		cartaRepository.save(c2);
		cartaRepository.save(c3);
		cartaRepository.save(c4);
		cartaRepository.save(c5);
		c1= new Carta("Isola","blu",1,u1);
		c2= new Carta("Montagna","rossa",1,u1);
		c3= new Carta("Montagna","rossa",1,u1);
		cartaRepository.save(c1);
		cartaRepository.save(c2);
		cartaRepository.save(c3);
		c1= new Carta("Isola","blu",1,u3);
		c2= new Carta("Montagna","nera",1,u3);
		c3= new Carta("Pianura","bianca",1,u3);
		c4= new Carta("Guglie Cineree","rossa",1,u3);
		cartaRepository.save(c1);
		cartaRepository.save(c2);
		cartaRepository.save(c3);
		cartaRepository.save(c4);

	}

	
	
}