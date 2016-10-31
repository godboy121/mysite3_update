package com.bit2016.mysite.action.guestbook;

import com.bit2016.web1.Action;
import com.bit2016.web1.Actionfactory;

public class GuestMainFactory extends Actionfactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action=null;
		if("add".equals(actionName)){
			action=new AddAction();
		}else if("deleteform".equals(actionName)){
			action=new DeleteFormAction();
		}else if("delete".equals(actionName)){
			action=new DeleteAction();
		}else if("ajax".equals(actionName)){
			action=new AjaxAction();
		}else if("ajax-list".equals(actionName)){
			action=new AjaxListAction();
		}
		else
		{
			action=new ListAction();
		}
		return action;
	
	}

}
