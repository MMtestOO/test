//검색 
$(document).ready(function(){ 
	$("#searchBtn").click(function(){
		
		var movieTitle=$("input[name=movieTitle]").val();
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

			/*
			function filter(){

	        var value, name, item, i;
	
	        value = document.getElementById("movieList").value.toUpperCase();
	        item = document.getElementsByClassName("movieTitle");
	
	        for(i=0;i<item.length;i++){
	          name = item[i].getElementsByClassName("searched");
	          if(name[0].innerHTML.toUpperCase().indexOf(value) > -1){
	            item[i].style.display = "flex";
	          }else{
	            item[i].style.display = "none";
	          }
	        }
      	}
      }
      */
		/*	$.ajax({
				type: "get",
				async: true,
				url: "movieSearch",
				data: {movieTitle:movieTitle},
				success: function(data, textStatus){
					var jsonInfo = JSON.parse(data);
					displayResult(jsonInfo);
				},
				error: function(data, textStatus){
					alert("에러가 발생했습니다."+data);
				},
				complete: function(data, textStatus){
					alert("작업을 완료했습니다.");
				}
			})
		}
		
		function displayResult(jsonInfo){
			var count = jsonInfo.movieTitle.length;
			if(count > 0){
				var html = '';
				for (var i in jsonInfo.movieTitle) {
					html += "<a href="javascript:select('"jsonInfo.movieTitle[i]+"')">"
					+jsonInfo.movieTitle[i]+"</a><br>";
				}
				var listView = document.getElementById("suggestList");
				listView.innerHTML=html;
				show('suggest');
			}else{
				hide('suggest');
			}
		}
		*/
		
	
});


