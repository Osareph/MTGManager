package progetto.MTGManager.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import progetto.MTGManager.model.Carta;
import progetto.MTGManager.repository.CartaRepository;

@Service
public class CartaService {
	

	@Autowired
	CartaRepository cartaRepository;
	
	@Transactional
	public Carta aggiungiCarta(Carta carta) {
		return cartaRepository.save(carta);
	}
	
	@Transactional
	public Carta cartaPerNome(Carta carta) {
		return this.cartaRepository.findByNome(carta.getNome());
	}
	
	@Transactional
	public Carta cartaPerSet(Carta carta) {
		return this.cartaRepository.findBySet(carta.getSet());
	}
	
	@Transactional
	public Carta cartaPerId(Long id) {
		return (Carta)this.cartaRepository.findById(id).get();
	}

	@Transactional
	public List<Carta> tutti(){
		return (List<Carta>) cartaRepository.findAll();
	}
}
