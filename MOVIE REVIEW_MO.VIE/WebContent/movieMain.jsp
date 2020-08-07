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
body{
background-color: black;
color : white;
}

#aside {
	margin-left: 50px;
	margin-top: 20px;
	width: 300px;
	height: 800px; clear : both;
	float: left;
	clear: both;
	border : none;
}



div {
	display: block;
}

#contents {
	width: 1300px;
	height: 800px;
	margin-left: 370px;
	margin-top: 20px;
	overflow: auto;
	border : 2px solid darkred;
}

#footer {
	width: 1700px;
	height: 200px;
	margin-top: 50px;
	border : none;
}

#list {
	margin-top: 5px;
	list-style: none;
}

ul li {
	float: left;
	padding: 20px;
	background-color: black;
	color: white;
}

a {
	color: white;
	padding: 5px;
	margin-top: 10px;
}

#navivar {
	width: 1700px;
	height: 70px;
	background-color: black;
	border: none;
}
.detailcode{
border: none;
padding: 22px;
margin-left: 5px;
float : left;
}
.listimg{
width : 200px;
height : 300px;
}
button{
float:right;
text-decoration: none;
background-color: black;
border: none;
color : red;
outline : none;
}
#aside img{
width: 250px;
height: 130px;
}
b{
font-size : 20px;
color : red;
}
</style>
</head>
<body>
	${joinMsg} ${logout} ${successUploadMsg} ${mlDetail} ${nullIDMsg} ${deleteMsg} ${showListerror} ${nullpoint}

	<jsp:include page="Mainheader.jsp"></jsp:include>
	<div id="navivar">
		<ul id="list">
			<li>전체리뷰</li>
			<li>인기리뷰</li>
			<li>추천영화</li>
			<li>자유게시판</li>
		</ul>
	</div>

	<div id="aside">
	<center>
	<jsp:include page="loginForm.jsp"></jsp:include>
	<b>▽영화 예매하러가기▽</b><br/>
	<a href = 'https://www.youtube.com'>
	<img src = 'img/youtube.png'/>
	</a>
	<a href = 'https://www.netflix.com/kr/'>
	<img src = 'img/넷플릭스2.png'/>
	</a>
	<a href = 'http://www.cgv.co.kr/'>
	<img src = 'img/cgv.png'/>
	</a>
	<a href = 'https://www.megabox.co.kr/'>
	<img src = 'img/megabox.png'/>
	</a>
	<a href = 'https://www.lottecinema.co.kr/'>
	<img src = 'img/lotte.png'/>
	</a>
</center>
	</div>
	<div id="contents"></div>
	<div id="footer">
	<center>
	<br/>
	<p>회원약관 | 개인정보처리방침 | 이메일무단수집거부 | 영상정보처리기기 운영∙관리방침 | MV.POINT회원안내 | 광고/임대문의 | 윤리경영 | 기업정보</p>
	<p> 제작 MO.VIE | 대표 박혜인 | 고객센터 032-876-3332 | 사업자 등록번호 110-46-934239 | 소속 인천일보 아카데미 오픈 플랫폼 3기</p>
	<p>인천 미추홀구 매소홀로488번길 6-32 태승빌딩 4층 3강의실 </p>
	<p>COPYRIGHT© MO.VIE ALL RIGHT RESERVED.</p>
	</center>
	</div>

</body>
<script>
Aj("showAllitem","#contents");
function Aj(url,position){
	$.ajax({
		url : "showAllitem",
		type : "post",
		success : function(page){
			$(position).html(page);
		},
		error : function(err){
			consol.log(err);
		}
	})
};
</script>
</html>