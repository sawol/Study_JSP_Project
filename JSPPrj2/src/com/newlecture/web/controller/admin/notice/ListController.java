package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		response.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		String ids_ = request.getParameter("ids");
		String[] ids = ids_.trim().split(" ");
		
		NoticeService service = new NoticeService();
		
		switch(cmd) {
		case "일괄공개":
			for(String openId : openIds) {
				System.out.printf("open id : %s\n", openId);
			}
			
			List<String> oids = Arrays.asList(openIds);
			
			List<String> cids = new ArrayList<String>(Arrays.asList(ids));
			cids.removeAll(oids);
			
			// Transaction ( 아래 두 함수가 함께 실행되어야함. 하나가 실패하면 이전 상태로 돌려놔야함)
			service.pubNoticeAll(oids, cids);
//			service.closeNoticeList(cids);
			
			break;
		case "일괄삭제":
			int[] ids1 = new int[delIds.length];
			for(int i=0; i<delIds.length; i++) {
				ids1[i] = Integer.parseInt(delIds[i]);
			}
			service.deleteNoticeAll(ids1);
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
