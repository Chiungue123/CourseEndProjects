package com.courseendprojects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.courseendprojects.model.Batch;
import com.courseendprojects.model.Participant;

public class BatchSignUpDAO {
		
		// private final static String URL = System.getenv("DB_URL");
		// private final static String USER = System.getenv("DB_USER");
		// private final static String PASS = System.getenv("DB_PASS");
		
		private final static String URL = "jdbc:mysql://localhost:3306/java_projects";
		private final static String USER = "root";
		private final static String PASS = "root1";
	
	public int[] scheduleParticipants(Integer batchID, ArrayList<Participant> selectedParticipants){
		
		int[] rowsAffected = {};
		String sql = "INSERT INTO BATCH_PARTICIPANT (batchID, participantID) "
					+"VALUES(?,?)";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			for (Participant p: selectedParticipants) {
				prst.setInt(1, batchID);
				prst.setInt(2, p.getId());
			
				prst.addBatch();
			}
			
			rowsAffected = prst.executeBatch();
			
		} catch (SQLException e) {
			System.out.println("Error Scheduling Participants: " + e);
			System.out.print("Stack Trace: ");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int[] removeBatchParticipants(int batchID, ArrayList<Participant> selectedParticipants) {
		
		int[] rowsAffected = {};
		String sql = "DELETE FROM BATCH_PARTICIPANT WHERE PARTICIPANTID = ? AND BATCHID = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			for (Participant p: selectedParticipants) {
				prst.setInt(1, p.getId());
				prst.setInt(2, batchID);	
				prst.addBatch();
			}
			
			rowsAffected = prst.executeBatch();
			
		} catch (SQLException e) {
			System.out.println("Error Scheduling Participants: " + e);
			System.out.print("Stack Trace: ");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public List<Participant> filterParticipants(int batchID, List<Participant> allParticipants, List<Integer> enrolledParticipantIDs){
		
		List<Participant> participants = new ArrayList<>();
		
		for (Participant p: allParticipants) {
			if (!enrolledParticipantIDs.contains(p.getId())) {
				participants.add(p);
			}
		}
		
		return participants;
	}
	
	public List<Participant> getEnrolledParticipants(int batchID, List<Participant> allParticipants, List<Integer> enrolledParticipantIDs){
		
		List<Participant> participants = new ArrayList<>();
		
		for (Participant p: allParticipants) {
			if (enrolledParticipantIDs.contains(p.getId())) {
				participants.add(p);
			}
		}
		
		return participants;
	}
	
	public List<Integer> getBatchParticipantIDs(int batchID){
		
		List<Integer> participantIDs = new ArrayList<>();
		ResultSet result = null;
		String sql = "SELECT * FROM BATCH_PARTICIPANT WHERE batchID = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setInt(1, batchID);
			result = prst.executeQuery();
			
			while (result.next()) {
				participantIDs.add(result.getInt("participantID"));
			}
					
		} catch (SQLException e) {
			System.out.println("Error Getting Participants Enrolled in Batch ID " + batchID + ". Error: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return participantIDs;
	}
	
	public List<Participant> getEnrolledParticipants(int batchID, List<Integer> participantIDs){
		
		 List<Participant> participants = new ArrayList<>();
		 String sql = "SELECT p.* FROM PARTICIPANT p INNER JOIN BATCH_PARTICIPANT bp ON p.ID = bp.participantID WHERE bp.batchID = ?";
	
		    try (Connection con = DriverManager.getConnection(URL, USER, PASS);
		         PreparedStatement prst = con.prepareStatement(sql)) {
	
		        prst.setInt(1, batchID);
	
		        try (ResultSet result = prst.executeQuery()) {
		            while (result.next()) {
		            	Participant participant = new Participant();
		            	participant.setId(result.getInt("id"));
		            	participant.setName(result.getString("name"));
		            	participant.setEmail(result.getString("email"));
						participants.add(participant);
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("Error Getting Participants Enrolled in Batch ID " + batchID + ". Error: " + e);
		        e.printStackTrace();
		    }
	
		    return participants;
	}

	public List<Integer> getParticipantBatchIDs(Integer id) {
		
		List<Integer> batchIDs = new ArrayList<>();
		ResultSet result = null;
		String sql = "SELECT * FROM BATCH_PARTICIPANT WHERE participantID = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setInt(1, id);
			result = prst.executeQuery();
			
			while (result.next()) {
				batchIDs.add(result.getInt("batchID"));
			}
					
		} catch (SQLException e) {
			System.out.println("Error Getting Participants Enrolled in Batch ID " + id + ". Error: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return batchIDs;
	}

	public List<Batch> getEnrolledBatches(Integer participantID, List<Integer> batchIDs) {
		List<Batch> batches = new ArrayList<>();
	    String sql = "SELECT b.* FROM BATCH b INNER JOIN BATCH_PARTICIPANT bp ON b.batchID = bp.batchID WHERE bp.participantID = ?";

	    try (Connection con = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement prst = con.prepareStatement(sql)) {

	        prst.setInt(1, participantID);

	        try (ResultSet rs = prst.executeQuery()) {
	            while (rs.next()) {
	                Batch batch = new Batch();
	                batch.setBatchID(rs.getInt("batchID"));
	                batch.setName(rs.getString("name"));
	                batch.setTimeSlot(rs.getString("timeSlot"));
	                batches.add(batch);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error Getting Batches for Participant ID " + participantID + ". Error: " + e);
	        e.printStackTrace();
	    }

	    return batches;
	}	
}