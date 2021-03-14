package com.sophie.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sophie.util.RequestHelper;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// we will rewrite the URL's 
		final String URI = request.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
		case "login":
			RequestHelper.processLogin(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "employees":
			RequestHelper.processEmployees(request, response);
			break;
		case "viewallforemployee":
			RequestHelper.processViewReimbursementRequestsForEmployee(request, response);
			break;
		case "viewpendingforemployee":
			RequestHelper.processViewPendingReimbursementRequestsForEmployee(request, response);
			break;
		case "viewresolvedforemployee":
			RequestHelper.processViewResolvedReimbursementRequestsForEmployee(request, response);
			break;
		case "viewall":
			RequestHelper.processViewReimbursementRequests(request, response);
			break;
		case "viewpending":
			RequestHelper.processViewPendingReimbursementRequests(request, response);
			break;
		case "viewresolved":
			RequestHelper.processViewResolvedReimbursementRequests(request, response);
			break;
		case "error":
			RequestHelper.processError(request, response);
			break;
		case "viewinfo":
			RequestHelper.processViewInfo(request, response);
			break;
		case "updateinfo":
			RequestHelper.processUpdateInfo(request, response);
			break;
		case "postreimbursement":
			RequestHelper.processPostReimbursement(request, response);
			break;
		} 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}