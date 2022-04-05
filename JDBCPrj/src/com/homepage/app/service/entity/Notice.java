package com.homepage.app.service.entity;

import java.sql.Date;

public class Notice {
	private int id;
	private String title;
	private String writerID;
	private Date regDate;
	private String content;
	private int hit;
	private String files;
	
	public Notice() {
	}
	
	public Notice(int id, String title, String writerID, Date regDate, String content, String files, int hit) {
		this.id = id;
		this.title = title;
		this.writerID = writerID;
		this.regDate = regDate;
		this.content = content;
		this.files = files;
		this.hit = hit;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	
}
