<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/shoppingcart.css" rel="stylesheet">
    <script src="/js/shoppingcart.js"></script>
    <title>Shopping Cart</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-1 col-sm-1 col-md-1"></div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <h1 style="color:#FFF;font-family: 'Adobe 黑体 Std R'"><span class="glyphicon glyphicon-shopping-cart"></span>Your Shopping Cart
            </h1>
            <br>
            <div id="grad1"></div>

            <table style="color: #fbfbfb" class="table">
                <thead>
                <tr>
                    <th width="20%">
                        Serial Number
                    </th>
                    <th width="30%">
                        Game Name
                    </th>
                    <th width="25%">
                        Price
                    </th>

                    <th width="15%">
                        Actions
                    </th>
                </tr>
                </thead>
                <tbody style="background-color:#1f486a" id="spcar">

                </tbody>
            </table>

            <h2 style="color:#CFF" align="right">Total Price: ￥<span id="prices"></span></h2>

            <div align="right" style=" border-top-width:10px;padding:20px">
                <button type="button" class="btn btn-default btn-lg active" style="background-color:#090"
                        onclick="orderadd()">Confirm Purchase
                </button>
            </div>
            <br>
            <br>
            <h3 style="color:#fff" align="left">Delivery</h3>
            <div style="background-color:#000;padding:10px 40px 10px 100px;">
                <h4 style="color:#FFF"><a href="#"><img src="/img/static/logo.jpg" width="61" height="50"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    All digital products will be delivered to you via the WEPLAY desktop application.</h4>
            </div>
            <br/>
            <br/>

            <div align="left" style="border:30px">
                <h4><a href="/" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    Continue Shopping</a></h4>
            </div>
        </div>
        <div class="col-xs-1 col-sm-1 col-md-1"></div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
