package com.courseendprojects.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courseendprojects.dao.BatchSignUpDAO;
import com.courseendprojects.dao.ParticipantDAO;
import com.courseendprojects.model.Participant;
import com.courseendprojects.utils.ErrorStatus;
import com.courseendprojects.utils.OperationStatus;

@WebServlet("/BatchSignup")
public class BatchSignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		request.setAttribute("action", action);
		
		BatchSignUpDAO batchSignUpDao = new BatchSignUpDAO();
		ParticipantDAO participantDao = new ParticipantDAO();
		
		Integer id = Integer.parseInt(request.getParameter("batchID"));
		System.out.println("Batch ID: " + id);
		
		List<Participant> allParticipants = participantDao.getParticipants();
		System.out.println("All Participants: " + allParticipants);
		
		List<Integer> enrolledParticipantIDs = batchSignUpDao.getBatchParticipantIDs(id);
		System.out.println("Enrolled Participant IDs: " + enrolledParticipantIDs);
		
		if ("add".equals(action)) {
			List<Participant> filteredParticipants = batchSignUpDao.filterParticipants(id, allParticipants, enrolledParticipantIDs);
			request.setAttribute("participants", filteredParticipants);
		} else if ("remove".equals(action)){
			List<Participant> enrolledParticipants = batchSignUpDao.getEnrolledParticipants(id, allParticipants, enrolledParticipantIDs);
			request.setAttribute("participants", enrolledParticipants);
		}
		
		request.setAttribute("batchID", id);
		request.getRequestDispatcher("batchSignUp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println();
		System.out.println("Batch Sign Up Controller");
		System.out.println("========================");
		
		String action = request.getParameter("action");
		Integer id = Integer.parseInt(request.getParameter("batchID"));
		String[] participantIDs = request.getParameterValues("selectedParticipants");

		ArrayList<Participant> selectedParticipants = new ArrayList<>();
		BatchSignUpDAO batchSignUpDAO = new BatchSignUpDAO();
		ParticipantDAO dao = new ParticipantDAO();
		int[] rows = {};
		
		OperationStatus os = new OperationStatus();
		os.setAction("Batch Sign Up doPost");
		
		
		if (participantIDs != null && participantIDs.length > 0) {
			
			for (String p: participantIDs) {
		    	System.out.println("Participant ID: " + p);
		        Participant participant = dao.getParticipantByID(Integer.parseInt(p));
		        System.out.println(participant);
		        selectedParticipants.add(participant);
		    }
  
		} else {
		    os.setSuccess(false);
		    os.setMessage("No Participant IDs were selected");
		    request.setAttribute("status", os);
		    request.getRequestDispatcher("result.jsp").forward(request, response);
		}
		
		if ("remove".equals(action)) {
			rows = batchSignUpDAO.removeBatchParticipants(id, selectedParticipants);
		}
		else if ("add".equals(action)){
			rows = batchSignUpDAO.scheduleParticipants(id, selectedParticipants);
		}
		
		

		if (rows.length > 0) {
		    os.setSuccess(true);
		    os.setMessage("Rows Affected: " + Arrays.toString(rows));
		    request.setAttribute("status", os);
		    request.getRequestDispatcher("result.jsp").forward(request, response);
		    
		} else {
		    ErrorStatus error = new ErrorStatus();
		    error.setAction("Batch Sign Up doPost");
		    error.setErrorMessage("Rows Affected: " + Arrays.toString(rows));
		    request.setAttribute("errorStatus", error);
		    request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}