/**
 * Created by Lenovo on 2018/1/25.
 */
//ajax验证数据
//获取input的所有id
var user;
var pwd;
var flag;
function ajaxValidate() {
    user = document.getElementById("userCode");
    pwd = document.getElementById("userPassword");
    flag = false;
    if (!user.value) {
        user.innerHTML = '请输入用户名';
        user.focus();
        flag = false;
    } else {
        flag = true;
    }
    if (!pwd.value) {
        pwd.innerHTML = '请输入密码';
        pwd.focus();
        flag = false;
    } else {
        flag = true;
    }
    if (flag) {
        $.ajax({
            url: "/validateLogin",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                "userCode": user.value,
                "userPassword": pwd.value
            },
            success: function (json) {
                if (json.result) {
                    flag = true;
                    window.sessionStorage.clear();
                } else {
                    alert(json.detail);
                    flag = false;
                }
            }
        });
    }
    return flag;
}

var i = 0;

function setCookie(name, value, hours, path) {
    if (i === 0) {
        var name = escape(name);
        var value = escape(value);
        var expires = new Date();
        expires.setTime(expires.getTime() + hours * 60 * 60 * 1000);
        path = path == "" ? "" : ";path=" + path;
        _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
        document.cookie = name + "=" + value + _expires + path;
        /*		alert("---cookie:"+i+"c" + document.cookie);
         */
    }
    i++;
}
