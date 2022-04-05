package com.homepage.app.console;

import java.sql.SQLException;
import java.util.List;

import com.homepage.app.service.NoticeService;
import com.homepage.app.service.entity.Notice;

public class NoticeConsole {
	
	private NoticeService service;
	
	public NoticeConsole() {
		service = new NoticeService();
	}
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = service.getList();
		
		System.out.println("----------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글\n", 12);
		System.out.println("----------------------------------");
		for(Notice n : list) {
			System.out.printf("%d. %s / %s \t / %s \n", n.getId(), n.getTitle(), n.getWriterID(), n.getRegDate());
		}
		System.out.println("----------------------------------");
		System.out.printf("                  %d/%d pages\n", 1, 2);
	}

	public int inputNoticeMenu() {
		return 0;
	}

}
