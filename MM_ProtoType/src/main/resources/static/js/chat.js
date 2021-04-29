//검색

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
$(document).ready(function(){ 
var me = {};

var you = {};

function formatAMPM(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}            

//-- No use time. It is a javaScript effect.
function insertChat(who, text, time){
    var control = "";
    var date = formatAMPM(new Date());
    
    if (who == "me"){
        
        control = '<li style="width:100%">' +
                        '<div class="msj macro">' +
                            '<div class="text text-r">' +
                            	'<br/>' +
                                '<p>'+ text +'</p>' +
                                '<p><small>'+date+'</small></p>' +
                                 '<br/>' +
                            '</div>' +
                        '</div>' +
                    '</li>';                    
    }else{
        control = '<li style="width:100%;">' +
                        '<div class="msj-rta macro">' +
                            '<div class="text text-l">' +
                            	'<br/>' +
                                '<p>'+text+'</p>' +
                                '<p><small>'+date+'</small></p>' +
                                '<br/>' +
                      		  '</div>' +	
                        '</div>' +
		              '</li>';
    }
    setTimeout(
        function(){                        
            $(".message-box").append(control);

        }, time);
    
}

function resetChat(){
    $(".message-box").empty();
}

$(".mytext").on("keyup", function(e){
    if (e.which == 13){
        var text = $(this).val();
        if (text !== ""){
            insertChat("me", text);              
            $(this).val('');
        }

		$.get("chat",{
			chat:text
			},function(data){	
				var text = data;
		        if (text !== ""){
		            insertChat("mm", text,15);              
		        }	  
				}		   
			);
    }
});


resetChat();

});
