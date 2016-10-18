package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.guestbook.dao.GuestBookDao;
import com.bit2016.mysite.guestbook.vo.GuestBookVo;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVo> list = dao.getList();
		
		request.setAttribute( "list", list );
		WebUtil.forward(
			request,
			response,
			"/WEB-INF/views/guestbook/list.jsp" );
	}
}