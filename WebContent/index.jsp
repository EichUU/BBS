<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id=(String)session.getAttribute("id");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script>
	function join(){
		location.href="joinForm.jsp"
	}
</script>
<style>
#wrap{
	margin:auto;
	width:1024px;
	padding:10px;
}
#top-right{
	margin:auto;
	width:300px;
	height:120px;
	border:2px solid #ccc;
	
	text-align:center;
	float:right;	
}
fieldset{
	text-align:center;
	border:none;
}
#selectButton{
	margin-top:10px;
}
h3{
	margin:0;
	padding:0;
}
table{
	width:281px;
	margin:auto;
	padding:0;
}
.td_left{
	width:180px;
}
.td_right{
	width:100px;
	padding:0 20px 0 0;
	
}
#ifLogin{
	margin:auto;
	width:300px;
	height:120px;
	border:2px solid #ccc;	
	text-align:center;
	float:right;	
}
#top{
	margin:auto;
	width:1045px;
	height:124px;
	border:1px solid #ccc;
}

#nav{
	margin:auto;
	padding:10px;
	width:1024px;
	height:100px;
	border:1px solid #ccc;
}
#left{	
	margin:auto;
	width:200px;
	
	border:1px solid #ccc;
	float:left;
}
#center{	
	margin:auto;
	width:1045px;
	
	border:1px solid #ccc;	
}
#bottom{
	margin:auto;
	width:1024px;
	padding:10px;
	border:1px solid #ccc;
}
#clear{
	clear: both; 
	height: 4px; 
	overflow: hidden;
}


</style>
</head>
<body>
<div id="wrap">
	
	<div id="top">
	top		

	<%	if(id==null) { %>	
	<div id="top-right">
		<h3>로그인</h3>
		<form action="Login" method="post">
			<fieldset>
				<table>
					<tr>
						<td class="td_left"><label for="id">아이디</label></td>
						<td class="td_right"><input type="text" name="id" id="id" /></td>
					</tr>
					<tr>
						<td class="td_left"><label for="passwd">비밀번호</label></td>
						<td class="td_right"><input type="password" name="passwd" id="passwd" /></td>
					</tr>
				</table>
				
				<input type="submit" value="로그인" id="selectButton" />				
				<input type="button" value="회원가입" onclick="join()"/>
			</fieldset>		
		</form>		
	</div>
	<%	}else{ %>
	<div id="ifLogin">	
		<br/>
		<table>			
			<tr>
				<td><%=id %>님 환영합니다</td>
			</tr>
			<tr>								
				<td><a href="logout">로그아웃</a></td>
			</tr>			
		</table>	
	</div>
	<%	} %>
	</div>
	<div id="clear"></div>
	
	<div id="nav">
	nav
	</div>
	
	<div id="clear"></div>
	
	<div id="center">
	center
	
		<div id="left">
			<ul>
				<li><a href="boardList.bo">게시판이동하기</a></li>
				<li><a href="dogList.dog">개 상품목록 보기</a></li>
			</ul>
		</div>
		
	</div>
	
	<div id="clear"></div>
	<div id="bottom">
	bottom
	</div>
	
</div>

</body>
</html>