package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.vo.UserVo;
import com.bit2016.mysite3.dao.UserDao;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();//세션을 받아온다
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );//세션 유지된 번호랑 이메일 넘어옴
		
		
		if(authUser==null){//로그인 안되어있을때 글쓰기 창으로 못간다
			WebUtil.redirect(request, response, "/mysite3/board");
			return ;
		}
		
		WebUtil.forward(request,response, "/WEB-INF/views/board/write.jsp");
	}

}
