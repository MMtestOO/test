// 로그인
$(document).ready(function(){
	$("#signInBtn").click(function(){
		
		/*
		function reload() {
			location.reload();
		}*/
		
		var id=$("#floatingID").val();
		var pw=$("#floatingPassword").val();
		
		$.post("signIn",
			{			   
				id:id,
				pw:pw
			},
			function(data){
				var obj=JSON.parse(data);			  
			  	
				if(obj.name){
				 	//alert(obj.name+ "님 환영합니다.");
					Swal.fire({
						  title: obj.name+"님 환영합니다.",
						  icon: 'success',
						  showConfirmButton: false,
						  timer: 5000
					})
				 	location.href = "home";
					//setTimeout("location.href='home'",5000);
				 	//setTimeout(location,10000);
					$.cookie("logined",obj.name + "님 환영합니다.", {path:'/'});	
				}else{
					//alert(obj.msg);
					Swal.fire({
						  title: obj.msg,
						  icon: 'warning',
						  showConfirmButton: false,
						  timer: 5000
					})
					//location.reload();
					//setTimeout(reload,5000);
					//setTimeout("location.reload()",5000);
					//location.href="/";
				}	
			});
		alert("무비 멘토 접속 중");
		/*
		Swal.fire({
			  title: '안녕하세요. Movie Mentor입니다.',
			  icon: 'info',
			  showConfirmButton: false,
			  timer: 5000
		})*/
	});
});


//로그인 쿠키 처리
$(document).ready(function(){ 
	$(function(){
		var login=$.cookie('logined');
		
	$("#msgDiv").html(login);
	});

});


// 로그아웃
$(document).ready(function(){
	$("#signOutBtn").click(function(){
		
		function location() {
			location.href="/";
		}
		
	/*if (confirm('로그아웃 하시겠습니까?')){
	$.post("signOut",
		{
		   
		},
		function(data){		  
			alert(data);	
			$.removeCookie("logined", {path:'/'});
			location.href = "/";	
			}		   
		);
		}*/
		
		Swal.fire({
			  title: '로그아웃 하시겠습니까?',
			  icon: 'question',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((result) => {
			  if (result.isConfirmed) {
				  $.post("signOut",
							{
							   
							},
							function(data){		  
								Swal.fire({
									  icon: 'success',
									  title: data,
									  showConfirmButton: false,
									  timer: 2000
									})
								$.removeCookie("logined", {path:'/'});
								//setTimeout(location,2000);
								setTimeout("location.href='/'",2000);
								//location.href = "/";
								}		   
							);
			  }
			})
		
	});
});

// 회원가입
$(document).ready(function(){
	$("#signUpBtn").click(function(){ 
		$.post("signUp",
		{

		}
		);
	});
});

//회원 정보 수정
$(document).ready(function(){
	$("#memberUpdateBtn").click(function(){

		function close() {
			window.close();
		}
		
		var name=$("#floatingName").val();
		var pw=$("#floatingPassword").val();


		var prefer_arr = [];
		$("input[name='preference']:checked").each(function() {
				var prefer = $(this).val();
			prefer_arr.push(prefer);
		});

		if(pw == ""){
			//alert("비밀번호 값은 필수 입력입니다.");
			Swal.fire({
				  title: '비밀번호 값은 필수 입력입니다.',
				  icon: 'warning',
				  showConfirmButton: false,
				  timer: 1500
			})
			
		}else if (name == "" && prefer_arr.length == 0){
			//alert("수정된 정보가 없습니다.");

			Swal.fire({
				  title: '수정된 정보가 없습니다.',
				  icon: 'warning',
				  showConfirmButton: false,
				  timer: 1500
			})
			
		}else{
			$.ajax({
				method : "POST",
				url : "memberUpdate",
				traditional : true,
				data : {
				name:name,
				pw:pw,
				prefer:prefer_arr
				},
				success : function(data){
				if(data != "비밀번호가 일치하지 않습니다." && data != "수정된 정보가 없습니다."){
				//alert(data);
					/*Swal.fire({
						  title: '정말 회원 정보를 수정하시겠습니까?',
						  icon: 'question',
						  showCancelButton: true,
						  confirmButtonColor: '#3085d6',
						  cancelButtonColor: '#d33',
						  confirmButtonText: '확인',
						  cancelButtonText: '취소'
						}).then((result) => {
						  if (result.isConfirmed) {
							  //Swal.fire(data);
							  Swal.fire({
								  icon: 'success',
								  title: data,
								  showConfirmButton: false,
								  timer: 2000
								});
							setTimeout(close, 2000);
						  }
						})*/
					
					Swal.fire({
						  icon: 'success',
						  title: data,
						  showConfirmButton: false,
						  timer: 2000
					})
				opener.parent.location.reload();
				//window.close();
				setTimeout(close, 2000);
				}else{
					//alert(data);
					Swal.fire({
						  icon: 'warning',
						  title: data,
						  showConfirmButton: false,
						  timer: 2000
					})
				}
				}
			});
		}
	});
});


//회원 탈퇴
$(document).ready(function(){
	$("#memberDeleteBtn").click(function(){ 
		
		function location(){
			location.href = "/";
		}
		
		/*if (confirm('정말 탈퇴하시겠습니까?')){
			$.post("memberDelete",
					{
					   
					},
					function(data){		  
						alert(data);	
						$.removeCookie("logined", {path:'/'});
						location.href = "/";	
						}		   
					);
		}*/
		
		Swal.fire({
			  title: '정말 탈퇴하시겠습니까?',
			  text: '탈퇴하면 정보를 다시 되돌릴 수 없습니다.',
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '확인',
			  cancelButtonText: '취소'
			}).then((result) => {
			  if (result.isConfirmed) {
				  $.post("memberDelete",
							{
							   
							},
							function(data){
								Swal.fire({
									  icon: 'success',
									  title: data,
									  showConfirmButton: false,
									  timer: 2000
									})
								//alert(data);
								$.removeCookie("logined", {path:'/'});
								setTimeout("location.href='/'",2000);
								//location.href = "/";
								//setTimeout(location,2000);
								}
					);
			  }
			})
		
	});
});


// 멤버 추가
$(document).ready(function(){
	$("#memberInsertBtn").click(function(){ 
	
		function close() {
			window.close();
		}
		
		var name=$("#floatingName").val();
		var id=$("#floatingID").val();
		var pw=$("#floatingPassword").val();


		var prefer_arr = [];
		$("input[name='preference']:checked").each(function() {
				var prefer = $(this).val();
			prefer_arr.push(prefer);
			});

		if(name == ""){
			//alert("이름 값은 필수 입력입니다.");
			
			Swal.fire({
				  title: '이름 값은 필수 입력입니다.',
				  icon: 'warning',
				  showConfirmButton: false,
				  timer: 1500
			})

		}
		else if(id == ""){
			//alert("아이디 값은 필수 입력입니다.");
			
			Swal.fire({
				  title: '아이디 값은 필수 입력입니다.',
				  icon: 'warning',
				  showConfirmButton: false,
				  timer: 1500
			})

		}
		else if(pw == ""){
			//alert("비밀번호 값은 필수 입력입니다.");
			
			Swal.fire({
				  title: '비밀번호 값은 필수 입력입니다.',
				  icon: 'warning',
				  showConfirmButton: false,
				  timer: 1500
			})
			
		}else{
			if($("input:checkbox[name='preference']").is(":checked")==false){
				//alert("하나 이상의 취향 선택은 필수입니다.");
				
				Swal.fire({
					  title: '하나 이상의 취향 선택은 필수입니다.',
					  icon: 'warning',
					  showConfirmButton: false,
					  timer: 1500
				})
				
			}else{
				$.ajax({
					method : "POST",
					url : "memberInsert",
					traditional : true,
					data : {
						name:name,
						id:id,
						pw:pw,
						prefer:prefer_arr
					},
					success : function(data){
						if(data != "아이디가 중복입니다."){
						//alert(data);
							Swal.fire({
								  title: data,
								  icon: 'success',
								  showConfirmButton: false,
								  timer: 1500
							})
						opener.parent.location.reload();
						//window.close();
						setTimeout(close, 2000);
						}else{
							//alert("아이디가 중복입니다.");
							Swal.fire({
							  title: '아이디가 중복입니다.',
							  icon: 'warning',
							  showConfirmButton: false,
							  timer: 1500
						})
						}
					}
				});
			}
		}


	});

});