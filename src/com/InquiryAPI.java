package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InquiryAPI")
public class InquiryAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Inquiry InquiryObj = new Inquiry();
       

    public InquiryAPI() {
        super();
        
    }

    //API Method for Get Data
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//API Method for Insert Data
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = InquiryObj.insertInquiry(
				request.getParameter("hidItemIDSave"),
				request.getParameter("full_name"),
				request.getParameter("phone_no"),
				request.getParameter("email"),
				request.getParameter("address"),
				request.getParameter("description")
				);
response.getWriter().write(output);
	}


	//API Method for Update the Data
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Map paras = getParasMap(request); 
		String output = InquiryObj.updateInquiry(paras.get("hidItemIDSave").toString(), 
												paras.get("full_name").toString(), 
												paras.get("phone_no").toString(),
												paras.get("email").toString(),  
												paras.get("address").toString(),  
												paras.get("description").toString()
												//paras.get("sector").toString(),
												); 
		response.getWriter().write(output); 
	}


	//API Method for Delete Data
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	Map paras = getParasMap(request); 
		
		String output = InquiryObj.deleteInquiry(paras.get("inquiry_id").toString()); 
		response.getWriter().write(output);
	}
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) {
			
			Map<String, String> map = new HashMap<String, String>(); 
			try{ 
				
				 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				 String queryString = scanner.hasNext() ? 
				 scanner.useDelimiter("\\A").next() : ""; 
				 scanner.close(); 
				 String[] params = queryString.split("&"); 
				 for (String param : params) {
					 
					 String[] p = param.split("=");
					 map.put(p[0], p[1]); 
				 } 
			}catch (Exception e) { 
				
			} 
			return map; 
		}

}
