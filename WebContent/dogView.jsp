<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="vo.Dog" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개 상품 상세정보</title>

<style type="text/css">
#listForm{
	width:640px;
	border:1px solid red;
	margin:auto;
}
h2{
	text-align:center;
}
img{
	width:280px;
	height:280px;
	border:none;
}
#content_main{
	height:300px;
}
#content_left{
	width:300px;
	float:left;
}
#content_right{
	width:340px;
	float:left;
}
#commandList{
	text-align:center;
}
#desc{
	height:170px;
	background:skyblue;
}
</style>

</head>
<body>

<div id="listForm">
	<h2>${dog.kind }의 상세정보</h2>
	
	<div id="content_main">
		<div id="content_left">
			<img src="images/${dog.image }" />
		</div>
		<div id="content_right">
			<b>품종 :</b> ${dog.kind }<br/>
			<b>가격 :</b> ${dog.price }<br/>
			<b>신장 :</b> ${dog.height }<br/>
			<b>체중 :</b> ${dog.weight }<br/>
			<b>원산지 :</b> ${dog.country }<br/>
			<p id="desc">
				<b>내용 :</b> ${dog.content }<br/>
			</p>			
		</div>
		<div style="clear:both"></div>
		<nav id="commandList">
			<a href="dogList.dog">쇼핑계속하기</a>
			<a href="dogCartAdd.dog?id=${dog.id }">장바구니에 담기</a>
		</nav>
	</div>

</div>

</body>
</html>