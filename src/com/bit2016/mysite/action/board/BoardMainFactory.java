package com.bit2016.mysite.action.board;

import com.bit2016.web1.Action;
import com.bit2016.web1.Actionfactory;

public class BoardMainFactory extends Actionfactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action=null;
		if("writeform".equals(actionName)){
			action= new WriteFormAction(); 
		}else if("write".equals(actionName)){
			action =new WriteAction();
		}else if("view".equals(actionName)){
			action= new ViewAction();
		}
		else{
			action=new ListBoardAction();
		}
	
		return action;
	}

}
