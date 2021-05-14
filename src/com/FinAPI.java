package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinAPI
 */

@WebServlet("/FinAPI")
public class FinAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Fin finObj = new Fin();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("------Insert Successful------");

		String output = finObj.insertFin(
				request.getParameter("Product_category"), 
				request.getParameter("Category_price"), 
				request.getParameter("Tax_rate"),
				request.getParameter("Gross_price")); 
				response.getWriter().write(output);
				
				String jspName = request.getParameter("hidFinIDSave");
				RequestDispatcher rd = request.getRequestDispatcher(jspName);

				rd.forward(request, response);
		}

	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("------Update Successful------");
		Map paras = getParasMap(request); 
		 String output = finObj.updateFin(
 		 paras.get("product_category").toString(), 
		 paras.get("category_price").toString(), 
		 paras.get("tax_rate").toString(),
		 paras.get("gross_price").toString(),
		 Integer.parseInt((String) paras.get("hidFinID")));
		 response.getWriter().write(output); 
	}

		
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = finObj.deleteFin(paras.get("tax_id").toString()); 
		response.getWriter().write(output); 
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}


}
