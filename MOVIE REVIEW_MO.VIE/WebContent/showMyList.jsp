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

td{
width : 150px;
height : 50px;
}
.Myreview{
width : 600px;
}
</style>
</head>
<body>
${deleteMsg}
<jsp:include page="Mainheader.jsp"></jsp:include>
<center>
<h1>내 리뷰</h1>
${myReview}
</center>
</body>
</html>