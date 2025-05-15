package com.WB.API.exceptions;

/**
 * Exception levé lorsque la ressource recherché en base de données n'a pas été
 * trouvé
 */
public class RessourceNotFoundException extends RuntimeException {
	public RessourceNotFoundException(String message) {
		super(message);
	}
}
