<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/shop.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<h3>주문정보 출력</h3>
	<input type="text" id="fileName">
	<button id="excelButton">엑셀로 다운받기</button>
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

<script>
	document.getElementById("excelButton").onclick = function() {
		var fileName = document.getElementById("fileName").value;
		
		var orderNumbers = document.getElementsByClassName("orderNumber");
		var customerNumbers = document.getElementsByClassName("customerNumber");
		var customerNames = document.getElementsByClassName("customerName");
		var productNumbers = document.getElementsByClassName("productNumber");
		var productNames = document.getElementsByClassName("productName");
		
		var totalArray = new Array();
		for(var i=0; i<orderNumbers.length; i++) {
			/* var total = new Object();
			
			total.orderNumber = orderNumbers[i].innerHTML;
			total.customerNumber = customerNumbers[i].innerHTML;
			total.customerName = customerNames[i].innerHTML;
			total.productNumber = productNumbers[i].innerHTML;
			total.productName = productNames[i].innerHTML; */

			totalArray.push(orderNumbers[i].innerHTML);
			totalArray.push(customerNumbers[i].innerHTML);
			totalArray.push(customerNames[i].innerHTML);
			totalArray.push(productNumbers[i].innerHTML);
			totalArray.push(productNames[i].innerHTML);
			/* totalArray.push(total); */
		}
		console.log(totalArray);
		
		var jsonTotal = {
				"fileName" : fileName,
				"totalArray" : totalArray
		}
		console.log(jsonTotal);
		
		var httpRequest = new XMLHttpRequest(); //XMLHttpRequest 객체 생성자표현식으로 객체생성
		httpRequest.open("post","${pageContext.request.contextPath}/shop/excelDown",true); //open(HTTP메서드, 요청처리할URL, 비동기처리여부boolean값(디폴트가 true))
		httpRequest.setRequestHeader('Content-type', 'application/json'); //open 메서드 호출 이후에 호출
		httpRequest.send(JSON.stringify(jsonTotal)); //준비된 요청을 전달하는 메서드
							//JSON.stringify : 객체 => JSON 형식의 문자열
							
		httpRequest.onload = function() {
			if(httpRequest.readyState==4 && httpRequest.status == 200) {
				console.log("성공");
			}else {
				console.log("실패");
			}
		}
	}
</script>
</html>