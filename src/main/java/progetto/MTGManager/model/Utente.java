package progetto.MTGManager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	@Column
	private String email;
	@Column(name = "password")
	private String parolaSegreta;
	@Column
	private String role;
	@Column(name = "username")
	private String username;
	@OneToMany(mappedBy= "utente")
	private List<Carta> carte;
	
	
	
	
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getParolaSegreta() {
		return parolaSegreta;
	}
	public void setParolaSegreta(String parolaSegreta) {
		this.parolaSegreta = parolaSegreta;
	}
	public List<Carta> getCarte() {
		return carte;
	}
	public void setCarte(List<Carta> carte) {
		this.carte = carte;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Utente() {}
	
}
