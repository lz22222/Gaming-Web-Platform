<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/regist.css" rel="stylesheet">
    <script src="/js/regist.js" type="text/javascript" charset="utf-8"></script>
    <title>User Verification</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2"></div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <div class="row zczt">
                <h1>Create Account</h1>
            </div>
            <div class="row zczt">
                <div class="col-xs-offset-1 col-xs-5" id="regist_2">
                    <div class="row zczt">
                        Enter your verification code: <br/>
                        <input class="inputcolor" type="text" id="code">
                        <input id="send" type="button" class="btnsz" value="Resend Verification Code"
                               style="background-color:#417a9b;" onclick="sendMail()">
                    </div>
                    <div class="form-group">
                        <div class=" col-sm-10">
                            <button type="submit" class="btn btn-default" style="background-color:#417a9b;"
                                    onclick="validate()">Submit
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <h3>
                        Why Join Us?
                    </h3>
                    <ul>
                        <li>
                            Purchase and download complete retail games
                        </li>
                        <li>
                            Chat with friends while playing games
                        </li>
                        <li>
                            Play on any computer
                        </li>
                        <li>
                            Arrange games, competitions, or LAN parties
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
<script src="/js/regist.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
