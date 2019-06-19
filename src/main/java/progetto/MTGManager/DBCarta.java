package progetto.MTGManager;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import progetto.MTGManager.model.Carta;
import progetto.MTGManager.repository.CartaRepository;

public class DBCarta implements ApplicationRunner {

	@Autowired
	private CartaRepository cartaRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.populationDB();
	}
	
	public void populationDB() throws IOException, InterruptedException {
		
		Carta Blasphemous_Act = new Carta(1L, "Blasphemous_Act", "Rossa");
		this.cartaRepository.save(Blasphemous_Act);
	}

}
