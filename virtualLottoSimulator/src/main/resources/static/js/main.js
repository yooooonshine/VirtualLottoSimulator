window.onload = function() {
    loadUserName();
}


//유저 이름
let userId = null;
function loadUserName() {
    if(localStorage.getItem('userId')){
        userId = localStorage.getItem('userId');
        printUserName();
    } else {
        window.location.replace('/');
    }
}

function printUserName() {
    $("#userId").text(userId);
}