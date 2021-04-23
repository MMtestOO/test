//검색 
$(document).ready(function(){ 
	$("#searchBtn").click(function(){
		
		var movieTitle=$("#movieTitle").val();
		if (movieTitle == ""){
			$.get("movieList",{
				});
		}
		else {
			$.get("moiveSearch",{
					movieTitle : movieTitle,
			});
		}
	});
});