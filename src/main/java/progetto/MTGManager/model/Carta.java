package progetto.MTGManager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Carta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable= false)
	private Long id;
	private String nome;
	@ManyToOne
	private Set set;
	@ManyToOne
	private Utente utenti;
	@ManyToOne
	private Collezione collezione;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	public Utente getUtenti() {
		return utenti;
	}
	public void setUtenti(Utente utenti) {
		this.utenti = utenti;
	}
	public Collezione getCollezione() {
		return collezione;
	}
	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	
	
	
}
