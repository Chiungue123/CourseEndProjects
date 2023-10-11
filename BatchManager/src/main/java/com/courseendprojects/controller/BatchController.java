package com.courseendprojects.controller;

import java.io.IOException;
import java.sql.ResultSet;

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
			System.out.println("doGet");
			System.out.println("Action: " + action);
			
			// Forward to JSP
			request.getRequestDispatcher("createBatch.jsp").forward(request, response);
			
		} else if ("View".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("viewsBatch.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("updateBatch.jsp").forward(request, response);
			
		} else if ("Delete".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("deleteBatch.jsp").forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		int rowsAffected = 0;
		
		if ("Create".equals(action)) {
			// Create Batch and Operation Status object
			OperationStatus status = new OperationStatus();
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
				
			} else {
				
				status.setSuccess(false);
				status.setAction("Create Batch");
				status.setMessage("Invalid Input");				
				request.setAttribute("status", status);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
		} else if ("View".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
		} else if ("Delete".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
		}
	}

}
