package com.sawol.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
//		ServletContext application = req.getServletContext();		// 서블릿 컨텍스트를 사용하는 어플리케이션 방법
//		HttpSession session = req.getSession();						// 세션
		Cookie[] c = req.getCookies();								// 쿠키
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");		// 응답을 UTF-8로 설정해서 보냄
		resp.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		PrintWriter out = resp.getWriter();
		
		String v_ = req.getParameter("v");
		String op = req.getParameter("operator");
		
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("계산")) {
//			int x = (Integer)application.getAttribute("v");
//			int x = (Integer)session.getAttribute("v");
			int x = 0;
			for(Cookie cook: c) {
				if(cook.getName().equals("v")) {
					x = Integer.parseInt(cook.getValue());
					break;
				}
			}
			
			String operator = "";
			for(Cookie cook: c) {
				if(cook.getName().equals("op")) {
					operator = cook.getValue();
					break;
				}
			}
			int y = v;
			
//			String operator = (String)application.getAttribute("op");
//			String operator = (String)session.getAttribute("op");
			int result = 0;
			
			if(operator.equals("+")) {
				result = x+y;
			}else {
				result = x-y;
			}
			
			out.printf("result is %d", result);
			
		}
		else {
//			application.setAttribute("v", v);
//			session.setAttribute("v", v);
//			application.setAttribute("op", op);
//			session.setAttribute("op", op);
			
			Cookie valueCookie = new Cookie("v", String.valueOf(v));
			Cookie operatorCookie = new Cookie("op", op);
//			valueCookie.setPath("/add");
//			operatorCookie.setPath("/add");
			
			valueCookie.setMaxAge(60*60);	// 1시간 동안 쿠키 생존
			
			resp.addCookie(valueCookie);
			resp.addCookie(operatorCookie);
		}
	}
}
