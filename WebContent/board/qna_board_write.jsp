<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="svc.LoginService" %>
<%@ page import="vo.Member" %>
    
<%
	String id=request.getParameter("id");
	String passwd=request.getParameter("passwd");
	
	LoginService loginService=new LoginService(); 
	Member loginMember=loginService.getLoginMember(id, passwd);
	out.println(loginMember);
	//로그인멤버가 받아지지 않는다...
	//세션이 부여되지 않는다..
	if(loginMember!=null) {	
	session.setAttribute("id", id);
	session.setAttribute("passwd", passwd);	
	}
	out.println(id);
	
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>

<style type="text/css">
#writeForm{
	width:600px;
	height:600px;
	border:1px solid red;
	margin:auto;
}
h2{
	text-align:center;
}
table{
	font-size:1.1em;
	margin:auto;
	width:550px;
}
.td_left{
	width:150px;
	background:orange;
	text-align:center;
}
.td_right{
	width:300px;
	background:skyblue;
}
#commandCell{
	text-align:center;
}

</style>

</head>
<body>

<!-- 게시판 등록 -->

<% if ((String)session.getAttribute(id)!=null) {%>
<div id="writeForm">
	<h2>게시판글등록</h2>
	<form action="boardWritePro.bo" method="post" enctype="multipart/form-data" name="boardform">
		<table>
			<tr>
				<td class="td_left"><label for="board_name">글쓴이</label></td>
				<td class="td_right"><input type="text" name="board_name" id=board_name value="<%=id %>"/></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_pass">비밀번호</label></td>
				<td class="td_right"><input type="password" name="board_pass" id="board_pass" required="required"/></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_subject">제목</label></td>
				<td class="td_right"><input type="test" name="board_subject" id="board_subject" required="required" /></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_content">내용</label></td>
				<td><textarea name="board_content" id="board_content" cols="55" rows="15" required="required"></textarea></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_file">파일첨부</label></td>
				<td class="td_right"><input type="file" name="board_file" id="board_file" required="required" /></td>
			</tr>			
		</table>
		<br/>
		<div id="commandCell">
			<input type="submit" value="등록" />&nbsp;&nbsp;
			<input type="reset" value="다시쓰기"/>&nbsp;&nbsp;
			<input type="button" value="목록" onclick="javascript:history.go(-1)"/>
		</div>
		<br/>
	</form>
</div>
<% } else{ 
	out.println("<script>");
	out.println("alert('게시판 글 쓰기는 로그인이 필요합니다.')");
	out.println("history.go(-1)");
	out.println("</script>");
	
   } %>
</body>
</html>