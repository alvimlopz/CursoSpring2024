package com.example.demo.services.validation;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.ClinteNewDTO;
import com.example.demo.resources.exception.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClinteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	public boolean isValid(ClinteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}