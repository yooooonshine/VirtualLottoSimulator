$("#id").focusout(function(){

 if($('#id').val() == ""){
    $('#check').text('아이디를 입력해주세요.');
    $('#check').css('color', '#6A24FE');

 }else{
     $('#check').hide();
 }
 });

$("#password1").focusout(function(){
 if($('#password1').val() == ""){
    $('#check').text('비밀번호를 입력해주세요');
    $('#check').css('color', '#6A24FE');
 }else{
     $('#check').hide();
 }
 });


//비밀번호 재확인 코드
$(function(){
    $("#alert-success").hide();
    $("#alert-danger").hide();
    $("input").keyup(function(){
        let pw1 = $("#password1").val();
        let pw2 = $("#password2").val();
        if(pw1 !== "" || pw2 != ""){
            if(pw1 == pw2){
                $("#alert-success").show();
                $("#alert-danger").hide();
                pwc = true; // 비밀번호같으면 pwc true
            }
            else{
                $("#alert-success").hide();
                $("#alert-danger").show();
                pwc = false;
            }
        }
    });
});


// //이메일 중복체크 변수
// var emoverChk = false;
//아이디 중복체크변수
 var overChk = false;



// //이메일 중복체크
//
// 	$("#emoverlay").click(function(){
// 		var email = $("#email").val();
// 			$.ajax({
// 				type:'post'
// 				,url:'emoverlay'
// 				,data:{'email' : email }
// 				,dataType:'JSON'
// 				,success:function(obj){
// 					console.log(obj);
// 					if(obj.use2 != 1){
// 						alert('사용할 수 있는 이메일 입니다.');
// 						emoverChk= true;
// 					}else{
// 						alert('이미 사용중인 이메일 입니다.');
//
// 					}
// 				}
// 				,error:function(e){
// 					console.log(e);
// 				}
// 			});
// 		});
//아이디 중복체크

$("#overlay").click(function(){
	var id = $("#id").val();
	// console.log(url);
	$.ajax({
		type:'post'
		// ,url:'myTimeTable/idCheck'
		,url: url
		,data:{'id' : id }
		,dataType:'JSON'
		,success:function(data){
			if(data.checkValue){

				alert('이미 사용중인 아이디 입니다.');


			}else{
				alert('사용할 수 있는 아이디 입니다.');
				overChk= true;
			}
		}

	});
});

$("#id").focusout(function(){
	 if($('#id').val() == ""){

		$('#idch').text('*필수 정보입니다.');
		$('#idch').css('color', 'red');
		return false;
			}else{
		 idc = true;
		 $("#idch").hide();
		 return true;
	 }
 });
$("#email").focusout(function(){
   if($('#email').val() == ""){

	   $('#emch').text('*필수 정보입니다.');
	   $('#emch').css('color', 'red');
		$(this).focus();
		return false;
   }else{
	   emailc = true;
	   return true;
   }
});
var idc = false;
var pwc = false;
// var pwc2 = false;
var emailc = false;
function regist(){
var id = $("#id").val();
var pw = $("#password1").val();
var pw2 = $("#password2").val();
var email = $("#email").val();
var  regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
if(!regExp.test(email)){
    alert('이메일 형식이 아닙니다');
    doc.getElementById('email').value='';
    doc.getElementById('email').focus();
    return ;
}


if(idc == false || id === ""){
  alert('아이디를 확인 해 주세요')
}else if(overChk == false){
  alert('아이디 중복 검사를 해주세요')
}else if(pwc == false|| pw2 === ""){
  alert('비밀번호를 확인 해 주세요')
}
else if(emailc == false || email === ""){
alert('이메일을 입력해주세요')}
// }else if(emoverChk == false){
// alert('이메일을 중복 검사를 해주세요')
// }
else{
  $('form').submit();
}
} ;
