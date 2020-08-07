<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 영화의 모든 리뷰 MO.VIE</title>
<link rel="icon" href="img/favicon.ico">
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
</style>
</head>
<body>
<jsp:include page="Mainheader.jsp"></jsp:include>
${deleteNotifyMsg} ${addblacklistMsg}
<center>
<h1>신고내역</h1><br/>
${nList}
</center>
</body>
</html>