//검색

$(document).ready(
		function() {
			var me = {};

			var you = {};

			function formatAMPM(date) {
				var hours = date.getHours();
				var minutes = date.getMinutes();
				var ampm = hours >= 12 ? 'PM' : 'AM';
				hours = hours % 12;
				hours = hours ? hours : 12; // the hour '0' should be '12'
				minutes = minutes < 10 ? '0' + minutes : minutes;
				var strTime = hours + ':' + minutes + ' ' + ampm;
				return strTime;
			}

			//-- No use time. It is a javaScript effect.
			function insertChat(who, text, time) {
				var control = "";
				var date = formatAMPM(new Date());

				if (who != "me") {

					control = '<li style="width:100%">'
							+ '<div class="msj macro">'
							+ '<div class="text text-r">' + '<br/>' + '<p>'
							+ text + '</p>' + '<p><small>' + date
							+ '</small></p>' + '<br/>' + '</div>' + '</div>'
							+ '</li>';
				} else {
					control = '<li style="width:100%;">'
							+ '<div class="msj-rta macro">'
							+ '<div class="text text-l">' + '<br/>' + '<p>'
							+ text + '</p>' + '<p><small>' + date
							+ '</small></p>' + '<br/>' + '</div>' + '</div>'
							+ '</li>';
				}
				setTimeout(function() {
					$(".message-box").append(control);

				}, time);

			}
			
			//-- No use time. It is a javaScript effect.
			function insertMovie(movies, posters, time) {
				var control = "";
				for(var i=0; i<movies.length; i++){
					control += '<div class="col-lg-4 col-md-6 mb-4">'+
		            		+'<div align="center" class="card h-100">'
		            		+'<div class="card-header text-white bg-secondary"><h4 th:text="'+movies[i].movieTitle+'" class="movieTitle"></h4></div>'
		            		+'<div class="card-body">'
		            		+'<p th:text="\'장르 : ' + movies[i].movieGenre+'"></p>'
		            		+'<p th:text="\'평점 : ' + movies[i].movieRating +'"></p>'
		                  	+'<img id="poster" th:alt="'+movies[i].movieTitle+ ' 포스터" th:src="/poster/'+ posters[i].posterPath + '/'+'"  width="25" height="25%"/>'
		                  	+'<div class = "image_overlay image_overlay_blur">'
							+'<div class = "image_movieStory" th:text="'+movies[i].movieStory+'"></div>'
							+'<br><br><br>'
							+'<form method="get" action="movieDetail">'
					        +'<input type="hidden" name="movieTitle" th:value="'+ movies[i].movieTitle+ '">'
					        +'<button class="movieDetailBtn" type="submit" name="movieDetailBtn">자세히 보기</button>'
				            +'</form>'
				            +'<br>'
					        +'<div> <button class="cartInsertBtn" th:classappend="'+i+'" name="cartInsertBtn">담기</button></div>'
					        +'<br>'
							+'</div>'
							+'<br><br>'
							+'<p th:text="'+new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(movies[i].moviePrice)+'"></p>'
			                +'<br>'
			                +'</div>'
			                +'</div>'
			                +'</div>';
				}

				setTimeout(function() {
					if(control != ""){
						alert(control);
						$(".movie-box").html(control);
					}

				}, time);

			}

			function resetChat() {
				$(".message-box").empty();
			}

			$(".mytext").on("keyup", function(e) {
				if (e.which == 13) {
					var text = $(this).val();
					if (text !== "") {
						insertChat("me", text);
						/*$(".message-box").scrollTop($(".message-box")[0].scrollHeight);*/
						$(this).val('');
					}

					$.get("chat", {
						chat : text
					}, function(data) {
						var obj=JSON.parse(data);
						var text = obj.chatMsg;
						var movies = obj.movies;
						var posters = obj.posters;
						
						if (text !== "") {
							insertChat("mm", text, 15);
							insertMovie(movies, posters, 15);
							/*$(".message-box").scrollTop($(".message-box")[0].scrollHeight);*/
				            $(".message-box").animate({scrollTop: '9999999999'}, 100);
								
						}
					});
				}
			});

			resetChat();
			insertChat("mm", "안녕하세요. 취향에 맞는 영화를 찾아주는 MM입니다.\n\n'영화 추천', '취향에 맞는 영화' 등을 입력해서 취향에 맞는 영화를 찾아보세요.\n\n원하는 영화 결과가 없을 경우 채팅창에 '다시'를 입력 해 주세요.", 0);

		});













/*var isEntered = false;
$(document).ready(function(){ 
	$("#btn-chat").click(function(){
		
		var chat=$("#btn-input").val();
			$.get("chat",{
				chat:chat
				
				},function(data){		  
					//location.href="home";	
				}		   
			);
			
			isEntered = true;
			
			if (isEntered) {
			      isEntered = false;
			      document.querySelector("#btn-input").value = '';
			}
	});
	
	$("#btn-input").keyup(function(e){
		if(e.keyCode == 13){
			var chat=$("#btn-input").val();
			$.get("chat",{
				chat:chat
				
				},function(data){		  
					//location.href="home";	
					}		   
				);
			
			isEntered = true;
			
			if (isEntered) {
			      isEntered = false;
			      document.querySelector("#btn-input").value = '';
			}
		}
	});
});
*/