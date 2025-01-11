<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/personal.css" rel="stylesheet">
    <script src="/js/personal.js"></script>
    <title>Personal Center</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="row divcolor" id="uname">
            <h3>${user} >> Profile</h3>
        </div>
        <div class="row">
            <!--Left profile column-->
            <div class="col-md-7 col-md-offset-1 ziti">
                <div class="row setmargin" id="userinfo_0">Username</div>
                <div class="row setmargin" id="userinfo_1">Nickname</div>
                <div class="row setmargin" id="userinfo_2">Email</div>
                <div class="row setmargin" id="userinfo_3">Phone</div>
                <div class="row setmargin ">
                    <a href="/user/update">
                        <button class="setbtnpadding">Edit Profile</button>
                    </a>
                </div>
            </div>
            <!--Right options column-->
            <div class="col-md-4 setmargin divcolor_1">
                <div class="row ziti">Edit</div>
                <div class="row ziti_1 setpadding setmargin">
                    <a href="/user/personal">My Profile</a>
                </div>
                <div class="row ziti_1 setmargin">
                    <a href="/order/orders">My Orders</a>
                </div>
                <div class="row ziti_1 setmargin">
                    <a href="/user/updatepassword">Change Password</a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="row divcolor setmargin_2">
                <h3>My Games</h3>
            </div>
            <div class="row">
                <div class="deletepadding">
                    <ul id="fenleixiangqing">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<%@ include file="common/bottom.jsp" %>

</body>
</html>
