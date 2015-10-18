package br.com.jsn.noleggio.main.pattern.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ApplicationScoped
public class BeanValidationProducer {
	
	@Produces
	public ValidatorFactory createValidatorFactory() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		return validatorFactory;
	}
	
	@Produces
	public Validator createValidator(ValidatorFactory factory) {
		return factory.getValidator();
	}
}
