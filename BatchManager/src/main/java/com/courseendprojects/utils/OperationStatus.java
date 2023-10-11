package com.courseendprojects.utils;

public class OperationStatus {
    private boolean isSuccess;
    private String message;
    private String action;
	
    public boolean isSuccess() {
		return isSuccess;
	}
    
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}