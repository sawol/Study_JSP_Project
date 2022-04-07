package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "jdbc:mysql://localhost:3306/jsppj?serverTimezone=UTC";
			String sql = "SELECT * FROM NOTICE";

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				List<Notice> list = new ArrayList<>();
				while(rs.next()){
					int id = rs.getInt("id");
					String title = rs.getString("title");
					Date regdate = rs.getDate("regdate");
					String writerId = rs.getString("writer_id"); 
					int hit = rs.getInt("hit");
					String files = rs.getString("files"); 
					String content = rs.getString("content");
					
					Notice notice = new Notice(id, title, regdate, writerId, hit, files, content);
					list.add(notice);
				}
				request.setAttribute("list", list);

				rs.close();
				st.close();
				con.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		}
}
