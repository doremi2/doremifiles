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
tr,td{
width : 100px;
height : 50px;
text-align: center;
}
body{
color : white;
background-color: black;
}
</style>
</head>
<body>
${updateMsg}
<jsp:include page="Mainheader.jsp"></jsp:include> 
<center>
<h1>MO.VIE 내정보 </h1>
${upDate}<br/>
</center>
</body>
<script>
function goMain(){
	location.href = 'movieMain';
}

function changetag(){
	$("#nickname").append("<form action='updatenickname' id='updatenickname'>");
	$("#updatenickname").append("<input type='text' name='nickname' value='"+$("p").text()+"'/>");
	$("p").remove();
	$("#updatenickname").append("<button>닉네임수정</button>");
	$("#updatenickname").append("</form>");
	$("#changebtn").remove();
	
	
}
</script>
</html>