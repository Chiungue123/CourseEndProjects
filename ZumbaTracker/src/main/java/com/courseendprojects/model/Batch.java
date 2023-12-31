package com.courseendprojects.model;

import java.util.*;

public class Batch {

	private ArrayList<Participant> participants;
	private String name;
	private String timeSlot;
	private Integer batchID;
	
	public Batch() {
		
	}
	
	public Batch(ArrayList<Participant> participants, String name, String timeSlot, Integer batchID) {
		this.participants = participants;
		this.name = name;
		this.timeSlot = timeSlot;
		this.batchID = batchID;
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Participant> participants) {
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
	
}
