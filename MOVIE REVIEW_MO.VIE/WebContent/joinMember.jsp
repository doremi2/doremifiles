<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 영화의 모든 리뷰 MO.VIE</title>
<link rel="icon" href="img/favicon.ico">
<style>
body{
background-color: black;
color : white;
}
</style>
</head>
<body>
<jsp:include page="Mainheader.jsp"></jsp:include>
<center>
<h1>MO.VIE 회원가입</h1>
<form action="joinMember" method="post">
<input type="text" name="id" id="id" placeholder="아이디 입력(20자 내)"/><br/>
<input type="password" name="pw" id="pw" placeholder="비밀번호 입력(20자 내)"/><br/>
<input type="text" name="name" id="name" placeholder="이름 입력"/><br/>
<input type="text" name="nickname" id="nickname" placeholder="닉네임 입력(10자 내)"/><br/>
<input type="text" name="age" id="age" placeholder="나이(숫자만)"/><br/>
<input type="radio" name="gender" value="남"/>남자
<input type="radio" name="gender" value="여"/>여자<br/>
${joinFailMsg}
<button>회원가입</button>
</form>
</center>
</body>
</html>