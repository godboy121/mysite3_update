package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.guestbook.dao.GuestBookDao;
import com.bit2016.mysite.guestbook.vo.GuestBookVo;
import com.bit2016.web1.Action;

import net.sf.json.JSONObject;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String spage=request.getParameter("p");
		int page;
		if("".equals(spage)){
			page=1;
		}else{
			page=Integer.parseInt(spage);
		}
	
		
		GuestBookDao dao=new GuestBookDao();
		List<GuestBookVo> list=dao.getList(page);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("result", "success");
		map.put("data", list);
		
		response.setContentType("application/json; charset=utf-8"); 
		JSONObject jsonobject=JSONObject.fromObject(map);
		response.getWriter().println(jsonobject.toString());

	}

}
