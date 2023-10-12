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
import com.courseendprojects.utils.ErrorStatus;

public class BatchDAO {
	
	private final static String URL = System.getenv("DB_URL");
	private final static String USER = System.getenv("DB_USER");
	private final static String PASS = System.getenv("DB_PASS");
	
	Database db = new Database();
	
	// TODO: Create Batch
	public int createBatch(Batch batch) {
		int rowsAffected = 0;
		String sql = "INSERT INTO BATCH (batchID, name, timeSlot)"
					+"VALUES(null,?,?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(1, batch.getName());
			prst.setString(2, batch.getTimeSlot());
			
			System.out.println("Query: " + prst);
			rowsAffected = db.executeUpdate(prst);
			
		} catch(SQLException e) {
			System.out.println("Error Creating Branch: "+  e);
			e.printStackTrace();
		}
		
		//System.out.println("addBatch: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
	
	// TODO: Read Batch
	public List<Batch> getBatches () {
		List<Batch> batches = new ArrayList<>();
		ResultSet result = null;
		String sql = "SELECT * FROM BATCH";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			System.out.println("Query: " + prst);
			result = prst.executeQuery();
			while (result.next()) {
            	Batch batch = new Batch();
            	batch.setBatchID(result.getInt("batchID"));
            	batch.setName(result.getString("name"));
            	batch.setTimeSlot(result.getString("timeSlot"));
				batches.add(batch);
        }
					
		} catch (SQLException e) {
			System.out.println("Error Getting Batches: " + e);
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		return batches;
	}
	
	// TODO: Update Batch
	public int updateBatch(int batchID, Batch newBatch) {
		int rowsAffected = 0;
		String sql = "UPDATE BATCH SET name = ?, timeSlot = ? WHERE batchID = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setString(1, newBatch.getName());
			prst.setString(2, newBatch.getTimeSlot());
			prst.setInt(3, batchID);
			
			System.out.println("Query: " + prst);
			rowsAffected = prst.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error Updating Batch");
			System.out.println("Stack Trace: ");
			e.printStackTrace();
		}
		
		System.out.println("updateBatch: " + rowsAffected + " rows affected");
		return rowsAffected;
	}
	
	// TODO: Delete Batch
	public int deleteBatch(int batchID) {
		int rowsAffected = 0;
		String sql = "DELETE FROM BATCH WHERE BATCHID = ?";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
			 PreparedStatement prst = con.prepareStatement(sql)) {
			
			prst.setInt(1, batchID);
			System.out.println("Query: " + prst);
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
