<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/regist.css" rel="stylesheet">
    <script src="/js/findpassword.js" type="text/javascript" charset="utf-8"></script>
    <title>Email Verification</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <div class="gutter_header">
                <ul class="list-group" id="leixing">

                </ul>
            </div>
        </div>
        <div class="col-xs-10 col-sm-10 col-md-10">

            <div class="row zczt"><h1>Retrieve Password</h1></div>
            <div class="row zczt">
                <div class="col-xs-offset-1 col-xs-5" id="regist_2">
                    <div class="row zczt">
                        Enter your email:<br/>
                        <input class="inputcolor" id="email"><input id="btna" type="button" class="btnsz" value="Send Email"
                                                                    style="background-color:#417a9b;"
                                                                    onclick="sendMail()"><br>
                        Enter your verification code:<br/>
                        <input class="inputcolor" type="text" id="code"><br>
                        Enter your new password:<br/>
                        <input class="inputcolor" type="password" id="newpassword"><br>
                        Re-enter your new password:<br/>
                        <input class="inputcolor" type="password" id="newpassword_1"><br>
                    </div>
                    <div class="form-group">
                        <div class=" col-sm-10">
                            <button type="submit" class="btn btn-default" style="background-color:#417a9b;"
                                    onclick="findpassword()">Confirm
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <h3>
                        Why join us?
                    </h3>
                    <ul>
                        <li>
                            Buy and download full retail games
                        </li>
                        <li>
                            Chat with friends while gaming
                        </li>
                        <li>
                            Play on any computer
                        </li>
                        <li>
                            Arrange games, tournaments or LAN parties
                        </li>
                        <li>
                            Get automatic game updates and more!
                        </li>
                    </ul>
                    <img src="/img/static/login_1.png"/>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
