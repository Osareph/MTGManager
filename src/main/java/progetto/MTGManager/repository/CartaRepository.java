package progetto.MTGManager.repository;

import org.springframework.data.repository.CrudRepository;

import progetto.MTGManager.model.Carta;

public interface CartaRepository extends CrudRepository<Carta, Long> {

}
