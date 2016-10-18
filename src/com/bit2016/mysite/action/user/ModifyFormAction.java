package com.bit2016.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.vo.UserVo;
import com.bit2016.mysite3.dao.UserDao;
import com.bit2016.web1.Action;
import com.bit2016.web.util.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );//세션 유지된 번호랑 이메일 넘어옴
		
		UserVo userVo = new UserDao().get( authUser.getNo() );//다 넘어옴
	
		request.setAttribute( "userVo", userVo );
		WebUtil.forward(
			request, 
			response,
			"/WEB-INF/views/user/modifyform.jsp");
	}

}