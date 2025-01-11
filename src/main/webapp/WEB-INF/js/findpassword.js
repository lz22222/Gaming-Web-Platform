var wait;
$(function () {
    wait = 0;
    time();
})

function schedule() {
    setTimeout(function () {
        time()
    }, 1000)
}

function time() {
    if (wait == 0) {
        document.getElementById("btna").removeAttribute("disabled");
        document.getElementById("btna").value = "send email";
        wait = 60;
    } else {
        document.getElementById("btna").setAttribute("disabled", true);
        document.getElementById("btna").value = wait + "s resend ";
        wait--;
        schedule();
    }
}

function sendMail() {
    var email = document.getElementById("email").value;
    $.post("/user/sendfetchpwdmail", {email: email}, function (result) {
        if (result.success) {
            alert("success！");
            time()
        } else {
            alert(result.msg);
        }
    });
}

function findpassword() {
    var code = document.getElementById("code").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("newpassword").value;
    var password_1 = document.getElementById("newpassword_1").value;
    if (password != password_1) {
        alert("The passwords you entered do not match！");
    }
    else {
        $.post("/user/findpassword", {password: password, email: email, code: code}, function (result) {
            if (result.success) {
                alert("Successfully find! Your username is:" + result.data);
                window.location.href = "/login";
            }
            else {
                alert(result.msg);
            }
        })
    }
}
