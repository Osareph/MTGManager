package progetto.MTGManager.repository;

import org.springframework.data.repository.CrudRepository;

import progetto.MTGManager.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

	public Utente findByUsername(String username);
	public Utente findByParolaSegreta(String parolaSegreta);
}
