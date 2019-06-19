package progetto.MTGManager.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import progetto.MTGManager.model.Utente;

@Component
public class UsernameValidator implements Validator{
	@Override
	public boolean supports(Class<?> arg0) {
		return Utente.class.equals(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
	}
}
	
