package com.bit2016.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.vo.UserVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ajaxtestServlet
 */
@WebServlet("/ajax")
public class ajaxtestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=utf-8");//json 타입으로 받겠다는 뜻? 
		
		List<UserVo> list=new ArrayList<UserVo>();
		
		UserVo vo=new UserVo();
		vo.setNo(10);
		vo.setName("남달우");
		vo.setEmail("darwoo1223@gmail.com");
		vo.setGender("male");
		list.add(vo);
		
		UserVo vo2=new UserVo();
		vo2.setNo(11);
		vo2.setName("준우");
		vo2.setEmail("junwoo@gmail.com");
		vo2.setGender("male");
		list.add(vo2);
		
		
		
		
		//JSONObject jsonObject=JSONObject.fromObject(vo);//자바객채를 json객채로변환
		JSONArray jsonArray=JSONArray.fromObject(list);//리스트를 jsonArray형식으로 변환.
		PrintWriter out=response.getWriter();
		//out.println(jsonObject.toString());//그 객체를 스트링으로 출력
		out.println(jsonArray.toString());//그 객체를 스트링으로 출력(리스트출력)
	
				
				
				
				

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
