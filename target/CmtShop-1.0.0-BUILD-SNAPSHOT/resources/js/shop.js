document.getElementById("excelButton").onclick = function() {
	excelDown('excelDown');
}

document.getElementById("excelButton2").onclick = function() {
	excelDown('excelDown2');
}

function excelDown(pathString) {
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
	httpRequest.open("post","/shop/"+pathString,true); //open(HTTP메서드, 요청처리할URL, 비동기처리여부boolean값(디폴트가 true))
	httpRequest.setRequestHeader('Content-type', 'application/json'); //open 메서드 호출 이후에 호출
	httpRequest.send(JSON.stringify(jsonTotal)); //준비된 요청을 전달하는 메서드
						//JSON.stringify : 객체 => JSON 형식의 문자열
						
	httpRequest.onload = function() {
		if(httpRequest.readyState==4 && httpRequest.status == 200) { //readyState => 데이터 전부 받은 상태 & status => 요청성공
			alert("다운성공");
		}else {
			alert("다운실패");
		}
	}
}