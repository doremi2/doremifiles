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
color : white;
background-color: black;
}
</style>
</head>
<body>
${upLoadFailMsg}
<jsp:include page="Mainheader.jsp"></jsp:include> 
<center>
<h1>게시물 등록</h1>
<form action="upLoadMovie" method="post" enctype="multipart/form-data"><br/><br/>
<input type="text" name="mlname" id="mlname" placeholder="게시물이름입력(영화제목,30자이내)" style='width:400px; height:50px;'><br/><br/>
장르  <input type="text" name="mlgenre" id="mlgenre" placeholder="5자이내"style='width:85px; height:20px;'>
개봉일  <input type="text" name="mlplayday" id="mlplayday" placeholder="YYYYMMDD(-제외 숫자입력)" style='width:180px; height:20px;'><br/><br/>
감독  <input type="text" name="mlproducer" id="mlproducer" placeholder="감독(20자이내)"  COLS=70 ROWS=4><br/><br/>
<textarea name="mldetail" id="mldetail" placeholder="내용" style='text-align:center; width:400px; height:200px;'></textarea><br/><br/>
트레일러 동영상
<input type="text" name='mlvideo' placeholder="소스코드 고유번호만 입력하세요" style='width:270px; height:20px;' /><br/><br/>
<input type="file" name="mlimg" id="mlimg"><br/><br/>
<button>게시물 등록하기</button>
</form>
</center>
</body>
</html>