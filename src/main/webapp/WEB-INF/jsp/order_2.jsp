<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="common/head.jsp" %>
    <link rel="stylesheet" href="/css/order.css"/>
    <title>Payment Result Information</title>
    <script type="text/javascript">
        function show_confirm() {
            var r = confirm("Are you sure you want to make the payment?");
            if (r == true) {
                $.post("/order/${id}/pay", {orderid: ${id}}, function (result) {
                    if (result.success) {
                        window.location = "/order/orders";
                    } else {
                        alert(result.msg);
                    }
                })
            }
            else {
                window.location = "/order/orders";
            }
        }
    </script>
</head>

<body>
<%@ include file="common/top.jsp" %>
<br/>
<br/>
<div class="container">

    <div style=" padding:15px; height:60px;background-color:#7A8D96"><span style="background-color:#84CF30"
                                                                           class="glyphicon glyphicon-ok btn btn-info"></span>&nbsp;&nbsp;
        <h4 style=" font-family:'黑体'; font-weight:bold; display:inline">You have selected the products to purchase, please make the payment as soon as possible! </h4>
    </div>
    <br/>
    <hr size="5" color="#000000" width="100%"></hr>
    <br/>
    <br/>

    <div class="row">
        <div class="col-xs-4">
            <div class="row mar" align="center">
                <input type="button" onclick="show_confirm()"
                       class="btn btn-default btn-lg" style="width:80px; height:40px;" value="Pay"/>
            </div>
        </div>
        <div class="col-xs-2"></div>
        <div class="col-xs-6">
            <table class="tishi" align="center">
                <tr><h3 style="color:#FFF">Shopping on WePlay</h3></tr>
                <tr><h4 style="color:#FFF">When you submit your payment information, your data will be protected by a digitally certified SSL technology layer. Once you complete this transaction, we will send you an email to confirm the purchase receipt.</h4></tr>
                <tr><h3 style="color:#FFF">Customer Notice</h3></tr>
                <tr><h4 style="color:#FFF">This process may take up to 60 seconds. To avoid failure, please do not click the back button or close this window until the transaction is completed.</h4></tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
