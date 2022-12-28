$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text === '수정') {

            $.get(href, function (comments, status) {
                $('.myForm #Commentcontent').val(comments.text);
                $('.myForm #comment-id').val(comments.id).hide();

            });
        }
        $('.myForm #exampleModal').modal();

    });
});


$("#com-btn-save").click(function () {
    let data = {
        "boardId": $("#boardId").val(),
        "contentvalue": $("#com_content").val()
    };

    if($("#com_content").val() == ""){
        alert("댓글을 입력하세요!");
        $("#com_content").focus();
        return false;
    }

    $.ajax({
        type: "POST",
        url: '/order/review/save',
        data: JSON.stringify(data),
        contentType: "application/json; charset=uft-8",
        dataType: "text",

        success: function(parameter) {
            location.reload();
        },
        error: function (e) {
            alert("error");
        }
    })
});