window.onload = function() {
    loadUserName();
    loadUserLottoRoundList();
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


//로또회차 리스트
function loadUserLottoRoundList() {
    $.ajax({
        type:"post",
        url: "/lottoPurchased/userLottoRound",
        data: {"userId" : userId},
        dataType:"json",
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();
            let userLottoRoundList = data.userLottoRoundList;

            if (userLottoRoundList.length == 0) {
                window.location.replace('/main');
            }
            printUserLottoRoundList(userLottoRoundList);
        },
    });

}

function printUserLottoRoundList(userlottoRoundList) {
    $("#userLottoRoundListDiv").empty();
    let htmlCode2 = "</button>";
    let htmlCode1 = "<button class='btn lottoRoundButton' type='button' onclick='loadWinningResult(this)'>";
    userlottoRoundList.forEach(lottoRound => $("#userLottoRoundListDiv").append(htmlCode1 + makeLottoRoundName(lottoRound) + htmlCode2));
}




const ROUND_FORM = "회차";
const LOTTO_DATE_FORM = '(${month}월 ${day}일)'
function makeLottoRoundName(lottoRound, date) {
    let lottoRoundString = "<span class='userLottoRoundNumber'>" + lottoRound + "</span>" + ROUND_FORM;
    return lottoRoundString;
}


//유저가 선택한 회차에 맞는 구매 로또 리스트를 서버에서 가져온다.
function loadPurchasedLottoList(lottoRound) {
    $.ajax({
        type:"post",
        url: "/lottoPurchased/purchasedLottoList",
        data:{"userId" : userId,
            "lottoRound" : lottoRound},
        dataType:"json",
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();
            let purchasedLottoList = data.purchasedLottoList;
            printPurchasedLottoList(purchasedLottoList);
        },
    });
}

//유저가 선택한 회차에 맞는 구매 로또 리스트 출력
function printPurchasedLottoList(purchasedLottoList) {
    const htmlCode1 = "<tr> <th scope='row'>"
    const htmlCode2 = "</th> <td>"
    const htmlCode3 = "</td> </tr>"

    $("#purchasedLottoTable").empty();

    for (var i = 1; i <= purchasedLottoList.length; i++) {
        $("#purchasedLottoTable").append(htmlCode1 + i + htmlCode2 + purchasedLottoList[i - 1] + htmlCode3 );
    }
}

function loadWinningResult(event) {
    let lottoRound = $(event).children('.userLottoRoundNumber').html();
    $.ajax({
        type:"post",
        url: "/lottoPurchased/winningResult",
        data:{"userId" : userId,
            "lottoRound" : lottoRound},
        dataType:"json",
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();
            $('#loadingErrorContent').css('display', 'none');
            $('#purchasedLottoContent').css('display', 'block');

            printWinngingPriceList(data.winningPriceList);
            printTotalPurchaseAmount(data.totalPurchaseAmount);
            printTotalWinningPrice(data.totalWinningPrice);
            printRateOfReturn(data.rateOfReturn);
        },
        error : function (xhr, ajaxSettings, thrownError) {
            $('#errorContent').css('display', 'block');
            $('#purchasedLottoContent').css('display', 'none');
        }
    });
}

const NUMBER_OF_PRIZE = "등 당첨개수: ";
const TOTAL_WINNING_PRICE = "총 당첨금액: ";
const TOTAL_PURCHASE_AMOUNT = "총 구입 금액: ";
const RATE_OF_RETURN = "수익률: ";
const WON = "원";

function printWinngingPriceList(winningPriceList) {
    for (var i = 0; i < winningPriceList.length; i++) {
        let div = $("div");
        div.html(NUMBER_OF_PRIZE + winningPriceList[i][0] + " " + TOTAL_WINNING_PRICE + winningPriceList[i][1] + WON);
        $("#lottoResult").append(div);
    }
}

function printTotalPurchaseAmount(purchaseAmount) {
    let div = $("div");
    div.html(TOTAL_PURCHASE_AMOUNT + purchaseAmount + WON);
    $("#lottoResult").append(div);
}

function printTotalWinningPrice(totalWinningPrice) {
    let div = $("div");
    div.html(TOTAL_WINNING_PRICE + totalWinningPrice + WON);
    $("#lottoResult").append(div);
}

function printRateOfReturn(rateOfReturn) {
    let div = $("div");
    div.html(RATE_OF_RETURN + rateOfReturn + "%");
    $("#lottoResult").append(div);
}