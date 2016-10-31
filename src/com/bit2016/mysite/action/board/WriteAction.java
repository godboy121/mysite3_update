package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.board.dao.BoardDao;
import com.bit2016.mysite.board.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );//userVo에 있는 글 등록 이름 알기위해 받아온다!!!!중요
		if( session == null ) {
			WebUtil.redirect(request, response, "/mysite3/board" );
			return;
		}
		
		if( authUser == null ) {
			WebUtil.redirect(request, response, "/mysite3/board" );
			return;
		}
		String title=request.getParameter("title");
		String context=request.getParameter("context");
		
		BoardDao dao=new BoardDao();
		BoardVo vo=new BoardVo();
		vo.setTitle(title);
		vo.setContext(context);
		vo.setUserNo( authUser.getNo() );
	
	
		dao.insert(vo);
		
		WebUtil.redirect( request, response, "/mysite3/board" );
		
		

	}

}
