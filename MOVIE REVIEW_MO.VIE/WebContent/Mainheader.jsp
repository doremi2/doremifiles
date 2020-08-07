<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://aJax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
#headerimg{
background-image: url("img/피카츄re.png");
background-size: 1050px 400px;
background-repeat: no-repeat;
border: none;
width: 1050px;
height : 400px;
margin-left: 350px;
}
#mainname {
margin-left:320px;
color: darkred;
font-family: sans-serif;
text-shadow: 2px 0 0 #fff, -2px 0 0 #fff, 0 2px 0 #fff, 0 -2px 0 #fff, 1px 1px #fff, -1px -1px 0 #fff, 1px -1px 0 #fff, -1px 1px 0 #fff;
}

</style>
</head>
<body>
<header id="headerimg">
<h1 id="mainname"><br><br><br><br><br>내 영화의 모든 리뷰 MO.VIE</h1>
</header>
</body>
<script>
$("#headerimg").click(function(){
	location.href = 'movieMain';
})
</script>
</html>