package com.courseendprojects.model;

import java.util.*;


public class Participant {
	
	// Declare Variables
	private String id;
	private String name;
	private ArrayList<Batch> batches;
	private String email;
	
	// Constructors
	Participant(){
		
	}
	
	Participant(String name, ArrayList<Batch> batches, String id, String email){
		this.name = name;
		this.batches = batches;
		this.id = id;
		this.email = email;
	}
	
	// Getters & Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Batch> getBatches() {
		return batches;
	}
	
	public void setBatches(ArrayList<Batch> batches) {
		this.batches = batches;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

