package com.courseendprojects.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("Participant")
public class ParticipantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("Create".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("createParticipant.jsp").forward(request, response);
			
		} else if ("View".equals(action)) {
			
			// Forward to JSP
			request.getRequestDispatcher("viewsParticipant.jsp").forward(request, response);
			
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
