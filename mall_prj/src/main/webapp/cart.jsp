<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="box">
			<section>
				<div id="title1">장바구니</div>
				<hr>
				<form action="buy.do" method="post">
				<table id="board">
					<thead id="thead">
						<tr>
							<th><input type="checkbox" id="all"></th>
							<th>제품이미지</th>
							<th>제품명</th>
							<th>가격</th>
							<th>수량</th>
							<th>X<th>
						</tr>
					</thead>
					<tbody id="tbody">
					
					</tbody>
				</table>
				<div id="calc">
					가격 : <input id="result" value="0" readonly>
				</div>
					<input id="write" type="submit" value="물품 구매">
				</form>
			</section>
		</div>
	</div>
	<%@ include file="footer.jsp" %>   
<script>

$(document).ready(getList());

function getList(){
	$.ajax({
		url: "getCart.do",
		type: "get",
		dataType: "text",
		success: function(data){ 

			let obj = JSON.parse(data);
			
			for (i = 0; i < obj.length; i++) {
				$("#tbody").append('<tr><td><input type="checkbox" name="products" class="chk"></td><td><input type="hidden" value="' 
						+ obj[i].pno + '"><input type="hidden" value="' + obj[i].pstock + '"><img src="img/'
						+ obj[i].pimg +'"></td><td>'+ obj[i].pname + '</td><td>' 
						+ obj[i].pprice + '</td><td><img class="minus" src="img/minus.png"><input type="text" class="qtt" value="' 
						+ obj[i].pqtt + '"><img class="plus" src="img/plus.png"></td><td>X</td></tr>');
				}

		},
		error: function(){
			alert("error.....");
		}
	});
}

$("#all").change(change);

function change(){
	
	$("#result").val(0);
	for(let i = 0; i < $(".chk").length; i++){
		if($("#all").is(":checked")){
			$(".chk").eq(i).prop("checked", true);
			let qtt = parseInt($(".chk").eq(i).parent().parent().children().last().prev().children().eq(1).val());
			let price = parseInt($(".chk").eq(i).parent().parent().children().last().prev().prev().text());
			let result = parseInt($("#result").val());
			$("#result").val(result+(qtt*price));
		}else{
			$(".chk").prop("checked", false);
		}
	}
}

$("#tbody").click(function(e){
	if($(e.target).hasClass("minus")){
		if($(e.target).next().val() == 1){
			alert("내릴수없음");
			return;
		}
		
		const qutt = parseInt($(e.target).next().val())-1;
		const price = parseInt($(e.target).parent().prev().text());
		const result = parseInt($("#result").val());
		
		$.ajax({
			url: "changeQtt.do",
			type: "get",
			data: {
				qtt : qutt,
				pno : $(e.target).parent().prev().prev().prev().children().first().val()
			},
			dataType: "text",
			success: function(data){
				$(e.target).next().val(data);
				if($(e.target).parent().parent().children().first().children().first().is(":checked")){
					$("#result").val(result-price);
				}
			}
		})

	}
});

$("#tbody").click(function(e){
	if($(e.target).hasClass("plus")){
		
		const qutt = parseInt($(e.target).prev().val())+1;
		const price = parseInt($(e.target).parent().prev().text());
		const result = parseInt($("#result").val());
		
		$.ajax({
			url: "changeQtt.do",
			type: "get",
			data: {
				qtt : qutt,
				pno : $(e.target).parent().prev().prev().prev().children().first().val()
			},
			dataType: "text",
			success: function(data){
				$(e.target).prev().val(data);
				if($(e.target).parent().parent().children().first().children().first().is(":checked")){
					$("#result").val(result+price);
				}
			}
		})

	}
});

$("#tbody").keyup(function(e){
	if($(e.target).hasClass("qtt")){
		const qutt = parseInt($(e.target).val());

		$.ajax({
			url: "changeQtt.do",
			type: "get",
			data: {
				qtt : qutt,
				pno : $(e.target).parent().prev().prev().prev().children().first().val()
			},
			dataType: "text",
			success: function(data){
				$("#result").val(0);
				for(let i = 0; i < $(".chk").length; i++){
					$(".chk").eq(i).prop("checked", true);
					let qtt = parseInt($(".chk").eq(i).parent().parent().children().last().prev().children().eq(1).val());
					let price = parseInt($(".chk").eq(i).parent().parent().children().last().prev().prev().text());
					let result = parseInt($("#result").val());
					$("#result").val(result+(qtt*price));
					
				}
			}
		})

		
	}
});


$("#tbody").click(function(e){
	if($(e.target).hasClass("chk") && $(e.target).is(":checked")){
		const price = parseInt($(e.target).parent().parent().children().eq(3).text());
		const qtt = parseInt($(e.target).parent().parent().children().eq(4).children().eq(1).val());
		let result = parseInt($("#result").val());
		$("#result").val(result+(price*qtt));
	}else if($(e.target).hasClass("chk") && !$(e.target).is(":checked")){
		const price = parseInt($(e.target).parent().parent().children().eq(3).text());
		const qtt = parseInt($(e.target).parent().parent().children().eq(4).children().eq(1).val());
		let result = parseInt($("#result").val());
		$("#result").val(result-(price*qtt));
	}

});

$("#tbody").click(function(e){
	if($(e.target).text() == 'X'){
		$.ajax({
			url: "delPIC.do",
			type: "get",
			data: {
				pno : $(e.target).prev().prev().prev().prev().children().eq(0).val()
			},
			dataType: "text",
			success: function(data){
				document.querySelector("#tbody").replaceChildren();
				getList();
				if($(e.target).parent().children().first().children().first().is(":checked")){
					$("#result").val(0);
					for(let i = 0; i < $(".chk").length; i++){
						$(".chk").eq(i).prop("checked", true);
						
						let qtt = parseInt($(".chk").eq(i).parent().parent().children().last().prev().children().eq(1).val());
						let price = parseInt($(".chk").eq(i).parent().parent().children().last().prev().prev().text());
					
						let result = parseInt($("#result").val());

						$("#result").val(result+(qtt*price));
					}
				}
				
			}
		})
	}
});

$("#thead").click(function(e){

	if($(e.target).text() == 'X'){
		let pnos = new Array();
		
		for(let i = 0; i < $(".chk").length; i++){
			if($(".chk").eq(i).is(":checked")){
				pnos.push($(".chk").eq(i).parent().next().children().eq(0).val());
			}
		}
		$.ajax({
			url: "delCPIC.do",
			type: "get",
			traditional: true,
			data: {
				pnos : pnos
			},
			dataType: "text",
			success: function(data){

				document.querySelector("#tbody").replaceChildren();
				getList();
				$("#result").val(0);
				for(let i = 0; i < $(".chk").length; i++){
					if($(".chk").eq(i).prop("checked", true)){
						
						let qtt = parseInt($(".chk").eq(i).parent().parent().children().last().prev().children().eq(1).val());
						let price = parseInt($(".chk").eq(i).parent().parent().children().last().prev().prev().text());
						let result = parseInt($("#result").val());
						$("#result").val(result+(qtt*price));
					}
					
				}
				
			},
			error: function(){
				alert("체크된 항목이 없습니다");
			}
		})
	}
});

Number.prototype.formatNumber = function(){

    if(this==0) return 0;

    let regex = /(^[+-]?\d+)(\d)/;

    let nstr = (this + '');

    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');

    return nstr;

};

</script>
</body>
</html>