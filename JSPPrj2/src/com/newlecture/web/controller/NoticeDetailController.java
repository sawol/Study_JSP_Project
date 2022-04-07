package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String url = "jdbc:mysql://localhost:3306/jsppj?serverTimezone=UTC";
		String sql = "SELECT * FROM NOTICE WHERE ID = ?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();

			String title = rs.getString("title");
			Date regdate = rs.getDate("regdate");
			String writerId = rs.getString("writer_id"); 
			int hit = rs.getInt("hit");
			String files = rs.getString("files"); 
			String content = rs.getString("content"); 

			request.setAttribute("title", title);
			request.setAttribute("regdate", regdate);
			request.setAttribute("writer_id", writerId);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
		
	}
}
