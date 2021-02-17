// package com.exist.util;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.validation.ObjectError;
// import java.util.*;


// @ControllerAdvice
// public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	

// 	@ExceptionHandler(Exception.class)
// 	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest re) {
// 		List<String> details = new ArrayList<E>();
// 		details.add(ex.getLocalizedMessage());
// 		ErrorResponse error = new ErrorResponse("Server Error", details);
// 		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
// 	}

// 	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
// 						HttpHeaders headers, HttpStatus status, WebRequest request) {
// 		List<String> details = new ArrayList<>();
// 		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
// 			details.add(error,getDefaultMessage());
// 		}
// 		ErrorResponse error = new ErrorResponse("Validation Failed", HttpStatus.BAD_REQUEST, details);
// 		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
// 	}
// }