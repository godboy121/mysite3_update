package com.bit2016.web1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public void execute (HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException;
}
