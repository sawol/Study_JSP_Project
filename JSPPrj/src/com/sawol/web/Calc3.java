package com.sawol.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		Cookie[] cookie = req.getCookies();								// 쿠키
		
		String v = req.getParameter("value");
		String op = req.getParameter("operator");
		String dot = req.getParameter("dot");
		
		String exp = "";
		if(cookie != null) {
			for(Cookie c: cookie) {
				if(c.getName().equals("exp")) {
					exp=c.getValue();
				}
			}
		}
		
		if(op != null && op.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js"); 
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		else {
			exp += (v == null)?"":v;
			exp += (op == null)?"":op;
			exp += (dot == null)?"":dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		if(op != null && op.equals("C")) {
			expCookie.setMaxAge(0); 			// 쿠키의 생존시간을 0초로하여 쿠키 삭제하기
		}
		resp.addCookie(expCookie);
		resp.sendRedirect("calcpage");

	}
}
