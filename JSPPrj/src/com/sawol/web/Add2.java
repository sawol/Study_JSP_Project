package com.sawol.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");		// 응답을 UTF-8로 설정해서 보냄
		resp.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		PrintWriter out = resp.getWriter();
		
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		int x = 0;
		int y = 0;
		
		if(!x_.equals("")) x = Integer.parseInt(x_);
		if(!y_.equals("")) x = Integer.parseInt(y_);
		
				
		out.printf("result is %d", x+y);
	}
}
