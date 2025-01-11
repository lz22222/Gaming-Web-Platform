<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/personal.css" rel="stylesheet">
    <script src="/js/updateUserInfo.js"></script>
    <title>Personal Center</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="row divcolor setmargin_2"><h3>>>Edit Personal Information</h3></div>
        <div class="row">
            <!-- Left section for user info -->
            <div class="col-md-7 col-md-offset-1 ziti">
                <div class="row setmargin">Username</div>
                <div class="row setmargin">Nickname<input class="setmargin_1 setpadding" type="text" id="userInfo_0"></div>
                <div class="row setmargin">Email<input class="setmargin_1 setpadding" type="text" id="userInfo_1"></div>
                <div class="row setmargin">Phone<input class="setmargin_1 setpadding" type="text" id="userInfo_2"></div>
                <div class="row setmargin ">
                    <button class="setbtnpadding" onclick="updateUserInfo()">Submit</button>
                </div>
            </div>
            <!-- Right section with options -->
            <div class="col-md-4 setmargin divcolor_1">
                <div class="row ziti">Edit</div>
                <div class="row ziti_1 setpadding setmargin"><a href="/user/personal">My Personal Information</a></div>
                <div class="row ziti_1 setmargin"><a href="/order/orders">My Orders</a></div>
            </div>
        </div>

    </div>
</div>

<%@ include file="common/bottom.jsp" %>
</body>

</html>
