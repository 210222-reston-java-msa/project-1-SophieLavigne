package com.sophie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophie.models.Employee;
import com.sophie.models.LoginTemplate;
import com.sophie.models.Reimbursement;
import com.sophie.services.EmployeeService;
import com.sophie.services.ReimbursementService;

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
		
		String uName = loginAttempt.getUsername();
		String pwd = loginAttempt.getPassword();
		
		log.info("User attempted to login with username: " + uName);
		Employee e = EmployeeService.confirmLogin(uName, pwd);
		if (e != null) {
			log.info("Found an employee with specified username and password!");
			int role_id = e.getRole_id();
			
			Cookie ck = new Cookie("role_id", String.valueOf(role_id));
			//Get the current session, or create one if it doesn't exist yet.
			HttpSession session = req.getSession();
			response.addCookie(ck);
			session.setAttribute("username", uName);
			session.setAttribute("password", pwd);
			session.setAttribute("role_id", role_id);
			log.info("Role is ID#: " + session.getAttribute("role_id"));
			
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			log.info("{\"user\": {\"uName\":" + uName + ", \"pass\":" + pwd + ",\"role_id\":" + role_id + "}}");
			pw.write("{\"user\": {\"uName\":" + uName + ", \"pass\":" + pwd + ",\"role_id\":" + role_id + "}}");
			RequestDispatcher rd = req.getRequestDispatcher("/reimbursements.html"); 
			
			try {
				log.info("Attempting forward!");
				rd.forward(req, response);
				log.info(uName + " has successfully logged in!");
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
		else {
			response.setStatus(204);
		}
		
	}
	
	public static void processLogout(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to log out!");
		HttpSession session = req.getSession(false);
		if (session != null) {
			log.info("Session is not null!");
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("role_id");
			session.invalidate();
			
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json");
			RequestDispatcher rd = req.getRequestDispatcher("/index.html");
			try {
				log.info("Sending forward to homepage!");
				rd.forward(req, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	public static void processViewReimbursementRequestsForEmployee(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to view all of an employee's reimbursement requests.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		HttpSession session = req.getSession();
		String uName = session.getAttribute("username").toString();
		Employee e = EmployeeService.findByUsername(uName);
		List<Reimbursement> allReimE = ReimbursementService.findAllForEmployee(e);
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allReimE);
		//Send the JSON String back.
		PrintWriter pw = response.getWriter();
		pw.println(json);
		return;
	}
	
	public static void processViewPendingReimbursementRequestsForEmployee(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Received request by employee to view pending reimbursement requests.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		HttpSession session = req.getSession();
		String uName = session.getAttribute("username").toString();
		Employee e = EmployeeService.findByUsername(uName);
		List<Reimbursement> allReimPE = ReimbursementService.findAllPendingForEmployee(e);
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allReimPE);
		//Send the JSON String back.
		PrintWriter pw = response.getWriter();
		pw.println(json);
		return;
	}
	
	public static void processViewResolvedReimbursementRequestsForEmployee(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to view an employee's resolved reimbursement requests.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		HttpSession session = req.getSession();
		String uName = session.getAttribute("username").toString();
		Employee e = EmployeeService.findByUsername(uName);
		List<Reimbursement> allReimRE = ReimbursementService.findAllResolvedForEmployee(e);
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allReimRE);
		//Send the JSON String back.
		PrintWriter pw = response.getWriter();
		pw.println(json);		
		return;
	}
	
	public static void processViewReimbursementRequests(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to view all reimbursement requests.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		List<Reimbursement> allReim = ReimbursementService.findAll();
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allReim);
		//Send the JSON String back.
		PrintWriter pw = response.getWriter();
		pw.println(json);		
		return;
	}
	
	public static void processViewPendingReimbursementRequests(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Received request to view all pending reimbursement requests.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		List<Reimbursement> allReimP = ReimbursementService.findAllPending();
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allReimP);
		//Send the JSON String back.
		PrintWriter pw = response.getWriter();
		pw.println(json);		
		return;
	}
	
	public static void processViewResolvedReimbursementRequests(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to view all resolved reimbursement requests.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Get list of all employees in DB.
		List<Reimbursement> allReimR = ReimbursementService.findAllResolved();
		//Turn list of Employees into a JSON string.
		String json = om.writeValueAsString(allReimR);
		//Send the JSON String back.
		PrintWriter pw = response.getWriter();
		pw.println(json);		
		return;
	}
	
	public static void processEmployees(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to view all employees.");
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
	
	public static void processError(HttpServletRequest req, HttpServletResponse response) throws IOException {
		try {
			req.getRequestDispatcher("error.html").forward(req, response);
			// we do NOT create a new request
			// we also maintain the url....
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void processViewInfo(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to view an employee's info.");
		//Set content type to application/json.
		response.setContentType("application/json");
		//Find the employee.
		HttpSession session = req.getSession();
		String uName = session.getAttribute("username").toString();
		Employee e = EmployeeService.findByUsername(uName);
		//Return the employee as a JSON object.
		String json = om.writeValueAsString(e);
		PrintWriter pw = response.getWriter();
		pw.println(json);
		log.info(json);
		return;
	}

	public static void processUpdateInfo(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to update an employee's info.");
		//Set content type to application/json.
				response.setContentType("application/json");
				HttpSession session = req.getSession();
				String uName = session.getAttribute("username").toString();
				Employee e = EmployeeService.findByUsername(uName);
				/*
				 * The back half of this method involves taking input from the request and updating elements in the database accordingly.
				 */
				return;
			}

	public static void processPostReimbursement(HttpServletRequest req, HttpServletResponse response) throws IOException {
		log.info("Attempting to post a reimbursement request.");
		//Set content type to application/json.
				response.setContentType("application/json");
				HttpSession session = req.getSession();
				String uName = session.getAttribute("username").toString();
				Employee e = EmployeeService.findByUsername(uName);
				/*
				 * The back half of this method involves taking input from the request and creating an element in the database accordingly.
				 */
				return;
			}
	
	/*
	 * public static void main(String[] args) {

		log.info(EmployeeService.findAll());
	}
	*/
	}
