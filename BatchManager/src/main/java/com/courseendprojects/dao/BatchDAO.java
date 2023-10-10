package com.courseendprojects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.courseendprojects.db.Database;
import com.courseendprojects.model.Batch;

public class BatchDAO {
	
	private String url = System.getenv("DB_URL");
	private String user = System.getenv("DB_USER");
	private String pass = System.getenv("DB_PASS");
	
	Database db = new Database();
	
	// TODO: Create Batch
	public int createBatch(Batch batch) {
		int rowsAffected = 0;
		String sql = "INSERT INTO BATCH (batchID, name, timeSlot)"
					+"VALUES(null,?,?)";
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(2, batch.getName());
			prst.setString(3, batch.getTimeSlot());
			
			rowsAffected = db.executeUpdate(prst);
			
		} catch(SQLException e) {
			System.out.println("Error Creating Branch: "+  e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("addBatch: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
	
	// TODO: Read Batch
	public ResultSet getBatches () {
		ResultSet result = null;
		String sql = "SELECT * FROM BATCH";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			result = prst.executeQuery();
			System.out.println("getBatches executed.");
			System.out.println("ResultSet: " + result);
					
		} catch (SQLException e) {
			System.out.println("Error Getting Batches: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// TODO: Update Batch
	public int updateBatch(int batchID, Batch newBatch) {
		int rowsAffected = 0;
		String sql = "UPDATE BATCH SET name = ?, timeSlot = ? WHERE batchID = ?";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(1, newBatch.getName());
			prst.setString(2, newBatch.getTimeSlot());
			prst.setInt(3, batchID);
			
			rowsAffected = prst.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error Updating Batch!");
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("updateBatch: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
	
	// TODO: Delete Batch
	public int deleteBatch(int batchID) {
		int rowsAffected = 0;
		String sql = "DELETE BATCH WHERE batchID = ?";
		
		try (Connection con = DriverManager.getConnection(url, user, pass);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setInt(1, batchID);
			rowsAffected = prst.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error Deleting Batch!");
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("deleteBatch: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
}
