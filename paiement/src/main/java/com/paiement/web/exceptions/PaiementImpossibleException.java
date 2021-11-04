package com.paiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //code 500
public class PaiementImpossibleException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PaiementImpossibleException(String message) {
        super(message);
    }
}
