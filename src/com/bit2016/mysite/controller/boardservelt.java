package com.bit2016.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.action.board.BoardMainFactory;
import com.bit2016.mysite.action.guestbook.GuestMainFactory;
import com.bit2016.web1.Action;
import com.bit2016.web1.Actionfactory;

/**
 * Servlet implementation class boardservelt
 */
@WebServlet("/board")
public class boardservelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String actionName=request.getParameter("a");
		Actionfactory af=new BoardMainFactory();
		Action action=af.getAction(actionName);
		
		action.execute(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
