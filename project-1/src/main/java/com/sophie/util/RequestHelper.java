package com.sophie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophie.models.Employee;
import com.sophie.models.LoginTemplate;
import com.sophie.services.EmployeeService;

public class RequestHelper {

	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest req, HttpServletResponse response) throws IOException {
		//We want to turn the request into a string to process.
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		
		//Logic to transfer from reader to stringbuilder.
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		
		String body = sb.toString();
		log.info(body);
		
		//Building a model called LoginTemplate which holds a Username and Password.
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); //From JSON to Java.
		
		//String u = req.getParameter("username");
		//String p = req.getParameter("password");
		
		String uName = loginAttempt.getUsername();
		String pwd = loginAttempt.getPassword();
		
		log.info("User attempted to login with username: " + uName);
		Employee e = EmployeeService.confirmLogin(uName, pwd);
		if (e != null) {
			//Get the current session, or create one if it doesn't exist yet.
			HttpSession session = req.getSession();
			session.setAttribute("username", uName);
			session.setAttribute("password", pwd);
			
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			
			
			
			RequestDispatcher rd = req.getRequestDispatcher("home.html");
			try {
				rd.forward(req, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
			
			log.info(uName + " has successfully logged in!");
		}
		else {
			response.setStatus(204);
		}
		
	}
	
	public static void processLogout(HttpServletRequest req, HttpServletResponse response) throws IOException {
		HttpSession session = req.getSession(false);
		return;
	}
	
	public static void processEmployees(HttpServletRequest req, HttpServletResponse response) throws IOException {
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		List<Employee> allEmps = EmployeeService.findAll();
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allEmps);
		//
		PrintWriter pw = response.getWriter();
		pw.println(json);		
		return;
	}
	
	public static void main(String[] args) {
		log.info(EmployeeService.findAll());
	}
	}
