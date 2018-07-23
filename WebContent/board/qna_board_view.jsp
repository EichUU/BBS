<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="vo.BoardBean" %>    

<%
	BoardBean article=(BoardBean)request.getAttribute("article");
	String nowPage=(String)request.getAttribute("page");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>

<style type="text/css">
#articleForm{
	width:500px;
	height:500px;
	border:1px solid red;
	margin:auto;
}
h2{
	text-align:center;
}
#basicInfoArea{
	height:40px;
	text-align:center;
}
#articleContentArea{
	background:orange;
	margin-top:20px;
	padding:20px;
	height:300px;
	
	overflow:auto;
}
#commandList{
	margin:auto;
	width:500px;
	text-align:center;
}
</style>

</head>
<body>

<!-- 게시판 수정 -->
<div id="articleForm">
	<input type="text" name="board_num" value="<%=article.getBoard_num() %>" />
	<h2>글 내용 상세보기</h2>
	<div id="basicInfoArea">
		제목 : <%=article.getBoard_subject() %> &nbsp;&nbsp;&nbsp;
		첨부파일 : 
		<%if(!(article.getBoard_file()==null)) { %>
			<a href="file_down?diwnFile=<%=article.getBoard_file() %>">
				<%=article.getBoard_file() %></a>		
		<%} %>
	</div>
	<div id="articleContentArea">
		<%=article.getBoard_content() %>
	</div>
</div>
<div id="commandList">
	<a href="boardReplyForm.bo?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">[답변]</a>
	<a href="boardModifyForm.bo?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">[수정]</a>
	<a href="boardDeleteForm.bo?board_num=<%=article.getBoard_num() %>&page=<%=nowPage %>">[삭제]</a>
	<a href="boardList.bo?page=<%=nowPage %>">[목록]</a>
	&nbsp;&nbsp;
</div>

</body>
</html>