package com.sophie.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sophie.models.Employee;
import com.sophie.services.EmployeeService;

import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private static Logger log = Logger.getLogger(LoginServlet.class);
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 
		 * We'll have an index.html with a form/login section and on clicking the button, it'll send a POST to THIS servlet.
		 * 
		 * Handle parameters from index.
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		
		log.info("User attempted to login with username: " + username);
		Employee e = EmployeeService.confirmLogin(username, password);
		
		if (e != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("username", username);
			
			RequestDispatcher rd = request.getRequestDispatcher("home.html");
			
			rd.forward(request, response);
			log.info(username + " has successfully logged in.");
		} else {
			log.info("Login attempt failed!");
		}
	}

}
