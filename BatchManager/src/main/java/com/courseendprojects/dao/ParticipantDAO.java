package com.courseendprojects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courseendprojects.db.Database;
import com.courseendprojects.model.Batch;
import com.courseendprojects.model.Participant;

public class ParticipantDAO {
	
	// private final static String URL = System.getenv("DB_URL");
	// private final static String USER = System.getenv("DB_USER");
	// private final static String PASS = System.getenv("DB_PASS");
	
	private final static String URL = "jdbc:mysql://localhost:3306/java_projects";
	private final static String USER = "root";
	private final static String PASS = "root1";
	
	Database db = new Database();
	
	// TODO: Create Participant
	public int createParticipant(Participant participant) {
		
		int rowsAffected = 0;
		String sql = "INSERT INTO PARTICIPANT (id, name, email) "
					+"VALUES(null,?,?)";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(1, participant.getName());
			prst.setString(2, participant.getEmail());
			
			rowsAffected = db.executeUpdate(prst);
			
		} catch (SQLException e) {
			System.out.println("ParticipantDAO: Error Creating Participant: "+  e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	// TODO: Get ParticipantByID
	public Participant getParticipantByID(int id) {
		
		ResultSet result = null;
		String sql = "SELECT * FROM PARTICIPANT WHERE ID = ?";
		Participant participant = new Participant();
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
				
				prst.setInt(1, id);
				result = db.executeQuery(prst);
            
            	participant.setId(result.getInt("id"));
            	participant.setName(result.getString("name"));
            	participant.setEmail(result.getString("email"));
				
			} catch (SQLException e) {
				System.out.println("Error Getting Participants: " + e);
				System.out.print("Stack Trace: ");
				e.printStackTrace();
			}
		
		return participant;
	}
	
	// TODO: Read Participant
	public List<Participant> getParticipants() {
		
		List<Participant> participants = new ArrayList<>();
		ResultSet result = null;
		String sql = "SELECT * FROM PARTICIPANT";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			result = db.executeQuery(prst);
			while (result.next()) {
            	Participant participant = new Participant();
            	participant.setId(result.getInt("id"));
            	participant.setName(result.getString("name"));
            	participant.setEmail(result.getString("email"));
				participants.add(participant);
			}
			
		} catch (SQLException e) {
			System.out.println("Error Getting Participants: " + e);
			System.out.print("Stack Trace: ");
			e.printStackTrace();
		}
		
		return participants;
	}
	
	// TODO: Update Participant
	public int updateParticipant(int id, Participant newParticipant) {
		int rowsAffected = 0;
		String sql = "UPDATE PARTICIPANT SET name = ?, email = ? WHERE id = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(1, newParticipant.getName());
			prst.setString(2, newParticipant.getEmail());
			prst.setInt(3, id);
			
			rowsAffected = prst.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error Updating Participant: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("updateParticipant: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
	
	// TODO: Delete Participant
	public int deleteParticipant(int id) {
		
		System.out.println("Deleting Participant with Id: " + id);
		int rowsAffected = 0;
		String sql = "DELETE FROM PARTICIPANT WHERE id = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setInt(1, id);
			rowsAffected = prst.executeUpdate();
			System.out.println("updateCustomer executed");
			
		} catch(SQLException e) {
			System.out.println("Error Deleting Participant: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("deleteParticipant: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
}