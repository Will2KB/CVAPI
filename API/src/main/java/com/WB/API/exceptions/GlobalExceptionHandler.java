package com.WB.API.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.WB.API.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Gestionnaires des exceptions gloables
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Gestion des exceptions de type RessourceNotFoundException
	 * 
	 * @param ex:      Exception levée
	 * @param request: Requête HTTP d'où provient l'exception
	 * 
	 * @return Retourne une réponse de type APIError
	 */
	@ExceptionHandler(RessourceNotFoundException.class)
	public ResponseEntity<ApiError> handleResourceNotFound(RessourceNotFoundException ex, HttpServletRequest request) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Gestion des exceptions de type BadRequestException
	 * 
	 * @param ex:      Exception levée
	 * @param request: Requête HTTP d'où provient l'exception
	 * 
	 * @return Retourne une réponse de type APIError
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Gestion des exceptions de type invalid argument
	 * 
	 * @param ex: Exception levée
	 * 
	 * @return Retourne une réponse sous forme de liste des erreurs soulevées pour
	 *         chaque argument non valide
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(errors);
	}

	/**
	 * Gestion des exceptions générique
	 * 
	 * @param ex:      Exception levée
	 * @param request: Requête HTTP d'où provient l'exception
	 * 
	 * @return Retourne une réponse de type APIError
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Une erreur interne est survenue.",
				request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}