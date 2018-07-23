<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복확인</title>
<style>
form{
	text-align:center;
}
</style>
</head>
<body>

<form action="Check" method="post">
	<p></p>
	<h3>아이디를 입력하세요</h3>
	<input type="text" name="idCheck" required/>
	<br/><br/>
	<input type="submit" value="확인" />
</form>

</body>
</html>