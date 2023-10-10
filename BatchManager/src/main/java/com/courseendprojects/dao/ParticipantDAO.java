package com.courseendprojects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.courseendprojects.db.Database;
import com.courseendprojects.model.Participant;

public class ParticipantDAO {
	
	private String url = System.getenv("DB_URL");
	private String user = System.getenv("DB_USER");
	private String pass = System.getenv("DB_PASS");
	
	Database db = new Database();
	
	// TODO: Create Participant
	public int createParticipant(Participant participant) {
		
		int rowsAffected = 0;
		String sql = "INSERT INTO PARTICIPANT (id, name, email)"
					+"VALUES(null,?,?)";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(1, participant.getName());
			prst.setString(2, participant.getEmail());
			
			db.executeUpdate(prst);
			
		} catch (SQLException e) {
			System.out.println("Error Creating Participant: "+  e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	// TODO: Read Participant
	public ResultSet getParticipants() {
		
		ResultSet result = null;
		String sql = "SELECT * FROM PARTICIPANT";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			result = db.executeQuery(prst);
			System.out.println("getParticipants executed.");
			System.out.println("ResultSet: " + result);
			
		} catch (SQLException e) {
			System.out.println("Error Getting Participants: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// TODO: Update Participant
	public int updateParticipant(int id, Participant newParticipant) {
		int rowsAffected = 0;
		String sql = "UPDATE PARTICIPANT SET name = ?, email = ? WHERE id = ?";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
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
		int rowsAffected = 0;
		String sql = "DELETE PARTICIPANT WHERE id = ?";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setInt(1, id);
			rowsAffected = prst.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error Deleting Participant: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("deleteParticipant: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
}
