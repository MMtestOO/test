
//영화 자세히 보기
$(document).ready(function(){
	$("button[name=movieDetailBtn]").click(function(){ 
		var index = $(this).attr("id");
		var movieTitle=$('.movieTitle').eq(index).text();
		$.get('cartMovieDetail',
				{			   
					movieTitle:movieTitle
				},
				function(data){
					window.open('cartMovieDetail?movieTitle='+movieTitle,'_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=500,height=500');
				});
		
	});
});
