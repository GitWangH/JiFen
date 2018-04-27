(function() {
    $('.bar2').slideToUnlock({
        height: 40,
        width: 320,
        text: '滑动解锁',
        succText: '解锁成功',
        bgColor: '#43455B',
        progressColor: 'rgb(123, 171, 14)',
        succColor: 'rgb(123, 171, 14)',
        textColor: '#fff',
        succTextColor: '#fff',
        successFunc: function() {
            $('#loginBtn').attr('disabled', false);
        }
    });

    $('#loginBtn').attr('disabled', true);

    $('#loginBtn').on('click', function() {
        login();
    });

    $(document).keypress(function(e) {
        if ($('#loginBtn').attr('disabled') == true && e.which == 13) {
            login();
        }
    });

    var login = function() {
        var userName = $('#username').val();
        var passWord = $('#password').val();
        if (!userName || userName == '') {
            $('#loginMesg').html("用户名不能为空");
            return;
        }
        if (!passWord || passWord == '') {
            $('#loginMesg').html("密码不能为空");
            return;
        }
        var user = new Object();
        user.acctName = userName;
        user.acctPwd = window.pwdString.encode(passWord);
        var postData = JSON.stringify(user);
        $.ajax({
            type: 'POST',
            url: "login",
            contentType: "application/json",
            data: postData,
            success: function(res) {
                var timestamp = Date.parse(new Date());
                timestamp = timestamp / 1000
                if (localStorage) {
                    if (localStorage.$loginInfo) {
                        localStorage.$loginInfo = new Object();
                    }
                    localStorage.setItem(timestamp, window.pwdString.encrypt(JSON.stringify(res)));
                    window.location.href = "index.html?#/" + timestamp + "/#";
                } else if (document.cookie) {
                    setCookie(timestamp, window.pwdString.encrypt(JSON.stringify(res)));
                    window.location.href = "index.html?#/" + timestamp + "/#";
                } else {
                    $('#loginMesg').html("不支持当前浏览器登录!");
                }
            },
            error: function(res) {
                $('#loginMesg').html(res.responseJSON.text);
            }
        });
    }


})()