<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="vo.PageInfo" %>
<%@ page import="vo.BoardBean" %>
<%@ page import="java.util.*" %>    
<%@ page import="java.text.SimpleDateFormat" %>

<%
 	ArrayList<BoardBean> articleList=(ArrayList<BoardBean>)request.getAttribute("articleList");
	PageInfo pageInfo=(PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();			
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>

<style type="text/css">
#listForm{
	width:600px;
	height:600px;
	border:1px solid red;
	margin:auto;
}
h2{
	text-align:center;
}
h4{
	float:right;
	padding:0 20px 0 0;
}
table{
	font-size:1.2em;
	margin:auto;
	width:550px;
	text-align:center;
}
#tr_top{
	background:orange;
	text-align:center;
}
#pageList{
	margin:auto;
	width:500px;
	text-align:center;
}
#emptyArea{
	margin:auto;
	width:500px;
	text-align:center;
}
</style>

</head>
<body>

<!-- 게시판 리스트 -->
<div id="listForm">
	<h2>글목록</a></h2>
	<h4>전체글 수 <%=listCount %>개</h4>
	<table>
	<%if(articleList!=null && listCount>0) { %>
		<tr id="tr_top">
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>날짜</td>
			<td>조회수</td>		
		</tr>
		
		<%
		for(int i=0; i<articleList.size(); i++) {	
		%>
		<tr>
			<td><%=articleList.get(i).getBoard_num() %>
			</td>
			<td>
				<%if(articleList.get(i).getBoard_re_lev()!=0) { 
					for(int a=0; a<=articleList.get(i).getBoard_re_lev()*2; a++) { %>
						&nbsp;
					<%} %> ▶
				<%}else{ %> 
					▶
				<%} %>
				<a href="boardDetail.bo?board_num=<%=articleList.get(i).getBoard_num() %>&page=<%=nowPage %>">
					<%=articleList.get(i).getBoard_subject() %></a>		
			</td>
			<td><%=articleList.get(i).getBoard_name() %></td>
			<td><%=articleList.get(i).getBoard_date() %></td>
			<td><%=articleList.get(i).getBoard_readcount() %></td>		
		</tr>
		<%} %>
	</table>
</div>	
<div id="pageList">
	  <%if(nowPage<=1) { %>
			[이전]&nbsp;
	  <%}else { %>
		<a href="boardList.bo?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
      <%} %>
	
	  <%for(int a=startPage; a<=endPage; a++) { 
	   	  if(a==nowPage) {%>
			 [<%=a %>]
		  <%}else{ %>
			 <a href="boardList.bo?page=<%=a %>">[<%=a %>]
			 </a>&nbsp;
		  <%} %>		
	  <%} %>
	
	  <%if(nowPage>=maxPage) { %>
	  	  [다음]
	  	  <br/><a href="boardWriteForm.bo">게시판글쓰기
	  <%}else{ %>
		  <a href="boardList.bo?page=<%=nowPage+1 %>">[다음]
		  <br/><a href="boardWriteForm.bo">게시판글쓰기</a>
	  <%} %>
</div>	

	<%}else { %>
	<div id="emptyArea">등록된 글이 없습니다.
	<br/><br/><a href="boardWriteForm.bo">게시판글쓰기</a>
	</div>
	<%} %>


</body>
</html>