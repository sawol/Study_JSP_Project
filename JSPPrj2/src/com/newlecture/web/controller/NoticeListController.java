package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
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
			List<NoticeView> list = service.getNoticeList(field, query, page);
			int count = service.getNoticeCount(field, query);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		}
}
