package com.bit2016.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.vo.UserVo;
import com.bit2016.mysite3.dao.UserDao;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		//String no=request.getParameter("no");//no는 int형으로 선언해주었는데 뭔지 모르겠는데 string으로 받는데..
		HttpSession session = request.getSession();
		UserVo vo=(UserVo) session.getAttribute("authUser");
		
		UserDao dao=new UserDao();
		//vo.setNo(Integer.parseInt(no));//23번쨰 줄의 이유로 integer.parint(no)로 해준다.
		vo.setNo(vo.getNo());
		vo.setName(name);
		vo.setPasswd(password);
		vo.setGender(gender);
		vo=dao.update(vo);
		
		vo.setName(name);
		session.setAttribute("authUser",vo );
		
		
		
		WebUtil.redirect(request, response, "/mysite3/main" );
		
		
		
	}

}
