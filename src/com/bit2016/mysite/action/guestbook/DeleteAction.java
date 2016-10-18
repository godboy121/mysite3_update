package com.bit2016.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.guestbook.dao.GuestBookDao;
import com.bit2016.mysite.guestbook.vo.GuestBookVo;
import com.bit2016.web.util.WebUtil;
import com.bit2016.web1.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		String password=request.getParameter("passwd");
		
		GuestBookVo vo=new GuestBookVo();
		vo.setNo(Integer.parseInt(no));
		vo.setPasswd(password);
		
		GuestBookDao dao=new GuestBookDao();
		dao.delete(Integer.parseInt(no));
		
		WebUtil.redirect( request, response, "/mysite3/guestbook" );

	}

}
