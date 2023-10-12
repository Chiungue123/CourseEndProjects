package com.courseendprojects.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courseendprojects.dao.BatchDAO;
import com.courseendprojects.model.Batch;
import com.courseendprojects.utils.OperationStatus;
import com.courseendprojects.utils.ErrorStatus;

@WebServlet("/Batch")
public class BatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("Create".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("createBatch.jsp").forward(request, response);
			
		} else if ("View".equals(action)) {
			
			// Get batches, add as an attribute
			BatchDAO batchDAO = new BatchDAO();
			List<Batch> result = batchDAO.getBatches();
			request.setAttribute("batches", result);
			
			System.out.println("List of Batches: " + result);
			// Forward to JSP
			request.getRequestDispatcher("viewBatches.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("updateBatches.jsp").forward(request, response);
			
		} else if ("Delete".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("deleteBatch.jsp").forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		OperationStatus status = new OperationStatus();
		int rowsAffected = 0;
		
		if ("Create".equals(action)) {
			// Create Batch and Operation Status object
			
			Batch batch = new Batch();
			
			try {
				// Fetch parameters
				String name = request.getParameter("name");
				String timeSlot = request.getParameter("timeSlot");
				
				// Validate Parameters
				if (name != null && name.length() > 0 && timeSlot != null && timeSlot.length() > 0) {
					// Set parameters
					batch.setName(name);
					batch.setTimeSlot(timeSlot);
					
					// Print batch
					System.out.println("Creating Batch: " + batch);
					BatchDAO batchDAO = new BatchDAO();
					
					// Execute Update & Set Attributes
					rowsAffected = batchDAO.createBatch(batch);	
					
				}
				
			} catch (Exception e) {
				
				System.out.println("BatchController: Error Creating Batch: " + batch);

				ErrorStatus error = new ErrorStatus();
				error.setAction("Create Batch");
				error.setErrorMessage("Error" + e);
				
				request.setAttribute("errorStatus", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
				
			}
			
			if (rowsAffected > 0) {	
				status.setSuccess(true);
				status.setAction("Create Batch");
				status.setMessage("Batch Succesfully Created: " + batch.toString());	
				request.setAttribute("status", status);
				request.getRequestDispatcher("result.jsp").forward(request, response);
			}
			
		} else if ("View".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			// Get Parameters
			String id = request.getParameter("batchID");	
			String name = request.getParameter("name");
			String timeSlot = request.getParameter("timeSlot");
			
			// Convert String to Integer
			int idInt = Integer.parseInt(id);
			
			// Create new Batch Object
			Batch newBatch = new Batch();
			newBatch.setBatchID(idInt);
			newBatch.setName(name);
			newBatch.setTimeSlot(timeSlot);
			
			// Execute Update
			try {
				BatchDAO batchDAO = new BatchDAO();
				rowsAffected = batchDAO.updateBatch(idInt, newBatch);
				
			} catch(Exception e) {
				ErrorStatus error = new ErrorStatus();
				error.setAction("Update Batch");
				error.setErrorMessage("Error: " + e);
				
				request.setAttribute("errorStatus", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			// Set Operation Status
			if (rowsAffected > 0) {	
				status.setSuccess(true);
				status.setAction("Update Batch");
				status.setMessage("Batch Succesfully Updated. Updated Value: " + newBatch.toString());	
				request.setAttribute("status", status);
				request.getRequestDispatcher("result.jsp").forward(request, response);
			}
			
		} else if ("Delete".equals(action)) {
			
			// Get Parameters
			String batchID = request.getParameter("batchID");
			
			// Convert String to Integer
			int idInt = Integer.parseInt(batchID);
			
			// Execute Update
			try {
				BatchDAO batchDAO = new BatchDAO();
				rowsAffected = batchDAO.deleteBatch(idInt);
				
			} catch(Exception e) {
				System.out.println("BatchController: Error Deleting Branch");
				ErrorStatus error = new ErrorStatus();
				error.setAction("Update Batch");
				error.setErrorMessage("Error: " + e);
				
				request.setAttribute("errorStatus", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			// Set Operation Status
			if (rowsAffected > 0) {	
				status.setSuccess(true);
				status.setAction("Deleted Batch");
				status.setMessage("Batch Succesfully Deleted: ");	
				request.setAttribute("status", status);
				request.getRequestDispatcher("result.jsp").forward(request, response);
			}	
		}
	}

}
