package com.sawol.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		ServletContext application = req.getServletContext();		// 서블릿 컨텍스트를 사용하는 어플리케이션 방법
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");		// 응답을 UTF-8로 설정해서 보냄
		resp.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		PrintWriter out = resp.getWriter();
		
		String v_ = req.getParameter("v");
		String op = req.getParameter("operator");
		
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("계산")) {
			int x = (Integer)application.getAttribute("v");
			int y = v;
			
			String operator = (String)application.getAttribute("op");
			int result = 0;
			
			if(operator.equals("+")) {
				result = x+y;
			}else {
				result = x-y;
			}
			
			out.printf("result is %d", result);
			
		}
		else {
			application.setAttribute("v", v);
			application.setAttribute("op", op);
		}
	}
}
