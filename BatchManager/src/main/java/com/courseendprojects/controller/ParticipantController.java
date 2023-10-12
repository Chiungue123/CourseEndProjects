package com.courseendprojects.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courseendprojects.dao.ParticipantDAO;
import com.courseendprojects.model.Participant;
import com.courseendprojects.utils.ErrorStatus;
import com.courseendprojects.utils.OperationStatus;

@WebServlet("/Participant")
public class ParticipantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("Create".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("createParticipant.jsp").forward(request, response);
			
		} else if ("View".equals(action)) {
			
			// Get Participants & Set Attribute
			ParticipantDAO participantDAO = new ParticipantDAO();
			List<Participant> participants = participantDAO.getParticipants();
			request.setAttribute("participants", participants);
			
			// Forward to JSP
			request.getRequestDispatcher("viewParticipant.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("updateParticipant.jsp").forward(request, response);
			
		} else if ("Delete".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("deleteParticipant.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		int rowsAffected = 0;
		
		if ("Create".equals(action)) {
			
			// Get Parameters
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			// Create Participant
			Participant participant = new Participant();
			participant.setName(name);
			participant.setEmail(email);
			
			// Execute update
			try {
				System.out.println("Participant: " + participant.toString());
				ParticipantDAO participantDAO = new ParticipantDAO();
				rowsAffected = participantDAO.createParticipant(participant);
				System.out.println("Rows Affected: " + rowsAffected);
				
				
			} catch(Exception e) {
				System.out.println("Error Creating Participant");
				System.out.println("Error: " + e);
			}
			
			if (rowsAffected > 0) {
				// Create OperationStatus object
				OperationStatus status = new OperationStatus();
				status.setSuccess(true);
				status.setAction("Create Participant");
				status.setMessage("Participant Successfully Created: " + participant.toString());
				
				// Forward to JSP
				request.setAttribute("status", status);
				request.getRequestDispatcher("result.jsp").forward(request, response);
				
			} else {
				// Create ErrorStatus Object
				ErrorStatus error = new ErrorStatus();
				error.setAction("Create Participant");
				error.setErrorMessage("Failed, Rows Affected: " + rowsAffected);
				
				// Forward to JSP
				request.setAttribute("errorStatus", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			
		} else if ("View".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			// Get Parameters
			String participantID = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			// Convert String to Integer
			Integer id = Integer.parseInt(participantID);
			
			// Create Participant Object
			Participant newParticipant = new Participant();
			newParticipant.setName(name);
			newParticipant.setEmail(email);
			
			// Execute Update
			try {
				ParticipantDAO participantDAO = new ParticipantDAO();
				rowsAffected = participantDAO.updateParticipant(id, newParticipant);
				System.out.println("Rows Affected: " + rowsAffected);
				
			} catch(Exception e) {
				System.out.println("Error Updating Participant");
				System.out.println("Error: " + e);
				
			}
			
			if (rowsAffected > 0) {
				// Create OperationStatus Object
				OperationStatus status = new OperationStatus();
				status.setSuccess(true);
				status.setAction("Update Participant");
				status.setMessage("Participant Successfully Updated");
				
				// Forward to JSP
				request.setAttribute("status", status);
				request.getRequestDispatcher("result.jsp").forward(request, response);
				
			} else {
				System.out.println("Create Participant Unsuccessfull. Rows Affected: " + rowsAffected);
				// Create ErrorStatus Object
				ErrorStatus error = new ErrorStatus();
				error.setAction("Update Participant");
				error.setErrorMessage("Failed, Rows Affected: " + rowsAffected);
				
				// Forward to JSP
				request.setAttribute("errorStatus", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
		} else if ("Delete".equals(action)) {
			
			// Get Parameters
			String participantID = request.getParameter("id");
			
			// Convert String to int
			int id = Integer.parseInt(participantID);
			
			// Execute Update 
			try {
				ParticipantDAO participantDAO = new ParticipantDAO();
				rowsAffected = participantDAO.deleteParticipant(id);
				System.out.println("Rows Affected: " + rowsAffected);
				
			} catch(Exception e) {
				System.out.println("Error Deleting Participant");
				System.out.println("Error: " + e);
				
			}
			
			if (rowsAffected > 0) {
				// Create ErrorStatus Object
				OperationStatus status = new OperationStatus();
				status.setSuccess(true);
				status.setAction("Delete Participant");
				status.setMessage("Participant Successfully Deleted");
				
				// Forward to JSP
				request.setAttribute("status", status);
				request.getRequestDispatcher("result.jsp").forward(request, response);
				
			} else {
				// Create ErrorStatus Object
				ErrorStatus error = new ErrorStatus();
				error.setAction("Delete Participant");
				error.setErrorMessage("Failed, Rows Affected: " + rowsAffected);
				
				// Forward to JSP
				request.setAttribute("errorStatus", error);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}

}
