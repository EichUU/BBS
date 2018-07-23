<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function goCheck(){
		var popUrl = "joinCheck.jsp";	//팝업창에 출력될 페이지 URL
		var popOption = "width=540, height=300, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

			window.open(popUrl,"",popOption);

		}
</script>
<style>	
	h4{
		text-align:center;
	}
	table{
		margin :auto;		
		width:400px;
		border:1px solid #ccc;
		text-align:center;
	}
	tr{
		padding:10px;
	}
</style>
</head>
<body>

<h4>회원가입 페이지</h4>

<form action="Join" method="post">
																	
	<table>
		<tr>
			<td><label for="id">아이디</label></td>
			<td><input type="text" name="id" id="id"  placeholder="아이디" required />
				<input type="button" value="중복확인" onclick="goCheck()" /></td>
		</tr>
		
		<tr >
			<td><label for="passwd">비밀번호</label></td>
			<td><input type="password" name="passwd" id="passwd" placeholder="비밀번호" required /></td>
		</tr>
		<tr>
			<td><label for="name">이름</label></td>
			<td><input type="text" name="name" id="name" placeholder="이름" required /></td>
			
		</tr>		
		<tr>
			<td><label for="age">나이</label></td>
			<td><input type="text" name="age" id="age" placeholder="나이" required /></td>
		</tr>
		<tr>
			<td><label for="gender1">성별</label></td>
			<td>
				<input type="radio" name="gender" value="m" checked id="gender1" required />남자
				<input type="radio" name="gender" value="f" id="gender2"/>여자
			</td>
		</tr>
		<tr>
			<td><label for="addr">주소</label></td>
			<td><input type="text" name="addr" id="addr" placeholder="주소" required /></td>
			
		</tr>				
		<tr>
			<td><label for="email">이메일</label></td>
			<td><input type="text" name="email" id="email" placeholder="이메일" required /></td>
		</tr>	
		<tr>
			<td><label for="nation">국가</label></td>
			<td><input type="text" name="nation" id="nation" placeholder="국가" required /></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="submit" value="회원가입"/>&nbsp;&nbsp;
				<input type="reset" value="다시작성"/>
			</td>			
		</tr>
		
	</table>
</form>
</body>

</html>