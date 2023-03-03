<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/mall.css">
</head>
<body>
	<div id="wrap">
		<%@ include file="header.jsp"%>
		<%@ include file="nav.jsp"%>
		<div class="box">
			<section id="main">
				<div id="title1">물품정보</div>
				<hr>
				<div id="write2">
					<form action="addCart.do" method="post">
					<table>
					<sql:query var="rs" dataSource="jdbc/maria">SELECT * FROM mall_product WHERE pno = ${param.pno };</sql:query>
					<c:forEach var="row" items="${rs.rows }">
						<tr>
							<td colspan="2">
							<input type="hidden" name="pno" value="${row.pno }">
							<input class="subj" type="text" name="subject"
								value="${row.pname }" readonly></td>
						</tr>
						<tr>
							<td style="width: 500px"><img id="pimage" src="img/${row.pimg }" >
							</td>
							<td style="width: 500px">수량설정 : <img class="minus" src="img/minus.png">
								<input type="text" id="quantity" name="qtt" value="1">
								<img class="plus" src="img/plus.png">
								<input type="hidden" id="pstock" value="${row.pstock }">
							</td>
						</tr>
						<tr>
							<td colspan="2"><textarea id="cont" name="contents" readonly>${row.pinfo }</textarea></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:right">
								<input type="submit" class="btn" value="담기">
								<button type="button" class="btn"
									onclick="location.href='mallList.jsp'">뒤로</button>	
							</td>
						</tr>
					</c:forEach>
					</table>
					</form>
				</div>
			</section>
		</div>
	</div>
	
	<script>
		$(".minus").click(function(){
			const qtt = parseInt($("#quantity").val());
			
			if(qtt > 1){
				let qut = qtt-1;
				$("#quantity").val(qut);
			}else{
				alert('1이하로 내릴수 없어요!');
			}
		});
		
		$(".plus").click(function(){
			const qtt = parseInt($("#quantity").val());
			const stock = parseInt($("#pstock").val());
			
			if(qtt < stock){
				let qut = qtt+1;
				$("#quantity").val(qut);
			}else{
				alert("더 이상 재고가 없어요!");
			}
		});
		
		$("#quantity").keyup(function(){
			const qtt = parseInt($("#quantity").val());
			const stock = parseInt($("#pstock").val());
			
			if(qtt > stock){
				alert("재고가 없어요!");
				$("#quantity").val(1);
			}else if(qtt < 1){
				alert('1이하로 내릴수 없어요!');
				$("#quantity").val(1);
			}
		});
	</script>
</body>
</html>