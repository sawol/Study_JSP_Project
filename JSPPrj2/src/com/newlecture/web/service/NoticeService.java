package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class NoticeService {
	private String url = "jdbc:mysql://localhost:3306/jsppj?serverTimezone=UTC";
	
	public int removeNoticeAll(int[] ids) {
		
		return 0;
	}
	public int pubNoticeAll(int[] ids) {
		
		return 0;
	}
	public int insertNotice(Notice notice) {
		
		return 0;
	}
	public int deleteNotice(int ids) {
		
		return 0;
	}
	public int updateNotice(int ids) {
		
		return 0;
	}
	public List<Notice> getNoticeNewestList(int[] ids) {
		
		return null;
	}
	
	public List<NoticeView> getNoticeViewList(){
		return getNoticeViewList("title", "", 1);
	}
	public List<NoticeView> getNoticeViewList(int page){
		return getNoticeViewList("title", "", page);
	}
	public List<NoticeView> getNoticeViewList(String field,/*title, writer_id*/ String query/*A*/, int page){
		List<NoticeView> list = new ArrayList<>();
		
		String sql = "select * from notice_view where " + field + " like concat('%',?,'%') order by id desc limit ?, 10";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, query);
			ps.setInt(2, (page-1)*10);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getTimestamp("regdate");
				String writerId = rs.getString("writer_id"); 
				int hit = rs.getInt("hit");
				String files = rs.getString("files"); 
//				String content = rs.getString("content");
				int cmtCount = rs.getInt("CMT_COUNT");
				
				NoticeView notice = new NoticeView(id, title, regdate, writerId, hit, files, cmtCount);
				list.add(notice);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}
	public int getNoticeCount(String field, String query) {
		int count = 0;
		
		String sql = "select count(id) as count from notice where " + field + " like concat('%',?,'%')";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, query);
			ResultSet rs = ps.executeQuery();

			rs.next();
			count = rs.getInt("count");
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public Notice getNotice(int id) {
		Notice notice = null;
		String sql = "select * from notice where id=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id_ = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getTimestamp("regdate");
				String writerId = rs.getString("writer_id"); 
				int hit = rs.getInt("hit");
				String files = rs.getString("files"); 
				String content = rs.getString("content");
				
				notice = new Notice(id_, title, regdate, writerId, hit, files, content);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	public Notice getNextNotice(int id) {
		Notice notice = null;
		
		String sql = "select * from notice where regdate > (select regdate from notice where id=?) limit 1";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id_ = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getTimestamp("regdate");
				String writerId = rs.getString("writer_id"); 
				int hit = rs.getInt("hit");
				String files = rs.getString("files"); 
				String content = rs.getString("content");
				
				notice = new Notice(id_, title, regdate, writerId, hit, files, content);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Notice getPrevNotice(int id) {
		Notice notice = null;
		
		String sql = "select * from notice where regdate < (select regdate from notice where id=?) order by id desc limit 1";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "ssafy", "ssafy");
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id_ = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getTimestamp("regdate");
				String writerId = rs.getString("writer_id"); 
				int hit = rs.getInt("hit");
				String files = rs.getString("files"); 
				String content = rs.getString("content");
				
				notice = new Notice(id_, title, regdate, writerId, hit, files, content);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
