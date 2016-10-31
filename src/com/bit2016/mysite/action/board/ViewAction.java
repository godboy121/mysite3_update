package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.board.dao.BoardDao;
import com.bit2016.mysite.board.vo.BoardVo;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		int no1=Integer.parseInt(no);
		
	
		
		BoardDao dao=new BoardDao();
		BoardVo vo=dao.get(no1);
		
		request.setAttribute( "vo", vo );
		
		
		
		
	
		
		request.setAttribute("vo", vo);
		WebUtil.forward(
				request,
				response,
				"/WEB-INF/views/board/view.jsp" );

	}

}
