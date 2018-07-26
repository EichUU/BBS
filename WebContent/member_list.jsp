<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="vo.Member" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<style type="text/css">

	#memberListArea{
		width:400px;
		border:1px solid gray;
		margin:auto;
	}
	table{
		width:380px;
		margin:auto;
		text-align:center;
	}
</style>

</head>
<body>

<div id="memberListArea">
	<table>
		<tr>
			<td colspan="2"><h2>회원목록</h2></td>
		</tr>
		
		<c:forEach var="member" items="${memberList }">
		<tr>
			<td><a href="memberViewAction.bo?id=${member.id }">${member.id }</a></td>
			<td><a href="memberDeleteAction.bo?id=${member.id }">삭제</a></td>
		</tr>
		
		</c:forEach>
	</table>
	<a href="index.jsp">메인으로</a>
</div>

</body>
</html>