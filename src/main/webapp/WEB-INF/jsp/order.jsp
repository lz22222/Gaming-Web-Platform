<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/shoppingcart.css" rel="stylesheet">
    <link href="/css/order.css" rel="stylesheet">
    <script src="/js/order.js"></script>
    <title>My Orders</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<!-- Left Sidebar Navigation -->
<div class="container">
    <div class="row">
        <div class="col-xs-8 col-sm-8 col-md-12">
            <h2 style="color:#FFF"><span class="glyphicon glyphicon-shopping-cart"></span>Your Orders</h2>
            <div class="row zx">
                <div id="mainContents">
                    <ul style="font-family: 'Adobe 黑体 Std R';font-weight: bold;font-size: large" id="myTab"
                        class="nav nav-tabs">
                        <li role="presentation"><a href="#already" data-toggle="tab">Paid</a></li>
                        <li role="presentation"><a href="#yet" data-toggle="tab">Unpaid</a></li>
                        <li role="presentation"><a href="#cancel" data-toggle="tab">Cancelled</a></li>
                    </ul>
                    <br>
                    <div id="myTabContent" class="tab-content">
                        <div id="orderTab" class="tab-content">
                            <div class="tab-pane fade in active" id="already"></div>
                            <div class="tab-pane fade" id="yet"></div>
                            <div class="tab-pane fade" id="cancel"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <br>
    </div>
    <br>
    <h3 style="color:#fff" align="left">Delivery</h3>
    <div style="background-color:#000;padding:10px 40px 10px 100px;">
        <h4 style="color:#FFF"><a href="#"><img src="/img/static/logo.jpg" width="61" height="50"></a>&nbsp;&nbsp;&nbsp;&nbsp;
            All digital goods will be delivered to you via the WEPLAY desktop application</h4>
    </div>
    <br/>
    <br/>
    <div align="left" style="border:30px">
        <h4><a href="/" class="btn btn-info btn-lg">
            <span class="glyphicon glyphicon-shopping-cart"></span>
            Continue Shopping</a></h4>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
