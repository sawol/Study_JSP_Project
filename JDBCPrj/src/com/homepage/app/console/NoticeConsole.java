package com.homepage.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.homepage.app.service.NoticeService;
import com.homepage.app.service.entity.Notice;

public class NoticeConsole {
	
	private NoticeService service;
	private int page = 1;
	private String searchField = "";
	private String searchWord = "";
	
	public NoticeConsole() {
		service = new NoticeService();
	}
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount();
		int lastPage = (int)Math.ceil(count/10.0);
		System.out.println("----------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글\n", count);
		System.out.println("----------------------------------");
		for(Notice n : list) {
			System.out.printf("%d. %s / %s \t / %s \n", n.getId(), n.getTitle(), n.getWriterID(), n.getRegDate());
		}
		System.out.println("----------------------------------");
		System.out.printf("                  %d/%d pages\n", page, lastPage);
	}

	public int inputNoticeMenu() {
		System.out.println("1.상세조회    2.이전    3.다음    4.글쓰기    5.검색    6.종료   >");
		Scanner sc = new Scanner(System.in);
		String menu_ = sc.nextLine();
		int menu = Integer.parseInt(menu_);
		return menu;
	}
	public void movePrevList() {
		if(page > 1) {
			page--;
		}
		else {
			System.out.println("현재 첫 페이지 입니다.");
		}
		
	}
	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount();
		int lastPage = (int)Math.ceil(count/10.0);
		if(page == lastPage) {
			System.out.println("현재 마지막 페이지 입니다.");
			return;
		}
		page++;
	}
	public void inputSearchWord() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색 범주(TITLE/CONTENT/WRITER_ID)중에 하나를 입력하세요.");
		System.out.print("> ");
		searchField  = sc.nextLine();
		System.out.print("검색어 > ");
		searchWord = sc.nextLine();
	}
}
