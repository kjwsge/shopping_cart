<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/mall.css">
	<script src="js/jquery.js"></script>
</head>
<body>
	<div id="wrap">
		<%@ include file="header.jsp"%>
		<%@ include file="nav.jsp"%>
		<div id="board_section">
			<div class="box">
				<section id="main">
					<div id="title1">물품추가</div>
					<hr class="myhr">
					<div id="write2">
						<form action="addProduct.do" method="post" enctype="multipart/form-data">
							<table>
								<tr>
									<td><input class="subj" type="text" placeholder="상품명을 입력해주세요." name="pname"></td>
								</tr>
								<tr>
									<td><textarea id="cont" placeholder="상품 정보를 입력하세요"
											name="pinfo"></textarea></td>
								</tr>
								<tr>
									<td><input class="subj" type="text" placeholder="상품가격을 입력하세요" name="price"></td>
								</tr>
								<tr>
									<td><input class="subj" type="text" placeholder="재고량을 입력하세요" name="stock"></td>
								</tr>
								<tr>
									<td><input type="file" name="image"></td>
								</tr>
								<tr>
									<td style="text-align:right">
										<input type="submit" class="btn" value="확인">
										<input type="button" class="btn"
												onclick="location.href='mallList.jsp'" value="취소">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</section>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
<script>
$("input[type='submit']").click(function(){
	if($("#subj").val() == ''){
		alert("제목을 작성해주세요!!");
		$("#subj").focus();
		return false;
	}else if($("#cont").val() == ''){
		alert("내용을 작성해주세요!!");
		$("#cont").focus();
		return false;
	}else if($("input[type='file']").val() == ''){
		alert("파일을 첨부해주세요!!");
		$("input[type='file']").focus();
		return false;
	}else{
		alert("물품이 추가됩니다!!");
	}
});

</script>
</body>
</html>