package progetto.MTGManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import progetto.MTGManager.model.Carta;
import progetto.MTGManager.model.Set;

public interface CartaRepository extends CrudRepository<Carta, Long> {

	Carta findByNome(String nome);

	Carta findBySet(Set set);
	
	List<Carta> findByUtente_id(Long id);
	
	Carta findByNomeAndUtente_id(String nome, Long id);
	
	void deleteById(Long id);

}
