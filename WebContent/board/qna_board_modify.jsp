<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="vo.BoardBean" %>

<%
	BoardBean article=(BoardBean)request.getAttribute("article");
	String nowPage=(String)request.getAttribute("page");
	out.println("nowPage:"+nowPage);
%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>

<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
</script>

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

<!-- 게시판 수정 -->
<div id="writeForm">
	<h2>게시판글 수정</h2>
	<form action="boardModifyPro.bo?page=<%=nowPage %>" method="post" name="modifyform">
		<input type="text" name="board_num" value="<%=article.getBoard_num() %>"/>
		<table>
			<tr>
				<td class="td_left"><label for="board_name">글쓴이</label></td>
				<td class="td_right"><input type="text" name="board_name" id="board_name" value="<%=article.getBoard_name() %>"/></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_pass">비밀번호</label></td>
				<td class="td_right"><input type="password" name="board_pass" id="board_pass" /></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_subject">제 목</label></td>
				<td class="td_right"><input type="text" name="board_subject" id="board_subject" value="<%=article.getBoard_subject() %>"/></td>
			</tr>
			<tr>
				<td class="td_left"><label for="board_content">내 용</label></td>
				<td><textarea rows="20" cols="55" name="board_content" id="board_content"><%=article.getBoard_content() %></textarea></td>
			</tr>
		</table>
		<div id="commandCell">
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
		</div>
	</form>

</div>

</body>
</html>