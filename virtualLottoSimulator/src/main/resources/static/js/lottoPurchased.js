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
                alert("구매한 로또가 없습니다.");
                window.location.replace('/main');
            }
            printUserLottoRoundList(userLottoRoundList);
        },
    });

}

function printUserLottoRoundList(userLottoRoundList) {
    $("#userLottoRoundListDiv").empty();
    let htmlCode2 = "</button>";
    let htmlCode1 = "<button class='btn lottoRoundButton' type='button' onclick='loadPurchasedLottoContent(this)'>";
    userLottoRoundList.forEach(lottoRound => $("#userLottoRoundListDiv").append(htmlCode1 + makeLottoRoundName(lottoRound) + htmlCode2));
}

const ROUND_FORM = "회차";
const LOTTO_DATE_FORM = '(${month}월 ${day}일)'
function makeLottoRoundName(lottoRound, date) {
    let lottoRoundString = "<span class='userLottoRoundNumber'>" + lottoRound + "</span>" + ROUND_FORM;
    return lottoRoundString;
}



function loadPurchasedLottoContent(event) {
    let lottoRound = $(event).children('.userLottoRoundNumber').html();

    //구매한 로또 티켓 가져오기
    let purchasedLottoList = loadPurchasedLottoList(lottoRound);
    if (purchasedLottoList.length != 0) {
        $('#purchasedLottoContent').css('display', 'block');
        printPurchasedLottoList(purchasedLottoList);
    } else {
        $('#purchasedLottoContent').css('display', 'none');
        alert("해당 회차에 대한 구매정보가 없습니다.")
    }

    // 당첨번호가 있는 경우에만 load
    if (hasWinningNumber(lottoRound)) {
        $("#winningResult").css('display', 'block');
        loadWinningResult(lottoRound);
    } else {
        $("#winningResult").css('display', 'none');
        $("#winningResultError").css('display', 'block');
    }
}

//유저가 선택한 회차에 맞는 구매 로또 리스트를 서버에서 가져온다.
function loadPurchasedLottoList(lottoRound) {
    let purchasedLottoList = [];
    $.ajax({
        type:"post",
        url: "/lottoPurchased/purchasedLottoList",
        data:{"userId" : userId,
            "lottoRound" : lottoRound},
        dataType:"json",
        async: false,
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();

            purchasedLottoList = data.purchasedLottoList;
            // console.log("purchasedLottoList: " + purchasedLottoList);
        },
    });
    return purchasedLottoList;
}

//유저가 선택한 회차에 맞는 구매 로또 리스트 출력
function printPurchasedLottoList(purchasedLottoList) {
    console.log(purchasedLottoList);
    const htmlCode1 = "<tr> <th scope='row'>"
    const htmlCode2 = "</th> <td>"
    const htmlCode3 = "</td> </tr>"

    $("#purchasedLottoTable").empty();

    for (let i = 1; i <= purchasedLottoList.length; i++) {
        $("#purchasedLottoTable").append(htmlCode1 + i + htmlCode2 + purchasedLottoList[i - 1] + htmlCode3 );
    }
}

function hasWinningNumber(lottoRound) {
    let result;
    $.ajax({
        type:"post",
        url: "/lottoPurchased/hasWinningNumber",
        data:{
            "lottoRound" : lottoRound},
        dataType:"json",
        async: false,
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();
            result = data.result;
        },
        error : function (xhr, ajaxSettings, thrownError) {
            result = false;
        }
    });
    return result;
}

function loadWinningResult(lottoRound) {
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
            // console.log("안돼>");
            $("#mySpinner").hide();
            // $("#winningResult").empty();
            printWinningAndBonusNumber(data.winningNumber, data.bonusNumber);
            printWinningPrizeList(data.gameWinningPrizeList);
            printUserWinningPriceList(data.gameWinningPrizeList, data.userWinningPrizeResult);
            printTotalPurchaseAmount(data.totalPurchaseAmount);
            printTotalWinningPrice(data.totalWinningPrice);
            printRateOfReturn(data.rateOfReturn);
        },
        error : function (xhr, ajaxSettings, thrownError) {
            console.log("당첨 통계 정보를 가져오는 데 실패했습니다.");
        }
    });
}

const WINNING_NUMBER = "당첨번호: ";
const BONUS_NUMBER = " + ";
function printWinningAndBonusNumber(winningNumber, bonusNumber) {
    $('#lottoResult').append(
        $('<div>').prop({
            innerHTML: WINNING_NUMBER + winningNumber + BONUS_NUMBER + bonusNumber
        })
    );
}

function printWinningPrizeList(gameWinningPrizeList) {
    $("#firstPrice").text(gameWinningPrizeList[1]);
    $("#secondPrice").text(gameWinningPrizeList[2]);
    $("#thirdPrice").text(gameWinningPrizeList[3]);
    $("#forthPrice").text(gameWinningPrizeList[4]);
    $("#fifthPrice").text(gameWinningPrizeList[5]);
}

const NUMBER_OF_PRIZE = "등 당첨개수: ";
const TOTAL_WINNING_PRICE = "총 당첨금액: ";
const TOTAL_PURCHASE_AMOUNT = "총 구입 금액: ";
const RATE_OF_RETURN = "수익률: ";
const WON = "원";

function printUserWinningPriceList(gameWinningPrizeList, userWinningPriceList) {
    for (var i = 1; i < userWinningPriceList.length; i++) {
        $('#lottoResult').append(
            $('<div>').prop({
                innerHTML: i + NUMBER_OF_PRIZE + userWinningPriceList[i][0] + ", " + TOTAL_WINNING_PRICE + userWinningPriceList[i][1]  + WON,
            })
        );
    }
}

function printTotalPurchaseAmount(purchaseAmount) {
    $('#lottoResult').append(
        $('<div>').prop({
            innerHTML: TOTAL_PURCHASE_AMOUNT + purchaseAmount + WON,
        })
    );
}

function printTotalWinningPrice(totalWinningPrice) {
    $('#lottoResult').append(
        $('<div>').prop({
            innerHTML: TOTAL_WINNING_PRICE + totalWinningPrice + WON,
        })
    );
}

function printRateOfReturn(rateOfReturn) {
    $('#lottoResult').append(
        $('<div>').prop({
            innerHTML: RATE_OF_RETURN + rateOfReturn + "%",
        })
    );
}