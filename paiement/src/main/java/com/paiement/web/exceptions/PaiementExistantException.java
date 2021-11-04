package com.paiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // code 409 données recues en conflit avec donnees existantes
public class PaiementExistantException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PaiementExistantException(String message) {
        super(message);
    }
}
