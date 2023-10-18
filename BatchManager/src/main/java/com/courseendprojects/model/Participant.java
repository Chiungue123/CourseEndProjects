package com.courseendprojects.model;

import java.util.*;

public class Participant {
	
	// Declare Variables
	private Integer id;
	private String name = "";
	private List<Batch> batches = new ArrayList<>();
	private String email = "";
	
	// Constructors
	public Participant(){
		
	}
	
	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + ", batches=" + batches + ", email=" + email + "]";
	}
	
	public String display() {
		return "ID: " + id + ", Name: " + name;
	}

	Participant(String name, List<Batch> batches, Integer id, String email){
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
	
	public List<Batch> getBatches() {
		return batches;
	}
	
	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

