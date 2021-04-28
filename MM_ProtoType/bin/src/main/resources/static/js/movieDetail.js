// 영화 자세히 보기
$(document).ready(function(){ 
	$('button[name=movieDetailBtn]').click(function(){
		var index = $(this).attr("class");
		
		var movieTitle=$('.movieTitle').eq(index).text();
		
		$.ajax({
				type:"GET",
				url:"movieDetailSearch",
				data :{
					movieTitle:movieTitle
				},success : function(){
					alert("조회성공");
				},error : function(){
					alert("에러발생");
				}
			});
		alert("영화 자세히 보기");
	});
});