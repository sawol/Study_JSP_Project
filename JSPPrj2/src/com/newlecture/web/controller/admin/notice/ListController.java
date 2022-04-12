package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		// 응답을 UTF-8로 설정해서 보냄
		response.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		
		switch(cmd) {
		case "일괄공개":
			for(String openId : openIds) {
				System.out.printf("open id : %s\n", openId);
			}
			break;
		case "일괄삭제":
			NoticeService service = new NoticeService();
			int[] ids = new int[delIds.length];
			for(int i=0; i<delIds.length; i++) {
				ids[i] = Integer.parseInt(delIds[i]);
			}
			service.deleteNoticeAll(ids);
			break;
		}
		response.sendRedirect("list");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field = request.getParameter("f");
		if(field == null || field.equals("")) {
			field = "title";
		}
		String query = request.getParameter("q");
		if(query == null || query.equals("")) {
			query = "";
		}
		int page = 1;
		String page_ = request.getParameter("p");
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticeViewList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
	}
}
