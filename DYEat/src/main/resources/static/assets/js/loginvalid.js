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

    if ($('#EmailInput').val() != '') {

        $.ajax({

            type: 'GET',
            url: '/api/{emailId}/signup',
            data: 'id=' + $('#EmailInput').val(),
            dataType: 'json',
            success: function (result) {
                if (result == false) {
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
