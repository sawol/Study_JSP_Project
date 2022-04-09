package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

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
			Date regdate = rs.getTimestamp("regdate");
			String writerId = rs.getString("writer_id"); 
			int hit = rs.getInt("hit");
			String files = rs.getString("files"); 
			String content = rs.getString("content"); 
			
			Notice notice = new Notice(id, title, regdate, writerId, hit, files, content);
			
			request.setAttribute("n", notice);
			
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
		
	}
}
