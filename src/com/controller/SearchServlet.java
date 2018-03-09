package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.CountryDTO;
import com.exception.MyException;
import com.service.CountryService;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String word = (String) request.getParameter("search");
    	HashMap<String, Object> map = new HashMap<>();
    	map.put("cname", word);
    	map.put("limit", 10);
    	
    	CountryService cService = new CountryService();
    	List<CountryDTO> cList = null;
    	
    	try {
    		cList = cService.countrySelectListByCname(map);
			
			if(cList == null) {
				map.put("cename", word);
				cList = cService.countrySelectListByCename(map);
			}
			
			request.setAttribute("cList", cList);
		} catch (MyException e) {
			e.printStackTrace();
		}
    	
    	RequestDispatcher dis = request.getRequestDispatcher("search.jsp");
		dis.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
