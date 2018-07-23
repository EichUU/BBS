<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int board_num=(Integer)request.getAttribute("board_num");
	String nowPage=(String)request.getAttribute("page");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>

<style type="text/css">
#passForm{
	width:400px;
	margin:auto;
	border:1px solid orange;
}
</style>

</head>
<body>

<div id="passForm">
	<form action="boardDeletePro.bo?board_num=<%=board_num %>" method="post" name="deleteForm">
		<input type="text" name="page" value="<%=nowPage %>" />	
		<table>
			<tr>
				<td><label for="board_pass">글 비밀번호 </label></td>
				<td><input type="password" name="board_pass" id="board_pass"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="삭제" /></td>&nbsp;&nbsp;
				<td><input type="button" value="돌아가기" onclick="javascript:history.go(-1)"/></td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>