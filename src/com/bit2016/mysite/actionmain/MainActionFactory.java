package com.bit2016.mysite.actionmain;

import com.bit2016.web1.Action;
import com.bit2016.web1.Actionfactory;

public class MainActionFactory extends Actionfactory {

	@Override
	public Action getAction(String actionName) {
		
		return new MainAction();
	}

}
