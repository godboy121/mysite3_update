package com.bit2016.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.board.dao.BoardDao;
import com.bit2016.mysite.board.vo.BoardVo;
import com.bit2016.mysite.guestbook.dao.GuestBookDao;
import com.bit2016.mysite.guestbook.vo.GuestBookVo;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class ListBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.getList(1,5);
		
		request.setAttribute( "list", list );
		WebUtil.forward(
			request,
			response,
			"/WEB-INF/views/board/list.jsp" );
	}
}