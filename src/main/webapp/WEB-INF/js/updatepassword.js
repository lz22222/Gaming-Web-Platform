function updatepassword() {
    var password = document.getElementById("newpassword").value;
    var password_1 = document.getElementById("newpassword_1").value;
    if (password != password_1) {
        alert("The passwords you entered do not match!");
    }
    else {
        $.post("/user/updatepassword", {password: password}, function (result) {
            if (result.success) {
                window.location.href = "/user/personal";
            }
            else {
                alert(result.msg);
            }
        })
    }
}
