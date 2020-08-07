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
color:white;
}
.code{
width: 500px;
}
.movie_info{
width : 450px;
height : 200px;
float:left;
}
.movie_img{
width : 300px;
height : 450px;
clear: both;
padding: 10px;
padding-right: 40px;
}
.movie_detail{
width : 750px;
}
#detail{
width: 800px;
height : 800px;
float:left;
margin-left: 10px;
padding: 10px;

}
#allreview{
margin-left: 20px;
width: 800px;
heigth: 800px;
float: left;
}
#write,#txta{
width:800px;
height:70px;
}
#selectscore{
width : 100px; 
height:30px; 
font-size : 15px;
display: block;
}
#btn{
float:right;
margin-top: -80px;
}

#updateform{
float:left;
}
#video{
width:400px;
height:300px;
margin-top: -300px;
}
#allpage{
width: 1750px;
}
#showreview{
margin-top : 50px;
width : 800px;
overflow: auto;
}
.notifybtn{
float:right;
text-decoration: none;
background-color: black;
border: none;
color : red;
outline : none;
}
h2{
color : darkred;
}
</style>
</head>
<body>
${UpReviewMsg} ${updateMsg} ${deleteMsg} ${notifyreivewMsg}

<jsp:include page="Mainheader.jsp"></jsp:include>
<div id="allpage">
${mldetail} 
<div id="allreview">
<h1>Review</h1>
<div id="uploadreview">
<form action ="upReview" method="post">
<input type="hidden" name="mcode" id="mcode">
<select id="selectscore" name="score">
<option value="1">1점</option>
<option value="2">2점</option>
<option value="3">3점</option>
<option value="4">4점</option>
<option value="5" selected="selected">5점</option>
<option value="6">6점</option>
<option value="7">7점</option>
<option value="8">8점</option>
<option value="9">9점</option>
<option value="10">10점</option><br/>
<textarea id="write" name="reviewDetail" placeholder="리뷰를  작성하세요"></textarea>
</select>
<button id='appendbtn'>리뷰등록</button>
</form>
</div>
<br/>
<div id="showreview">
</div>
</div>
</div>
</body>
<script>
function goMain(){
	location.href = "movieMain.jsp";
}


var sessionid = "<%=session.getAttribute("id") %>"
console.log(sessionid);


$("#mcode").attr("value", $("#detail").data('code'));
console.log($("#selectscore option:selected").val());
console.log($("#selectedscore").val())
console.log($("#mcode").val())
console.log($("#detail").data('code'));
console.log($("#selectscore option:selected").val());

Aj("showreview","#showreview");

function Aj(url,position){
 $.ajax({
	url : url,
	type : "post",
	data : {data : $("#detail").data('code')},
	datatype : 'json',
	success : function(result){
		console.log("result"+result);
		var jsondata=JSON.parse(result);
		console.log(jsondata);
		/* for(var i in jsondata){
			for(var j in jsondata[i]){
				console.log(jsondata[i][j]);
			}
		} */
		for(var i in jsondata){
		$(position).append(jsondata[i].rnickname+" | ");
		$(position).append("평점["+jsondata[i].rscore+"] | ");
		$(position).append(jsondata[i].rdate);
		
		if(sessionid==jsondata[i].rid){
			//$(position).append("<form action='updateMyreview' method='post' id='updateform'>");
			$(position).append("<br/>");
			$(position).append("<div id='reviewDetail'><h4>"+jsondata[i].rdetail+"</h4></div><br/>");
			$(position).append("<input type='hidden' value="+jsondata[i].rid+">");
			$(position).append("<input type='hidden' value="+$("#detail").data('code')+">");
			$(position).append("<input type='button' id='updatebtn' onclick='upDatereview()' value='수정'>");
			$(position).append("<form action='deleteMyreview' method='post' id='deleteform'>");
			$("#deleteform").append("<input type='hidden' name = 'code' value="+$("#detail").data('code')+">");
			$("#deleteform").append("<button class='myreview'>삭제</button>");

		}else{
			$(position).append("<form id='notify"+i+"' name='notify' action ='notifyreview' method='post'>");
			$('#notify'+i+'').append("<input type='hidden' name='rcode' value='"+$("#detail").data('code')+"'/>");
			$('#notify'+i+'').append("<input type='hidden' name='rid' value='"+jsondata[i].rid+"'/>");
			$('#notify'+i+'').append("<button class='notifybtn'>신고하기</button>"+"<br/>");
			$(position).append("<h4>"+jsondata[i].rdetail+"</h4><br/>");
		}
		$(position).append("<hr/>");
		}
	},
	error : function(error){
		console.log(error);
	}
})
}
function upDatereview(){
	$("#reviewDetail").append("<form action ='updateReview' method='post' id='updateFrm'>");
	$("#reviewDetail h4").contents().unwrap().wrap("<textarea name='reDetail' id='txta'></textarea>");
	$("#updateFrm").append($("#txta"));
	$("#updateFrm").append("<input type='hidden' name='code' value='"+$("#detail").data('code')+"'>");
	$("#updateFrm").append("<button>수정</button>")
	$("#updatebtn").remove();
}
</script>
</html>