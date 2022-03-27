<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int num=0;
	String num_ = request.getParameter("n");
	String model;
	
	if(num_ != null && !num_.equals("")){
		num = Integer.parseInt(num_);
	}
	if(num%2 == 0){ 
		model = "짝수";
 	}else{ 
 		model = "홀수";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=model %>입니다.
</body>
</html>



