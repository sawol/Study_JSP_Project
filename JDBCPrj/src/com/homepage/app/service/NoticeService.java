package com.homepage.app.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.homepage.app.service.entity.Notice;

public class NoticeService {
	private String url = "jdbc:mysql://localhost:3306/jsppj?serverTimezone=UTC";
	private String dbID = "ssafy";
	private String dbPW = "ssafy";
	private String driver = "com.mysql.jdbc.Driver";
	
	
	public List<Notice> getList() throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM NOTICE";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, dbID, dbPW);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Notice> list = new ArrayList<>();
		
		while(rs.next()) {
			int id=rs.getInt("ID");
			String title=rs.getString("title");
			String writerID=rs.getString("writer_ID");
			Date regDate=rs.getDate("REGDATE");
			String content=rs.getString("Content");
			int hit=rs.getInt("hit");
			String files=rs.getString("files");
			
			Notice notice = new Notice(id, title, writerID, regDate, content, files, hit);
			list.add(notice);
			
		}
		rs.close();
		st.close();
		con.close();
		return list;
	}
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writerID = notice.getWriterID();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		String sql = "insert into NOTICE (title, writer_id, content, files) VALUES(?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, dbID, dbPW);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, writerID);
		ps.setString(3, content);
		ps.setString(4, files);
		int rs = ps.executeUpdate();
		
		ps.close();
		con.close();
		return rs;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String title = notice.getTitle();
		String writerID = notice.getWriterID();
		String content = notice.getContent();
		String files = notice.getFiles();
		int ID = notice.getId();
		
		String sql = "update NOTICE set title=?, writer_id=?, content=?, files? where id=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, dbID, dbPW);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, writerID);
		ps.setString(3, content);
		ps.setString(4, files);
		int rs = ps.executeUpdate();
		
		ps.close();
		con.close();
		return rs;
	}
	
	public int delete(int id) throws SQLException, ClassNotFoundException {		
		String sql = "delete NOTICE where id=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, dbID, dbPW);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		int rs = ps.executeUpdate();
		
		ps.close();
		con.close();
		return rs;
	}
}
