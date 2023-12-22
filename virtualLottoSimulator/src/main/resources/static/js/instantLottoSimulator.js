window.onload = function () {
    loadUserName();
}


//유저 이름
let userId = null;

function loadUserName() {
    if (localStorage.getItem('userId')) {
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


function sendPurchaseAmount() {
    let purchaseAmount = $("#purchaseAmount").val();

    if (!validatePurchaseAmount(purchaseAmount)) {
        return;
    }

    $.ajax({
        type: "post",
        url: "/instantLottoSimulator/purchaseAmount",
        data: {
            "userId": userId,
            "purchaseAmount": purchaseAmount,
        },
        dataType: "json",
        beforeSend: function (request) {
            $("#mySpinner").show();
        },
        success: function (data) {
            $("#mySpinner").hide();
            $("#winningResult").css('display', 'block');
            $("#lottoResult").empty();

            printPurchasedLottoList(data.lottoList);
            printWinningAndBonusNumber(data.winningNumber, data.bonusNumber);
            printWinningPrizeList(data.gameWinningPrizeList);
            printUserWinningPriceList(data.gameWinningPrizeList, data.userWinningPrizeResult);
            printTotalPurchaseAmount(data.totalPurchaseAmount);
            printTotalWinningPrice(data.totalWinningPrice);
            printRateOfReturn(data.rateOfReturn);
        },
        error: function (xhr, ajaxSettings, thrownError) {
            $("#winningResult").css('display', 'none');
            $("#winningResultError").css('display', 'block');

            console.log("당첨 통계 정보를 가져오는 데 실패했습니다.");
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
    if (purchaseAmount > 0) {
        return true;
    }
    alert("입력은 양수여야 합니다.");
    return false;
}

function isNumber(purchaseAmount) {
    if (isNaN(purchaseAmount)) {
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
        $("#purchasedLottoTable").append(htmlCode1 + i + htmlCode2 + purchasedLottoList[i - 1] + htmlCode3);
    }
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
                innerHTML: i + NUMBER_OF_PRIZE + userWinningPriceList[i][0] + ", " + TOTAL_WINNING_PRICE + userWinningPriceList[i][1] + WON,
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
