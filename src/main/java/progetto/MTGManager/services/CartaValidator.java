package progetto.MTGManager.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import progetto.MTGManager.model.Carta;
import progetto.MTGManager.model.Utente;

@Component
public class CartaValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Carta.class.equals(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
	}
}
