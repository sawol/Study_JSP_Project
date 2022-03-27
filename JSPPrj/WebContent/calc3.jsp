<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
int a = 9;
%>
a의 값은 : <%=a %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input{
	width:50px;
	height:50px;
}
.output{
	height:50px;
	background:#e9e9e9;
	font-size:24px;
	font-weight:bold;
	text-align:right;
	padding:0px 5px;
}

</style>
</head>
<body>
	<form action="calc3" method="post">
		<table>
			<tr>
				<td class="output" colspan="4">${3+4}</td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="CE" /></td>
				<td><input type="submit" name="operator" value="C" /></td>
				<td><input type="submit" name="operator" value="BS" /></td>
				<td><input type="submit" name="operator" value="Ã·" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="7" /></td>
				<td><input type="submit" name="operator" value="8" /></td>
				<td><input type="submit" name="operator" value="9" /></td>
				<td><input type="submit" name="operator" value="X" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="4" /></td>
				<td><input type="submit" name="operator" value="5" /></td>
				<td><input type="submit" name="operator" value="6" /></td>
				<td><input type="submit" name="operator" value="-" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="1" /></td>
				<td><input type="submit" name="operator" value="2" /></td>
				<td><input type="submit" name="operator" value="3" /></td>
				<td><input type="submit" name="operator" value="+" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="operator" value="0" /></td>
				<td><input type="submit" name="operator" value="." /></td>
				<td><input type="submit" name="operator" value="=" /></td>
			</tr>		
		</table>
	</form>
</body>
</html>