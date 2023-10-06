package com.courseendprojects.model;

import java.util.*;


public class Participant {
	// Declare Variables
	private String name;
	private ArrayList<Batch> batches;
	
	// Constructors
	Participant(){
		
	}
	
	Participant(String name, ArrayList<Batch> batches){
		this.name = name;
		this.batches = batches;
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
}
