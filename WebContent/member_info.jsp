<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="vo.Member" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보보기</title>
<style type="text/css">
	#memberInfoArea{
		width:400px;
		margin:auto;
		border:1px solid gray;
	}
	table{
		width:380px;
		margin:auto;
		text-align:center;
	}

</style>

</head>
<body>

<div id="memberInfoArea">
	<table>
		<tr>
			<td>아이디</td>
			<td>${member.id }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${member.passwd }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${member.addr }</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>${member.age }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${member.gender }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.name }</td>
		</tr>
		<tr>
			<td>국가</td>
			<td>${member.nation }</td>
		</tr>
		<tr>
			<td colspan="2"><a href="memberListAction.bo">리스트로 돌아가기</a>
		</tr>
	
	</table>
</div>


</body>
</html>