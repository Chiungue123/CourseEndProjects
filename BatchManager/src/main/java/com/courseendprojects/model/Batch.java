package com.courseendprojects.model;

import java.util.*;

public class Batch {

	// Declare Variables
	private Integer batchID;
	private String name = "";
	private List<Participant> participants = new ArrayList<>();
	private String timeSlot = "";
	
	public Batch() {
	
	}
	
	public Batch(ArrayList<Participant> participants, String name, String timeSlot, Integer batchID) {
		this.participants = participants;
		this.name = name;
		this.timeSlot = timeSlot;
		this.batchID = batchID;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Integer getBatchID() {
		return batchID;
	}

	public void setBatchID(Integer batchID) {
		this.batchID = batchID;
	}

	@Override
	public String toString() {
		return "Batch [batchID=" + batchID + ", name=" + name + ", participants=" + participants + ", timeSlot="
				+ timeSlot + "]";
	}
}