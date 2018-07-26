<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.HashMap" %>
<%@ page import="vo.Dog" %>    
<%@ page import="java.util.ArrayList" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개 상품 목록</title>

<style type="text/css">

#listForm{
	width:700px;	
	border:1px solid red;
	margin:auto;
}
#menu{
	float:right;
	
}
h2{
	text-align:center;
}
table{
	margin:auto;
	width:550px;
}
.div_empty{
	background-color:#ccc;
	width:100%;
	height:100%;
	text-align:center;
}
#todayImageList{
	text-align:center;
}
#productImage{
	width:150px;
	height:150px;
	border:none;
}
#todayImage{
	width:100px;
	height:100px;
	border:none;
}

</style>

</head>
<body>

<div id="listForm">
	<c:if test="${dogList!=null }">
		<h2>개 상품 목록 </h2> 		
		<div id="menu">
			<a href="dogRegistForm.dog">개 상품등록</a>&nbsp;&nbsp;
			<a href="dogCartList.dog">장바구니목록</a>&nbsp;&nbsp;
		</div>
		
		<table>
			<tr>
				<c:forEach var="dog" items="${dogList }" varStatus="status">	<!-- 상품이 추가된만큼 연이여 배치할 수 있도록 -->
					<td>
						<a href="dogView.dog?id=${dog.id }">
						<img src="images/${dog.image }" id="productImage" /></a>
						상품명:${dog.kind }<br/>
						가격:${dog.price }<br/>
					</td>
					<c:if test="${((status.index+1) mod 4)==0 }">   <!-- 한 줄에 몇 개씩 표시하는가 -->
			</tr>
			<tr>		
					</c:if>
				</c:forEach>				
			</tr>
		</table>
	</c:if>
	<c:if test="${dogList==null }">
		<div class="div_empty">
			개 상품이 없습니다. 분양불가
		</div>
	</c:if>	
	
	<c:if test="${todayImageList!=null }">
		<div id="todayImageList">
			<h2>오늘 본 개 상품 목록</h2>
			<table>
				<tr>
					<c:forEach var="todayImage" items="${todayImageList }" varStatus="status">
						<td>
							<img src="images/${todayImage }" id="todayImage" />
						</td>
						<c:if test="${((status.index+1) mod 4)==0 }">
				</tr>
				<tr>
						</c:if>
						</c:forEach>
				</tr>
			</table>
		</div>
	</c:if>

</div>

</body>
</html>