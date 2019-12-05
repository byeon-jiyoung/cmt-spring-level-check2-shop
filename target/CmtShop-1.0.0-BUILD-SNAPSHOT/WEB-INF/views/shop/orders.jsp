<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/shop.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h3>주문정보 출력</h3>
	<table>
		<tr>
			<th>주문번호</th>
			<th>고객번호</th>
			<th>고객명</th>
			<th>상품번호</th>
			<th>상품명</th>
		</tr>
		<c:forEach var="total" items="${totalList}">
			<tr>
				<td>${total.orderNumber}</td>
				<td>${total.customerNumber}</td>
				<td>${total.customerName}</td>
				<td>${total.productNumber}</td>
				<td>${total.productName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>