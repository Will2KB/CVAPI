package com.WB.API.dto;

import java.time.LocalDateTime;

public class ApiError {

	private int status;
	private String error;
	private String path;
	private LocalDateTime timestamp;

	public ApiError(int status, String error, String path) {
		this.status = status;
		this.error = error;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
