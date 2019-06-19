package progetto.MTGManager.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import progetto.MTGManager.model.Utente;
import progetto.MTGManager.repository.UtenteRepository;

@Service
public class UtenteUserDetailsService implements UserDetailsService {

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente utente = utenteRepository.findByUsername(username);
		
		if(utente == null) {
			throw new UsernameNotFoundException("username not found");
		}
		
		return new org.springframework.security.core.userdetails.User
				(username, utente.getParolaSegreta(), Collections.singleton(new SimpleGrantedAuthority("GUEST")));
	}

}
