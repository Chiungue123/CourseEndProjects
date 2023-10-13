package com.courseendprojects.model;

public class BatchSignUp {
	
	// Attributes
	Integer batchID;
	Integer participantID;
	
	// Getters & Setters
	public Integer getBatchID() {
		return batchID;
	}
	
	public void setBatchID(Integer batchID) {
		this.batchID = batchID;
	}
	
	public Integer getParticipantID() {
		return participantID;
	}
	
	public void setParticipantID(Integer participantID) {
		this.participantID = participantID;
	}
	
	@Override
	public String toString() {
		return "BatchSignUp [batchID=" + batchID + ", participantID=" + participantID + "]";
	}

}
