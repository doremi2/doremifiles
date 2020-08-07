<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 영화의 모든 리뷰 MO.VIE</title>
<link rel="icon" href="img/favicon.ico">
<style>
img{
width: 100px;
height : 200px;
}
th,tr,td{
text-align: center;
padding : 10px;
}
th{
font-size: 20px;
}

body{
background-color: black;
color:white;
}

</style>
</head>
<body>
${deleteMsg}
<jsp:include page="Mainheader.jsp"></jsp:include>
<center>
<div id="list">
<h1>전체 영화</h1><br/>
${uList}
</div>
</center>
</body>
<script>

</script>
</html>