package com.sawol.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Myhome")
public class Mypage extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		resp.setCharacterEncoding("UTF-8");		// 응답을 UTF-8로 설정해서 보냄
		resp.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		PrintWriter out = resp.getWriter();
		
		String parameter = req.getParameter("cnt");
		int cnt = 0; // 초기값 설정
		
		if(parameter != null && parameter.length() > 0) {
			cnt = Integer.parseInt(parameter);
		}
		
		for(int i=0; i<cnt; i++) {
			out.println(i+1+": this is 홈페이지 <br>");
		} 
	}
}
