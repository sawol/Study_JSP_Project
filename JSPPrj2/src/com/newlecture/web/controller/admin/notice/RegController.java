package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@MultipartConfig(
	/*location="/tmp",*/			// location은 절대경로를 사용해함. 하지만 어노테이션 내에서 동적으로 절대경로를 알아낼 수는 없음. 일단, 설정하지 않고 자바에서 디폴트로 지정해주는 위치를 사용함
	fileSizeThreshold=1024*1024,	// 첨부 파일이 1MB를 넘으면 location에 설정한 디스크를 사용하도록 함
	maxFileSize=1024*1024*50,		// 각각의 첨부 파일은 50MB를 초과 할 수 없음
	maxRequestSize=1024*1024*50*5	// 모든 첨부 파일은 250MB를 초과 할 수 없음			
)

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");	// 브라우저에게 이 형식으로 읽어달라고 요청
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Collection<Part> parts = request.getParts();		// 여러개의 첨부파일을 받기 위해
		StringBuilder sb = new StringBuilder();
		for(Part p: parts) {
			if(!p.getName().equals("file")) continue;		// name이 file이 아닌 경우 pass
			if(p.getSize() == 0) continue;					// 첨부파일이 1개만 또는 하나도 등록하지 않았을 경우 예외 처리. name이 file이지만, 바이너리 파일을 전달하지 않고 그냥 비어있는 데이터가 전달될 경우 pass
			
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			sb.append(fileName);
			sb.append(", ");
			
			InputStream fis = filePart.getInputStream();
			
			String realPath = request.getServletContext().getRealPath("/upload");	// getRealPath 메서드는 현재 서버의 절대경로 + 파라미터 경로를 붙여줌
			String filePath = realPath + File.separator + fileName; 	// 파일 경로는 유닉스("/")와 윈도우즈("//")의 방법이 다르기 때문에 하드코딩하면 안됨. 
																			// separator 메서드를 사용하면자바에서 현재 운영되는 OS에 맞게 구분자를 바꿔줌
			System.out.println(filePath);
			
			// realPath의 경로에 해당 폴더가 없을경우 폴더를 생성해줌. 여기서는 upload 폴더가 없으니 생성해준다.
			File path = new File(realPath);
			if(!path.exists()) path.mkdirs();
			
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf)) != -1) {				// fis.read(buf) 의 반환값은 받아온 데이터의 크기임
				fos.write(buf, 0, size);					// 마지막 input 값이 buf의 크기보다 작을 경우를 대비해 0~size만큼 받아오기
			}
			fos.close();
			fis.close();
		}
		sb.deleteCharAt(sb.length()-1);			// 마지막 , 문자 제거
		
		boolean pub = false;
		if(isOpen != null) pub = true;
		
		Notice notice = new Notice();
		notice.setContent(content);
		notice.setTitle(title);
		notice.setPub(pub);
		notice.setWriterId("sawol");	// 로그인한 사용자 이름 - 아직 미구현
		notice.setFiles(sb.toString());
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		response.sendRedirect("list");
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}
}
