package com.WB.API.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecaptchaResponse {

	@JsonProperty("isSuccess")
	private boolean success;

	@JsonProperty("score")
	private float score;

	@JsonProperty("action")
	private String action;

	@JsonProperty("challenge_ts")
	private String challengeTs;

	private String hostname;

	@JsonProperty("error-codes")
	private List<String> errorCodes;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getChallengeTs() {
		return challengeTs;
	}

	public void setChallengeTs(String challengeTs) {
		this.challengeTs = challengeTs;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public List<String> getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}
}
