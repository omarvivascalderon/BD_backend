package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

	public Map<String, Object> response(Object object, HttpStatus status) {
		Map<String, Object> res = new HashMap<>();
		res.put("result", object);
		return res;
	}

	public Map<String, Object> response(ConstraintViolationException ex, HttpStatus status) {
		Map<String, Object> res = new HashMap<>();
		StringBuilder errors = new StringBuilder();
		ex.getConstraintViolations().forEach((error) -> {
			String property = error.getPropertyPath().toString();
			String errorMessage = error.getMessage();
			errors.append(property).append(": ").append(errorMessage).append(";");
		});
		res.put("result", errors);
		return res;
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity responseViolationException(ConstraintViolationException ex, HttpStatus status) {
		JSONObject res = new JSONObject();
		ArrayList<Error> errors = new ArrayList<>();
		ex.getConstraintViolations().forEach((error) -> {
			Error e = new Error(error.getPropertyPath().toString(), error.getMessage());
			errors.add(e);
		});
		res.put("errors", errors);
		return ResponseEntity.status(status).body(res.toMap());
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity responseException(Exception ex, HttpStatus status, boolean showStackTrace) {
		ArrayList<Error> errors = new ArrayList<>();
		JSONObject res = new JSONObject();
		Error e = new Error(ex.getCause().getCause().toString(), ex.getCause().getMessage());
		errors.add(e);
		if (showStackTrace) {
			ArrayList<Error> errorsStackTrace = new ArrayList<>();
			for (StackTraceElement stack : ex.getCause().getStackTrace()) {
				e = new Error(stack.getFileName(), stack.toString());
				errorsStackTrace.add(e);
			}
			res.put("stackTrace", errorsStackTrace);
		}
		res.put("errors", errors);
		return ResponseEntity.status(status).body(res.toMap());
	}
}
