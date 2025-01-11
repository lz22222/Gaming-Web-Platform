<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/sanji.css" rel="stylesheet" type="text/css">
    <script src="/js/biaoqian.js"></script>
    <script src="/js/sanji.js"></script>
    <title>Game Page</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <div class="gutter_header">
                <ul class="list-group" id="leixing"></ul>
            </div>
        </div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <!--Game Detail Page-->
            <div calss="row">
                <!--Game Name-->
                <div class="row">
                    <div class="col-md-4">
                        <h3 style="color:white" id="gamename"></h3>
                    </div>
                </div>
                <!--Game Video, Images, and Description-->
                <div class="row">
                    <div class="col-md-8">
                        <div class="row">
                            <div id="myCarousel" class="carousel slide">
                                <!-- Carousel Indicators -->
                                <ol class="carousel-indicators" id="carousel_1">

                                </ol>
                                <!-- Carousel Items -->
                                <div class="carousel-inner" id="carousel_2">

                                </div>
                                <!-- Carousel Navigation -->
                            </div>

                        </div>
                        <div class="row addmargin">
                            <div class="col-md-1 btn_1" onClick="move('left')">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </div>
                            <div class="col-md-10 deletelrpadding" id="carousel_3">
                                <ul id="carousel_4"></ul>
                            </div>
                            <div class="col-md-1 btn_1" onClick="move('right')">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-4" id="getheader">

                    </div>
                </div>

                <!--Shopping Cart and Game Supported Languages-->
                <div class="row">
                    <div class="col-md-12" style="margin-top:50px;padding-left:0px;padding-right:9px">
                        <div class="row" style=" background-color:#356F95 ; color:#FFF ; margin-left:0px; margin-right:40px">
                            <div class="col-md-6" style="padding-left:30px" id="shoppingcar">

                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <p class="text-right" style="padding-top:30px;padding-right:30px" id="shopingcartbtn"><a href="#" class="btn btn-info btn-lg" onclick="addshopingcar()"> <span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart </a></p>
                                </div>
                            </div>
                        </div>
                        <div class="row"> &nbsp;</div>

                    </div>

                </div>

                <!--Latest Updates and System Requirements-->
                <div class="row">
                    <div class="col-md-4">
                        <h4 style="color:white">Game Configuration</h4>

                        <table class="table table-hover">
                            <h4 style="color:#4582A5">Minimum Configuration</h4>
                            <tbody id="systemcfg">

                            </tbody>
                        </table>


                    </div>
                    <div class="col-md-4 col-md-offset-2" style=" padding-left:0px; padding-right:0px">
                        <table class="table table-hover">
                            <caption style="color:white">
                                Supported Languages
                            </caption>
                            <thead>
                            <tr>
                                <th></th>
                                <th style="color:white">Interface</th>
                                <th style="color:white">Full Audio</th>
                                <th style="color:white">Subtitles</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td align="center" style="color:#acb2b8">Simplified Chinese</td>
                                <td align="center" style="color:gray"></td>
                                <td align="center" style="color:gray">Not Supported</td>
                                <td align="center" style="color:gray"></td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">English</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">French</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4"></td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">Italian</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4"></td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">German</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4"></td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
