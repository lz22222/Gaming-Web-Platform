<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weplay Admin System</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/adminpage.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/adminpage.css"/>
    <link rel="icon" href="/smile.ico" type="image/x-icon">
</head>

<body>
<div class="top"></div>
<div id="header">
    <div class="logo">Weplay Admin System</div>
</div>
<div id="content">
    <div class="left_menu">
        <ul id="nav_dot">
            <li>
                <h4 class="M1"><span></span>User Management</h4>
                <div class="list-item none">
                    <a href="#" onclick="showright_0()">Information Display</a>
                </div>
            </li>
            <li>
                <h4 class="M2"><span></span>Category Management</h4>
                <div class="list-item none">
                    <a href="#" onclick="showright_3()">Information Display</a>
                </div>
            </li>
            <li>
                <h4 class="M3"><span></span>Game Management</h4>
                <div class="list-item none">
                    <a href="#" onclick="showright_1()">Information Display</a>
                </div>
                <div class="list-item none">
                    <a href="#" onclick="showright_2()">Add Game</a>
                </div>
            </li>

        </ul>
    </div>
    <div class="m-right" id="right_0">
        <div class="main">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>
                                    User ID
                                </th>
                                <th>
                                    Username
                                </th>
                                <%--<th>--%>
                                <%--Password--%>
                                <%--</th>--%>
                                <th>
                                    Nickname
                                </th>
                                <th>
                                    Email
                                </th>
                                <th>
                                    Phone Number
                                </th>
                                <th>
                                    Creation Time
                                </th>
                                <th>
                                    User Status
                                </th>
                                <th>
                                    Actions
                                </th>
                            </tr>
                            </thead>
                            <tbody id="userInfo">

                            </tbody>
                        </table>
                    </div>
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="pagination pagination-right yema divsetcenter">
                                <ul id="paging_0">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="m-right" id="right_1">
        <div class="main">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>
                                    Game ID
                                </th>
                                <th>
                                    Game Name
                                </th>
                                <th>
                                    Price
                                </th>
                                <th>
                                    Status
                                </th>
                                <th>
                                    Actions
                                </th>


                            </tr>
                            </thead>
                            <tbody id="gameInfo">

                            </tbody>
                        </table>
                    </div>
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="pagination pagination-right yema divsetcenter">
                                <ul id="paging_1">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div class="m-right" id="right_2">
        <div class="main setmargin">
            <form method="post" name="game_info" id="game_info" enctype="multipart/form-data">


                <h5>Developer</h5>
                <input type="text " class="form-control" placeholder="Create" id="addgamecreater" name="creater">


                <h5>Game Name</h5>
                <input type="text" class="form-control" placeholder="Name" id="addgamename" name="name">


                <h5>Game Description</h5>
                <textarea class="form-control" placeholder="Desc" id="addgamedesc" name="desc"></textarea>


                <h5>System Configuration</h5>
                <textarea class="form-control" placeholder="Systemcfg" id="addgamesyscfg" name="systemcfg"></textarea>


                <h5>Game Price</h5>
                <input type="text" class="form-control" placeholder="Price" id="addgameprice" name="price">


                <h5>Discounted Game Price</h5>
                <input type="text" class="form-control" placeholder="Discount" id="addgamediscount" name="discount">


                <h5>Upload Game Cover (1 image)</h5>
                <input type="file" id="doc_0" style="width:150px;" accept="image/*" onchange="setImagePreviews_0();"
                       name="header">
                <div id="dd_0"></div>


                <div class=" setmargin_0">
                    <h5>Upload Game Screenshots (at least 5 images)</h5>
                    <input type="file" id="doc" multiple="multiple" style="width:150px;" onchange="setImagePreviews();"
                           accept="image/*" name="pics">
                    <div id="dd"></div>
                </div>
            </form>

            <div class=" setmargin_0">
                <button class="btn" id="addgamebtn" type="submit" onclick="addgame()">Confirm Add</button>
                <button class="btn" type="reset" onclick="cleartext()">Reset Content</button>
            </div>
        </div>
    </div>

    <div class="m-right" id="right_3">
        <div class="main">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>
                                    Category ID
                                </th>
                                <th>
                                    Category Name
                                </th>
                                <th>
                                    Actions
                                </th>
                            </tr>
                            <tr>
                                <th>0</th>
                                <th><input type="text" id="addkindinput"></th>
                                <th>
                                    <button class="btn" onclick="addkind()">Add</button>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="kind_all">

                            </tbody>
                        </table>
                    </div>
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="pagination pagination-right yema divsetcenter">
                                <ul id="paging_3">

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<div class="bottom"></div>
<div id="footer"><p>All rights reserved by <a href="" target="_blank">Weplay</a></p></div>
<script>navList(12);</script>
</body>
</html>
