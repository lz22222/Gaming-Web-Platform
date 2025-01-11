<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link rel="stylesheet" href="/css/order.css"/>
    <title>Select Payment Method</title>
    <style>
        .radio {
            display: inline-block
        }
    </style>
</head>

<body>
<%@ include file="common/top.jsp" %>

<br/>
<br/>
<br/>
<div class="container">
    <div class="row" style="background-color:#3D6C8D">
        <h1 align="center" style="color:#C6D4DF">Checkout</h1>
    </div>
    <br/>
    <br/>

    <div class="row">
        <table class="table">
            <thead>
            <th style="color:#C6D4DF">
                Order ID: ${id}
            </th>
            <th style="color:#C6D4DF">
                Recipient: ${user}
            </th>
            </thead>
        </table>
    </div>

    <div class="row" style="background-color:#3D6C8D">
        <h3 align="center" style="color:#C6D4DF">Please Select a Payment Method</h3>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-xs-3">
            <input type="radio" name="payway" value="zhifubao" class="radio"/><img src="/img/static/zhifubao.png"
                                                                                   height="50" width="100"
                                                                                   align="middle"/>
        </div>
        <div class="col-xs-3">
            <input type="radio" name="payway" value="weixin" class="radio"/><img src="/img/static/weixin.png"
                                                                                 height="50" width="100"
                                                                                 align="middle"/>
        </div>
        <div class="col-xs-3">
            <input type="radio" name="payway" value="zhonghang" class="radio"/><img src="/img/static/yinhang1.png"
                                                                                    height="50" width="100"
                                                                                    align="middle"/>
        </div>
        <div class="col-xs-3">
            <input type="radio" name="payway" value="nonghang" class="radio"/><img src="/img/static/yinhang2.png"
                                                                                   height="50" width="100"
                                                                                   align="middle"/>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-3">
            <input type="radio" name="payway" value="jiaohang" class="radio"/><img src="/img/static/yinhang3.png"
                                                                                   height="50" width="100"
                                                                                   align="middle"/>
        </div>
        <div class="col-xs-3">
            <input type="radio" name="payway" value="jianhang" class="radio"/><img src="/img/static/yinhang4.png"
                                                                                   height="50" width="100"
                                                                                   align="middle"/>
        </div>
        <div class="col-xs-3">
            <input type="radio" name="payway" value="gonghang" class="radio"/><img src="/img/static/yinhang5.png"
                                                                                   height="50" width="100"
                                                                                   align="middle"/>
        </div>
        <div class="col-xs-3">
            <input type="radio" name="payway" value="gonghang" class="radio"/><img src="/img/static/jianhang.png"
                                                                                   height="50" width="100"
                                                                                   align="middle"/>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <div class="row">
        <div class="col-xs-10" style="color:#F00;">
            <h4 align="left">Please ensure that your bank card has online banking payment functionality enabled, otherwise payment may not be possible.</h4>
        </div>
        <div class="col-xs-2">
            <a href="/order/${id}/pay">
                <button type="submit" style="height:40px;width:80px">Proceed to Payment</button>
            </a>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
