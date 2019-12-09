<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/shop.css" rel="stylesheet" type="text/css" />
<script defer src="${pageContext.request.contextPath}/resources/js/shop.js" charset="UTF-8"></script>
</head>
<body>
	<h3>주문정보 출력</h3>
	<input type="text" id="fileName" placeholder="파일명을 입력하세요">
	<button id="xlxExcelButton">xls 엑셀로 다운받기</button>
	<button id="xlsxExcelButton2">xlsx 엑셀로 다운받기</button>
	<c:if test="${!empty totalList}">
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
					<td class="orderNumber">${total.orderNumber}</td>
					<td class="customerNumber">${total.customerNumber}</td>
					<td class="customerName">${total.customerName}</td>
					<td class="productNumber">${total.productNumber}</td>
					<td class="productName">${total.productName}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty totalList}">
		주문정보가 없습니다.
	</c:if>
</body>
</html>