<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://aJax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>


a{
text-decoration: none;
font-size: 14px;

}
button{
width : 70px;
height : 25px;
}
#id{
margin-left: -40px;
}
#pw{
margin-left: 30px;
}
#errorlogin{
margin-left : 30px;
font-size: 15px;
color : white;
}
#joina{
margin-left: 30px;
}
</style>
</head>

<body>
<c:if test="${empty id}">

<form action="loginaccess" method="post">
<br/>
<input type="text" name="id" id="id" placeholder="ID"/>
<input type="password" name="pw" id="pw" placeholder="PASSWORD"/>
<button>로그인</button><br/>
${loginFailMsg}
</form>
<a href="Moviejoin" id='joina'>회원이 아니세요?(회원가입)</a><br/><br/>
</c:if>

<c:if test="${!empty id}">
<br/>
${nickname}님 , 안녕하세요!<br/><br/>
${workList}<br/><br/>
</c:if>
</body>
</html>