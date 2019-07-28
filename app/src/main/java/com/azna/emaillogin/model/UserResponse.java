package com.azna.emaillogin.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}