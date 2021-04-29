//검색
var isEntered = false;

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

