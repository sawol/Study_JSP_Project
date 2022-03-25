package com.sawol.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg")
public class Noticereg extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
//		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");		// 응답을 UTF-8로 설정해서 보냄
		resp.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		PrintWriter out = resp.getWriter();
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
				
		out.println(title);
		out.println(content);
	}
}
