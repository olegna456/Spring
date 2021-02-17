// package com.exist.util;

// import org.springframework.http.HttpStatus;
// import java.time.LocalDateTime;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import java.util.*;

// public class ErrorResponse {
// 	private HttpStatus status;

// 	@JsonFormat(shape=JsonFormat.shape.STRING,pattern="dd-MM-yyyy hh:mm:ss")
// 	private LocalDateTime timeStamp;

// 	private String message;

// 	private List<String> details;

// 	public ErrorResponse(String message, List<String> details) {
// 		this.timeStamp = new LocalDateTime.now();
// 		this.message = message;
// 		this.details = details;
// 	}

// 	public ErrorResponse(String message, HttpStatus status,  List<String> details) {
// 		this.timeStamp = new LocalDateTime.now();
// 		this.message = message;
// 		this.status = status;
// 		this.details = details;
// 	}

// }