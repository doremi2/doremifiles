<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 영화의 모든 리뷰 MO.VIE</title>
<link rel="icon" href="img/favicon.ico">
<script
	src="https://aJax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
th, tr, td {
	text-align: center;
	padding: 10px;
}

th {
	font-size: 20px;
}

body {
	background-color: black;
	color: white;
}

a {
	text-decoration: none;
	color: red;
}

div {
	display: block;
}

#blist {
	width: 800px;
	height: 500px;
	float: left;
	margin-left: 30px;
}

#detailbox {
	width: 800px;
	height: 500px;
	margin-left: 40px;
	float: left;
}
</style>
</head>
<body>
	<jsp:include page="Mainheader.jsp"></jsp:include>
${deleteBMsg}
	<div id="blist">
		<center>
			<h1>리스트</h1>
			<br /> ${showBlackList}
		</center>
	</div>
	<div id="detailbox">
	<center>
	<h1>상세 내용</h1>
	</center>
	<div id="detailblist">
	
	</div>
	</div>
</body>
<script>

$(".detailbid").click(function(){
	$.ajax({
		url : 'searchBLReview',
		type : 'post',
		data : {data : $(this).data('code')},
		dataType : 'json',
		success : function(data){
			console.log(data[0]);
			var str = "";
			 str += "<center><table><tr><th>게시물 코드</th><th>리뷰 내용</th></tr>";
			 for(var i in data){
			 str += "<tr><td>"+data[i].rcode+"</td>";
			 str += "<td>"+data[i].rdetail+"</td></tr>";
			 }
			 str += "</table></center>";
			var strresult = str;
			$("#detailblist").html(strresult);
		},
		error : function(error){
			console.log(data);
		}
	})
});
</script>
</html>