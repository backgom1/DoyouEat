function passConfirm() {

    var password = document.getElementById('pwdInput');
    var passwordConfirm = document.getElementById('rePwdInput');
    var confirmMsg = document.getElementById('confirmMsg');
    var correctColor = "#00ff00";
    var wrongColor = "#ff0000";

    if (password.value == passwordConfirm.value) {
        confirmMsg.style.color = correctColor;
        confirmMsg.innerHTML = "비밀번호 일치합니다.";
    } else {
        confirmMsg.style.color = wrongColor;
        confirmMsg.innerHTML = "비밀번호 불일치합니다.";
    }
}

$('#userCheck').click(function () {

    let data = {
        "EmailInput": $("#EmailInput").val(),
    };

    if ($('#EmailInput').val() != '') {

        $.ajax({
            type: 'POST',
            url: '/signup/api',
            data: JSON.stringify(data),
            contentType: "application/json; charset=uft-8",
            dataType: 'text',
            success: function (result) {
                if (result === "1") {
                    $('#result').text('사용 가능한 아이디입니다.');
                } else {
                    $('#result').text('이미 사용중인 아이디입니다.');
                }
            },
            error: function (a, b, c) {
                console.log(a, b, c);
            }

        });

    } else {
        alert('아이디를 입력하세요.');
        $('#EmailInput').focus();
    }

});

$('#nicknameCheck').click(function () {

    if ($('#nickInput').val() != '') {

        $.ajax({
            type: 'GET',
            url: '/signup/api/{nickname}',
            data: 'id=' + $('#nickInput').val(),
            dataType: 'json',
            success: function (result) {
                if (result == false) {
                    $('#nick_result').text('사용 가능한 닉네임입니다.');
                } else {
                    $('#nick_result').text('이미 사용중인 닉네임입니다.');
                }
            },
            error: function (a, b, c) {
                console.log(a, b, c);
            }

        });

    } else {
        alert('닉네임을 입력하세요.');
        $('#nickInput').focus();
    }

});
