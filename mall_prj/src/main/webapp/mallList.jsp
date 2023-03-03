<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/notice.css">
	<script src="js/jquery.js"></script>
</head>
<body>
	<div id="wrap">
		<%@ include file="header.jsp"%>
		<%@ include file="nav.jsp"%>
		<div class="box">
			<section>
				<div id="title1">의료 몰</div>
				<hr>
				<table id="board">
					<thead>
						<tr>
							<th>제품 번호</th>
							<th>이미지</th>
							<th>제품명</th>
							<th>가격</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
				<c:if test="${sessionScope.id == 'admin' }">
					<input id="write" type="button" value="물품 추가">
				</c:if>
			</section>
		</div>
	</div>
	<%@ include file="footer.jsp" %>   
<script>
$("#write").click(function(){

	location.href='addMall.jsp';	

});



$(document).ready(function(){

	$.ajax({
		url: "getPList.do",
		type: "get",
		dataType: "text",
		success: function(data){ 

			let obj = JSON.parse(data);
			
			if(obj.length < 10){
				for (i = 0; i < obj.length; i++) {
					$("tbody").append('<tr><td>' + obj[i].pno + '</td><td><img class="pimg" src="img/' 
							+ obj[i].pimg + '"></td><td>' + obj[i].pname + '</td><td>'
							+ obj[i].pprice + '</td></tr>');
					}
			}else{
				for (i = 0; i < 10; i++) {
					$("tbody").append('<tr><td>' + obj[i].pno + '</td><td><img class="pimg" src="img/' 
							+ obj[i].pimg + '"></td><td>' + obj[i].pname + '</td><td>'
							+ obj[i].pprice + '</td></tr>');
					}
				$("tbody").append('<tr><td id="page" colspan="4"></td></tr>');
				if(obj.length > 10){
					for(let j = 1; j < obj.length/10+1; j++ ){
						$("#page").append("<span>"+ j +"</span>");
						
					}
				}
			}
		},
		error: function(){
			alert("error.....");
		}
	});
});

$("tbody").on('click', function(e){
	if(e.target.tagName == 'SPAN'){
		$("tbody").empty();
		$.ajax({
			url: "getPList.do",
			type: "get",
			dataType: "text",
			success: function(data){ 
				
				let obj = JSON.parse(data);
				let i = (e.target.innerText)*10-10;
				
				for (i; i < e.target.innerText*10; i++) {
					if(i == obj.length){
						break;
					}
					$("tbody").append('<tr><td>' + obj[i].pno + '</td><td><img class="pimg" src=img/"' 
							+ obj[i].pimg + '"></td><td>' + obj[i].pname + '</td><td>'
							+ obj[i].pprice + '</td></tr>');
					}
				$("tbody").append('<tr><td id="page" colspan="4"></td></tr>');
				if(obj.length > 10){
					for(let j = 1; j < obj.length/10+1; j++ ){
						$("#page").append("<span>"+ j +"</span>");
						
					}
				}
			},
			error: function(){
				alert("error.....");
			}
		});
	}
});

$("tbody").on('click', function(e){
	if(e.target.tagName == 'TD' && e.target.id != 'page'){
		location.href="viewProduct.jsp?pno="+e.target.parentElement.firstChild.innerText;
	}
});

</script>
</body>
</html>