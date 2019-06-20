package progetto.MTGManager.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Carta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable= false)
	private Long id;
	@Column
	private String nome;
	@ManyToOne
	private Set set;
	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Collezione collezione;
	@Column
	private String colore;
	private int quantita;
	
	public Carta(String nome, String colore, int quantita) {
		this.nome=nome;
		this.colore=colore;
		this.quantita=quantita;
	}
	public int reduceQuantita() {
		return this.quantita--;
	}
	public int improoveQuantita() {
		return this.quantita++;
	}
	public Carta() {
		
	}
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
	public Collezione getCollezione() {
		return collezione;
	}
	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	
	
	
}
