// 장바구니 추가
$(document).ready(function(){
	$("button[name=cartInsertBtn]").click(function(){ 
	
		var index = $(this).attr("class");
		
		var movieTitle=$('.movieTitle').eq(index).text();	
		
	$.post("cartInsert",
		{			   
			movieTitle:movieTitle
		},
		function(data){
			 	alert(data);
			});
		});
});


// 장바구니 삭제
$(document).ready(function(){
	$('button[name=cartDeleteBtn]').click(function(){ 
		
		var index = $(this).attr("id");
		
		var movieTitle=$('.movieTitle').eq(index).text();
		
	if (confirm('정말 삭제하시겠습니까?')){
		$.post("cartDelete",
				{
				   movieTitle:movieTitle
				},
				function(data){		  
					alert(data);	
					location.reload();	
				});
		}
	});
});

//영화 구매창
$(document).ready(function(){
	$("button[name=movieOrderBtn]").click(function(){ 
		var index = $(this).attr("id");
		var movieTitle=$('.movieTitle').eq(index).text();
		$.get('movieOrder',
				{			   
					movieTitle:movieTitle
				},
				function(data){
					window.open('movieOrder?movieTitle='+movieTitle,'_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=400,height=500');
				});
		
	});
});

//영화 구매 취소
$(document).ready(function(){
	$("#cancleBtn").click(function(){ 	
		var movieTitle=$('.movieTitle').text();	
		if (confirm(movieTitle + ' 구매를 취소하시겠습니까?')){
			alert("구매가 취소되었습니다.");
			opener.parent.location.href = "cart";
			window.close();
		}
	});	
});

//영화 구매
$(document).ready(function(){
	$("#orderBtn").click(function(){ 	
		var movieTitle=$('.movieTitle').text();	
		var orderMethod = "";
		var agreeCheck = "";
		$("input[name='orderMethod']:checked").each(function() {
			orderMethod = $(this).val();
		});
		$("input[name='agreeCheck']:checked").each(function() {
			agreeCheck = $(this).val();
		});
		
		if(agreeCheck == "" || orderMethod == ""){
			alert("구매조건 확인 및 결제 진행에 동의해 주세요.")
		}else{
			$.post("orderInsert",
					{			   
						movieTitle:movieTitle,
						orderMethod:orderMethod
					},
					function(data){
							if(data != "이미 구매한 영화 입니다."){
								alert(data);
								opener.parent.location.href = "orderList";
								window.close();
							}else{
								alert(data);
								alert("장바구니에서 삭제되었습니다.");
								window.close();
								location.href = "cart";
							}
					});
		}
	
		});
});

//영화 다운로드
$(document).ready(function(){
	$("button[name=downloadBtn]").click(function(){ 
		var index = $(this).attr("id");
		var movieTitle=$('.movieTitle').eq(index).text();
		if (confirm(movieTitle+' 를(을) 다운로드 받으시겠습니까?')){
			$.post("orderDown",
				{			   
					movieTitle:movieTitle,
				},
				function(data){
					alert(data);	
					location.reload();	
				});
			$.get("download",
				{			   
					movieTitle:movieTitle
				});
		}
	});
});


//영화 환불
$(document).ready(function(){
	$("button[name=refundBtn]").click(function(){ 	
		var index = $(this).attr("id");
		var movieTitle=$('.movieTitle').eq(index).text();
		if (confirm(movieTitle + ' 를(을) 정말 환불하시겠습니까?')){
			$.post("orderDelete",
					{			   
						movieTitle:movieTitle
					},
					function(data){
						alert(data);	
						location.reload();	
					});
			}
	});	
});

