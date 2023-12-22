window.onload = function() {
    loadUserName();
    loadRecentLottoRound();
}


//유저 이름
let userId = null;
function loadUserName() {
    if(localStorage.getItem('userId')){
        userId = localStorage.getItem('userId');
        printUserName();
    }
    else {
    	window.location.replace('/');
    }
}

function printUserName() {
    $("#userId").text(userId);
}

function loadRecentLottoRound() {
    $.ajax({
        type:"post",
        url: "/purchaseLotto/recentLottoRound",
        dataType:"json",
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();

            printRecentLottoRound(data.recentLottoRound);
        },
        error : function (xhr, ajaxSettings, thrownError) {
            alert("정보를 가져올 수 없습니다.");
            window.location.replace('/main');
        }
    });
}

function printRecentLottoRound(recentLottoRound) {
    $("#recentLottoRound").text(recentLottoRound);
}

function sendPurchaseAmount() {
    let purchaseAmount = $("#purchaseAmount").val();
    let lottoRound = $("#recentLottoRound").text();

    if (!validatePurchaseAmount(purchaseAmount)) {
        return;
    }

    $.ajax({
        type:"post",
        url: "/purchaseLotto/purchaseAmount",
        data: {"userId" : userId,
            "purchaseAmount" : purchaseAmount,
            "lottoRound" : lottoRound},
        dataType:"json",
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();
            printPurchasedLottoList(data.purchasedLottoList);
        },
        error : function (xhr, ajaxSettings, thrownError) {
            alert("정보를 가져올 수 없습니다.");
            window.location.replace('/main');
        }
    });
}

function validatePurchaseAmount(purchaseAmount) {
    return isNumber(purchaseAmount) &&
        isPositiveNumber(purchaseAmount) &&
        isUnitsOfLottoPrice(purchaseAmount) &&
        isLessThanMaxAmount(purchaseAmount);
}



function isPositiveNumber(purchaseAmount) {
    console.log(purchaseAmount);
    if(purchaseAmount > 0) {
        return true;
    }
    alert("입력은 양수여야 합니다.");
    return false;
}

function isNumber(purchaseAmount) {
    if(isNaN(purchaseAmount)) {
        alert("숫자로 입력해 주세요.");
        return false;
    }
    return true;
}

const UNITS_OF_LOTTO_PRICE = 1000;
function isUnitsOfLottoPrice(purchaseAmount) {
    if (purchaseAmount % UNITS_OF_LOTTO_PRICE == 0) {
        return true;
    }
    alert(UNITS_OF_LOTTO_PRICE + "원 단위로만 구매 가능합니다.");
    return false;
}


const MAX_AMOUNT = 100000000;
function isLessThanMaxAmount(purchaseAmount) {
    if (purchaseAmount <= MAX_AMOUNT) {
        return true;
    }
    alert("금액은 100,000,000원 이하이어야 합니다.");
    return false;
}

function printPurchasedLottoList(purchasedLottoList) {
    const htmlCode1 = "<tr> <th scope='row'>"
    const htmlCode2 = "</th> <td>"
    const htmlCode3 = "</td> </tr>"

    $("#purchasedLottoTable").empty();

    for (var i = 1; i <= purchasedLottoList.length; i++) {
        $("#purchasedLottoTable").append(htmlCode1 + i + htmlCode2 + purchasedLottoList[i - 1] + htmlCode3 );
    }
}
