<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <script src="/js/login.js" type="text/javascript" charset="utf-8"></script>
    <title>Login</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="row bjys ztys dlkwz">
            <div class="col-xs-4">
                <h1 class="btys">Login</h1>
                <p>Log in to your existing WePlay account</p> <br>
                <p>Username</p>
                <input type="text" class="form-control" placeholder="Username" id="name">
                <h5>Password</h5>
                <input type="password" class="form-control" placeholder="Password" id="password">
                <input type="checkbox" id="remember">Remember me for one week
                <br/><br/>
                <button type="submit" class="btnys" onclick="login()"><p class="btys">Login</p></button>
                <br/><br/>
                <a href="/user/findpassword">Forgot your password?</a>
            </div>
            <div class="col-xs-4">
                <h1 class="btys">Create</h1>
                <h5>A free Weplay account</h5>
                <h5>Welcome to join and use easily. Continue to create a Weplay account<br/>
                    and get Weplay - a cutting-edge digital solution for PC and Mac players.<br/>
                </h5>
                <a href="/register">
                    <button type="submit" class="btnys"><p class="btys">Join Weplay</p></button>
                </a>
            </div>
            <div class="col-xs-4">
                <h3 class="btys">Why join Weplay?</h3>
                <ul type="disc">
                    <li>Buy and download full retail games</li>
                    <li>Join the Weplay community</li>
                    <li>Chat with friends while gaming</li>
                    <li>Play on any computer</li>
                    <li>Arrange games, tournaments, or LAN parties</li>
                    <li>Get automatic game updates and more!</li>
                    <img class="imgdx" src="/img/login.png"/>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@ include file="common/bottom.jsp" %>
</body>
</html>
