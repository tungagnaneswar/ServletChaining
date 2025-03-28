package com.kodnest.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Demo() {
       System.out.println("Object Created..");
    }

	@Override
	public void init() {
		System.out.println("Init Executed..");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Service Executed..");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doGet Executed..");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doPost Executed..");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy Executed..");
	}
	
}
