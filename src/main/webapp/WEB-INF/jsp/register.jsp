<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/regist.css" rel="stylesheet">
    <script src="/js/regist.js" type="text/javascript" charset="utf-8"></script>
    <title>User Registration</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<!--Left navigation bar-->
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2"></div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <div class="row zczt"><h1>Create Account</h1></div>
            <div class="row zczt">
                <div class="col-xs-offset-1 col-xs-5" id="regist_1">
                    <div class="row zczt">
                        Create WePlay Username:<br/>
                        <input class="inputcolor" type="text" id="yonghuming"><br/>
                    </div>
                    <div class="row zczt">
                        Enter Nickname:<br/>
                        <input class="inputcolor" type="text" id="nichen"><br/>
                    </div>
                    <div class="row zczt">
                        Enter Password:<br/>
                        <input class="inputcolor" type="password" id="password"><br/>
                    </div>
                    <div class="row zczt">
                        Enter Email Address:<br/>
                        <input class="inputcolor" type="text" id="email"><br/>
                        WePlay will send a confirmation email to this account. Please verify your WePlay email account using the verification code in the email.
                    </div>
                    <div class="row zczt">
                        Enter Your Phone Number:<br/>
                        <input class="inputcolor" type="text" id="phone"><br/>
                    </div>
                    <div class="form-group" style="float:left;clear:both;">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-default" style="background-color:#417a9b;"
                                    onclick="regist()">Next
                            </button>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <h3>Why Join Us?</h3>
                    <ul>
                        <li>
                            Buy and download full retail games
                        </li>
                        <li>
                            Chat with friends while playing
                        </li>
                        <li>
                            Play on any computer
                        </li>
                        <li>
                            Organize games, tournaments, or LAN parties
                        </li>
                        <li>
                            Get automatic game updates and more!
                        </li>
                    </ul>
                    <img src="/img/login.png"/>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
