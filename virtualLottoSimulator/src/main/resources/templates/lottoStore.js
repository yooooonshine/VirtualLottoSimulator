function sendPurchaseAmount(){
    let post = {
        title: $(`#title`).val(), // 3가지 가능
        body: $('#body').val(),
        userId: $("#userId").val()
    };

    $.ajax({
        type:"post",
        url: "purchaseAmount",
        data:JSON.stringify(post),  // js오브젝트를 json으로 파싱
        headers:{
            "content-type":"application/json; charset=utf-8"
        },
        dataType:"json",
        beforeSend: function (request) {
            $("#mySpinner").show();

        },
        success: function (data) {
            $("#mySpinner").hide();
            let ticketList = data.ticketList;
            printTicketList();
        },
    });
}

function printTicketList(ticketList) {
    $("tickets").text(ticketList);
}