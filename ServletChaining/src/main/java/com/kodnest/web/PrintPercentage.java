package com.kodnest.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintPercentage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			PrintWriter pw = res.getWriter();
			HttpSession hs = req.getSession();
			int m1 = (int) hs.getAttribute("marks1");
			int m2 = (int) hs.getAttribute("marks2");
			int m3 = (int) hs.getAttribute("marks3");
			int m4 = (int) hs.getAttribute("marks4");
			float percentage = ((float)(m1+m2+m3+m4)/400)*100;
			pw.println(percentage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}