package com.courseendprojects.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Participant")
public class ParticipantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("Create".equals(action)) {
			
			/*
			 * Preprocessing
			 * - None, ensure validation logic is in place to 
			 * validate data and to prevent duplicate values 
			 */
			
			
			// Forward to JSP
			request.getRequestDispatcher("createParticipant.jsp").forward(request, response);
			
		} else if ("View".equals(action)) {
			
			/*
			 * Preprocessing
			 * - All participants and their assigned batches
			 */
			
			// Forward to JSP
			request.getRequestDispatcher("viewsParticipant.jsp").forward(request, response);
			
		} else if ("Update".equals(action)) {
			
			/*
			 * Preprocessing
			 * - Idea, create an additional jsp layer for Update operations to prompt for 
			 * an id number either through a single input field or a drop down menu 
			 * User will then be redirected to the doPost method where the update will be executed
			 * - Idea, retrieve the participant IDs in this method and create a drop down menu on
			 *  the jsp file below. Upon submitting, the doPost method will be involked,
			 *  execuing the appropriate Database and DAO methods
			 *  
			 *  Idea, delete by name or id number, offering flexibility
			 */
			
			// Forward to JSP
			request.getRequestDispatcher("updateParticipant.jsp").forward(request, response);
			
		} else if ("Delete".equals(action)) {
			
			/*
			 * Idea, retrieve the participant IDs in this method and create a drop down menu on
			 *  the jsp file below. Upon submitting, the doPost method will be involked,
			 *  execuing the appropriate Database and DAO methods
			 *  
			 *  Idea, update by name or id number, offering flexibility
			 */
			
			// Forward to JSP
			request.getRequestDispatcher("deleteParticipant.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("Create".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("result.jsp").forward(request, response);
			
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
