$("#id").focusout(function(){

 if($('#id').val() == ""){
    $('#check').text('아이디를 입력해주세요.');
    $('#check').css('color', '#6A24FE');

 }else{
     $('#check').hide();
 }
 });

$("#pass").focusout(function(){
 if($('#pass').val() == ""){
    $('#check').text('비밀번호를 입력해주세요');
    $('#check').css('color', '#6A24FE');
 }else{
     $('#check').hide();
 }
 });
