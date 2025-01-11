// JavaScript Document
$(
    function () {
        var userInfo = document.getElementById("userInfo");
        $.post("/admin/getuser", function (result) {
            for (i = 0; i < 10; i++) {
                if (result.data.user[i]) {
                    var tr = document.createElement("tr");
                    var j = i + 1;
                    var uid = result.data.user[i].id;
                    var stat = result.data.user[i].stat;
                    var time = new Date(result.data.user[i].ctime).toLocaleString();
                    if (stat == '0') {
                        stat = "Inactive";
                    }
                    if (stat == '1') {
                        stat = "Normal";
                    }
                    if (stat == '2') {
                        stat = 'Restricted Login';
                    }
                    if (stat == '3') {
                        stat = 'delete';
                    }
                    tr.innerHTML = "<td>" + j + "</td><td>"
                        + result.data.user[i].username + "</td><td>"
                        + result.data.user[i].nickname + "</td><td>"
                        + result.data.user[i].email + "</td><td>"
                        + result.data.user[i].phone + "</td><td>"
                        + time + "</td><td id='ustat_" + j + "'>"
                        + stat + "</td><td><button class='btn' onclick='restrict(" + uid + "," + j + ")'>"
                        + "Restricted Login" + "</button><button class='btn' onclick='del(" + uid + "," + j + ")'>"
                        + "Delete" + "</button><button class='btn' onclick='relieve(" + uid + "," + j + ")'>"
                        + "Recover" + "</button></td>";
                    userInfo.appendChild(tr);
                }
            }
            var ul = document.getElementById("paging_0");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var li_first = document.createElement("li");
            li_first.innerHTML = "<a href='#'>&laquo;</a>";
            ul.appendChild(li_first);
            for (i = 0; i < pages; i++) {
                var li = document.createElement("li");
                var j = i + 1;
                li.innerHTML = "<a href='#'>" + j + "</a>";
                ul.appendChild(li);
            }
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'>&raquo;</a>";
            ul.appendChild(li_last);
        })

        var gameInfo = document.getElementById("gameInfo");
        $.post("/admin/getgames", function (result) {
            for (i = 0; i < 10; i++) {
                if (result.data.game[i]) {
                    var tr = document.createElement("tr");
                    var j = i + 1;
                    var gid = result.data.game[i].id;
                    var stat = result.data.game[i].stat;
                    if (stat == "1") {
                        stat = "Listed";
                    }
                    if (stat == "2") {
                        stat = "Unlisted";
                    }
                    if (stat == "0") {
                        stat = "not listed";
                    }
                    tr.innerHTML = "<td>" + j + "</td><td id='gname_" + gid + "'>"
                        + result.data.game[i].name + "</td><td id='gprice_" + gid + "'>"
                        + result.data.game[i].price + "</td><td id='gstat_" + j + "'>"
                        + stat + "</td><td><button class='btn' onclick='upgame(" + gid + "," + j + ")'>"
                        + "Listed" + "</button><button class='btn' onclick='downgame(" + gid + "," + j + ")'>"
                        + "Unlisted" + "</button><button type='button' class='btn' data-toggle='modal' data-target='#myModal_1' onclick='getgamekind(" + gid + ")'>"
                        + "change type" + "</button>" + text_1 +
                        "<p>Number" + "<input type='text' readOnly='true' class='inputmargin_2'id='gamekindid" + "'></p><br>" +
                        "<p><div id='gamekind'>type" + "</div></p><br>" +
                        "</div>" +
                        "<div class='modal-footer' id='savebtn'>" +
                        "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancel</button>" +
                        "<button type='button' class='btn btn-primary' onclick='savekinds(" + ")'>Save</button>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo(" + gid + ")'>"
                        + "modify information" +
                        "</button>" + text +
                        "<p>Number" + "<input type='text' readOnly='true'class='inputmargin_2'id='gameid" + "'></p><br>" +
                        "<p>Game" + "<input type='text' class='inputmargin_2'id='gamename" + "'></p><br>" +
                        "<p>Price" + "<input type='text'class='inputmargin_2' id='gameprice" + "'></p><br>" +
                        "<p>Developer" + "<input type='text'class='inputmargin_1' id='gamecreater" + "'></p><br>" +
                        "<p>Description" + "<textarea  rows='5'  class='inputmargin_2' id='gamedesc" + "'></textarea></p><br>" +
                        "<p>Configuration" + "<textarea  rows='5'  class='inputmargin_2' id='sys" + "'></textarea></p><br>" +
                        "</div>" +
                        "<div class='modal-footer' id='savebtn'>" +
                        "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancel</button>" +
                        "<button type='button' class='btn btn-primary' onclick='savegameupdate(" + ")'>Save</button>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" + "</td>";
                    gameInfo.appendChild(tr);
                }
            }
            var ul = document.getElementById("paging_1");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            if (last < 1) {
                last = 1
            }
            if (next > pages) {
                next = pages
            }
            var li_first = document.createElement("li");
            li_first.id = "li_first";
            li_first.innerHTML = "<a href='#'onclick='getgeame(" + last + ")'>&laquo;</a>";
            ul.appendChild(li_first);
            document.getElementById("li_first").className = "disabled";
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li_" + j;
                li.innerHTML = "<a href='#'onclick='getgeame(" + j + ")'>" + j + "</a>";
                ul.appendChild(li);
            }
            document.getElementById("li_" + current).className = "active";
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'onclick='getgeame(" + next + ")'>&raquo;</a>";
            ul.appendChild(li_last);
        })

        var kind_all = document.getElementById("kind_all");
        $.post("/kind/all", function (result) {
            var i = 0;
            while (result.data[i]) {
                j = i + 1;
                var tr = document.createElement("tr");
                var kid = result.data[i].id;
                tr.innerHTML = "<th id='kindid_" + j + "'>"
                    + result.data[i].id + "</th><th id='kindname_" + j + "'>"
                    + result.data[i].name + "</th><th><button type='button' class='btn' data-toggle='modal' data-target='#myModal_2' onclick='managekind(" + kid + ")'>"
                    + "Manange" + "</button>" + text_2 +
                    "<p>Type ID" + "<input type='text' readOnly='true' class='inputmargin_2'id='kindgameid" + "'></p><br>" +
                    "<p><div id='kindgame'>Game" + "</div></p><br>" +
                    "</div>" +
                    "<div class='modal-footer' id='savebtn'>" +
                    "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancel</button>" +
                    "<button type='button' class='btn btn-primary' onclick='savekindsgames(" + ")'>Save</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</th>";
                i++;
                kind_all.appendChild(tr);
            }
        })
    }
)
function getgeame(pagenum) {
    var gameInfo = document.getElementById("gameInfo");
    gameInfo.innerHTML = "";
    $.post("/admin/getgames", {page: pagenum}, function (result) {
        for (i = 0; i < 10; i++) {
            if (result.data.game[i]) {
                var tr = document.createElement("tr");
                var j = 10 * (pagenum - 1) + i + 1;
                var gid = result.data.game[i].id;
                var stat = result.data.game[i].stat;
                if (stat == "1") {
                    stat = "Listed";
                }
                if (stat == "2") {
                    stat = "Unlisted";
                }
                if (stat == "0") {
                    stat = "Not listed";
                }
                tr.innerHTML = "<td>" + j + "</td><td id='gname_" + gid + "'>"
                    + result.data.game[i].name + "</td><td id='gprice_" + gid + "'>"
                    + result.data.game[i].price + "</td><td id='gstat_" + j + "'>"
                    + stat + "</td><td><button class='btn' onclick='upgame(" + gid + "," + j + ")'>"
                    + "Listed" + "</button><button class='btn' onclick='downgame(" + gid + "," + j + ")'>"
                    + "Unlisted" + "</button><button type='button' class='btn' data-toggle='modal' data-target='#myModal_1' onclick='getgamekind(" + gid + ")'>"
                    + "change type" + "</button>" + text_1 +
                    "<p>Number" + "<input type='text' readOnly='true' class='inputmargin_2'id='gamekindid" + "'></p><br>" +
                    "<p><div id='gamekind'>type" + "</div></p><br>" +
                    "</div>" +
                    "<div class='modal-footer' id='savebtn'>" +
                    "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancel</button>" +
                    "<button type='button' class='btn btn-primary' onclick='savekinds(" + ")'>Save</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo(" + gid + ")'>"
                    + "modify information" +
                    "</button>" + text +
                    "<p>Number" + "<input type='text' readOnly='true' class='inputmargin_2'id='gameid" + "'></p><br>" +
                    "<p>Game" + "<input type='text' class='inputmargin_2'id='gamename" + "'></p><br>" +
                    "<p>Price" + "<input type='text'class='inputmargin_2' id='gameprice" + "'></p><br>" +
                    "<p>Developer" + "<input type='text'class='inputmargin_1' id='gamecreater" + "'></p><br>" +
                    "<p>Description" + "<textarea  rows='5'  class='inputmargin_2' id='gamedesc" + "'></textarea></p><br>" +
                    "<p>Configuration" + "<textarea  rows='5'  class='inputmargin_2' id='sys" + "'></textarea></p><br>" +
                    "</div>" +
                    "<div class='modal-footer' id='savebtn'>" +
                    "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancel</button>" +
                    "<button type='button' class='btn btn-primary' onclick='savegameupdate(" + ")'>Save</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" + "</td>";
                gameInfo.appendChild(tr);
            }
        }
        var ul = document.getElementById("paging_1");
        ul.className = "pagination";
        ul.innerHTML = "";
        var pages = result.data.page.pages;
        var current = result.data.page.current;
        var last = current - 1;
        var next = current + 1;
        if (last < 1) {
            last = 1
        }
        if (next > pages) {
            next = pages
        }
        var li_first = document.createElement("li");
        li_first.id = "li_first";
        li_first.innerHTML = "<a href='#'onclick='getgeame(" + last + ")'>&laquo;</a>";
        ul.appendChild(li_first);
        for (i = 0; i < pages; i++) {
            var j = i + 1;
            var li = document.createElement("li");
            li.id = "li_" + j;
            li.innerHTML = "<a href='#'onclick='getgeame(" + j + ")'>" + j + "</a>";
            ul.appendChild(li);
        }
        document.getElementById("li_" + pagenum).className = "active";
        if (current <= "1") {
            document.getElementById("li_first").className = "disabled";
        }
        var li_last = document.createElement("li");
        li_last.id = "li_next";
        li_last.innerHTML = "<a href='#'onclick='getgeame(" + next + ")'>&raquo;</a>";
        ul.appendChild(li_last);
        if (current >= i) {
            document.getElementById("li_next").className = "disabled";
        }

    })
}


function restrict(uid, j) {
    $.post("/admin/restrictuser", {uid: uid}, function (result) {
        if (result.success) {
            alert("This user has been restricted from logging in\n！");
            document.getElementById("ustat_" + j).innerHTML = "Restricted Login";
        }
        else {
            alert(result.msg);
        }
    })
}

function relieve(uid, j) {
    $.post("/admin/relieveuser", {uid: uid}, function (result) {
        if (result.success) {
            alert("This user's login has been restored！");
            document.getElementById("ustat_" + j).innerHTML = "Normal";
        }
        else {
            alert(result.msg);
        }
    })
}

function del(uid, j) {
    $.post("/admin/deluser", {uid: uid}, function (result) {
        if (result.success) {
            alert("This user has been deleted！");
            document.getElementById("ustat_" + j).innerHTML = "Cancel";
        }
        else {
            alert(result.msg);
        }
    })
}

function upgame(gid, j) {
    $.post("/admin/upgame", {game: gid}, function (result) {
        if (result.success) {
            alert("Game is listed！")
            document.getElementById("gstat_" + j).innerHTML = "Listed";
        }
        else {
            alert(result.msg);
        }
    })
}

function downgame(gid, j) {
    $.post("/admin/downgame", {game: gid}, function (result) {
        if (result.success) {
            alert("Game is unlisted！")
            document.getElementById("gstat_" + j).innerHTML = "Unlisted";
        }
        else {
            alert(result.msg);
        }
    })
}

function getgamekind(gid) {
    document.getElementById("gamekindid").value = gid;
    var kinds = new Array();
    $.post("/kind/all", function (result) {
        var i = 0;
        document.getElementById("gamekind").innerHTML = "categories";
        while (result.data[i]) {
            document.getElementById("gamekind").innerHTML += "<br>" + "<input type='checkbox' id='kindbox_" + gid + "_" + i + "'>" + result.data[i].name;
            kinds[i] = result.data[i].name;
            i++;
        }
    })
    $.post("/admin/getgamekind", {game: gid}, function (result) {
        var i = 0;
        var j = 0;
        while (result.data[i]) {
            while (kinds[j]) {
                if (result.data[i].name == kinds[j]) {
                    document.getElementById("kindbox_" + gid + "_" + j).checked = "true";
                }
                j++;
            }
            i++;
            j = 0;
        }
        i = 0;
    })
}

function savekinds() {
    var i = 0;
    var j = 0;
    var kid = document.getElementById("gamekindid").value;
    var check = document.getElementById("kindbox_" + kid + "_" + i);
    var kinds = new Array();
    while (check) {
        if (check.checked) {
            kinds[j] = parseInt(i + 1);
            j++;
        }
        i++;
        check = document.getElementById("kindbox_" + kid + "_" + i);
    }
    $.ajax({
        type: "post",
        url: "/admin/updategamekind",
        traditional: true,
        data: {game: kid, kinds: kinds},
        success: function (result) {
            if (result.success) {
                alert("success modify！");
            } else
                alert(result.msg);
        }
    })
    $('#myModal_1').modal('hide');
}


function managekind(kid) {
    // var kidpages=2;
    // var currentpage = 1;
    document.getElementById("kindgameid").value = kid;
    var i = 0;
    $.post("/admin/getallgames", function (result) {
        document.getElementById("kindgame").innerHTML = "game";
        while (result.data[i]) {
            var j = result.data[i].id;
            document.getElementById("kindgame").innerHTML += "<br>" + "<input type='checkbox' class='gamebox' id='kind_" + kid + "_" + j + "'>" + result.data[i].name;
            i++;
        }
        var j = 0;
        i = 0;
        while (result.data[i]) {
            if (result.data[i].kinds) {
                while (result.data[i].kinds[j]) {
                    if (result.data[i].kinds[j].id == kid) {
                        var gid = result.data[i].id;
                        document.getElementById("kind_" + kid + "_" + gid).checked = true;
                    }
                    j++;
                }
            }
            i++;
            j = 0;
        }
    })
}


function savekindsgames() {
    var kid = document.getElementById("kindgameid").value;
    var i = 0;
    var games = new Array();
    var num = 0;
    var gamebox = document.getElementsByClassName("gamebox");
    while (gamebox[i]) {
        if (gamebox[i].checked) {
            games[num] = (gamebox[i].id).split('_')[2];
            num++;
        }
        i++;
    }
    i = 0;
    $.ajax({
        type: "post",
        url: "/admin/managerkind",
        traditional: true,
        data: {kind: kid, games: games},
        success: function (result) {
            if (result.success) {
                alert("success modify！");
            } else
                alert(result.msg);
        }
    })
    $('#myModal_2').modal('hide');
}
function showright_0() {
    document.getElementById("right_0").style.display = "block";
    document.getElementById("right_1").style.display = "none";
    document.getElementById("right_2").style.display = "none";
    document.getElementById("right_3").style.display = "none";
}
function showright_1() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "block";
    document.getElementById("right_2").style.display = "none";
    document.getElementById("right_3").style.display = "none";
}
function showright_2() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "none";
    document.getElementById("right_2").style.display = "block";
    document.getElementById("right_3").style.display = "none";
}
function showright_3() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "none";
    document.getElementById("right_2").style.display = "none";
    document.getElementById("right_3").style.display = "block";
}


function navList(id) {
    var $obj = $("#nav_dot"), $item = $("#J_nav_" + id);
    $item.addClass("on").parent().removeClass("none").parent().addClass("selected");
    $obj.find("h4").hover(function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    });
    $obj.find("p").hover(function () {
        if ($(this).hasClass("on")) {
            return;
        }
        $(this).addClass("hover");
    }, function () {
        if ($(this).hasClass("on")) {
            return;
        }
        $(this).removeClass("hover");
    });
    $obj.find("h4").click(function () {
        var $div = $(this).siblings(".list-item");
        if ($(this).parent().hasClass("selected")) {
            $div.slideUp(600);
            $(this).parent().removeClass("selected");
        }
        if ($div.is(":hidden")) {
            $("#nav_dot li").find(".list-item").slideUp(600);
            $("#nav_dot li").removeClass("selected");
            $(this).parent().addClass("selected");
            $div.slideDown(600);

        } else {
            $div.slideUp(600);
        }
    });
}


window.onload = function () {
    oTable = document.getElementById("tab");
    aTr = document.getElementsByTagName("tr");
    for (i = 0; i < aTr.length; i++) {
        if (i % 2 == 0) {
            aTr[i].style.background = "#fff";
        } else {
            aTr[i].style.background = "#ccc";
        }
        ;
    }
    ;
};
var text = "<div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
    "<div class='modal-dialog' role='document'>" +
    "<div class='modal-content'>" +
    "<div class='modal-header'>" +
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
    "<h4 class='modal-title' id='myModalLabel'>Game Info</h4>" +
    "</div>" +
    "<div class='modal-body'>"

var text_1 = "<div class='modal fade' id='myModal_1' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
    "<div class='modal-dialog' role='document'>" +
    "<div class='modal-content'>" +
    "<div class='modal-header'>" +
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
    "<h4 class='modal-title' id='myModalLabel'>Game Type</h4>" +
    "</div>" +
    "<div class='modal-body'>"

var text_2 = "<div class='modal fade' id='myModal_2' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
    "<div class='modal-dialog' role='document'>" +
    "<div class='modal-content'>" +
    "<div class='modal-header'>" +
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
    "<h4 class='modal-title' id='myModalLabel'>Type Management</h4>" +
    "</div>"
    "<div class='modal-body'>"

function getgameinfo(gid) {
    $.post("/game/" + gid, function (result) {
        document.getElementById("gameid").value = result.data.id;
        document.getElementById("gamename").value = result.data.name;
        document.getElementById("gameprice").value = result.data.price;
        document.getElementById("gamecreater").value = result.data.creater;
        document.getElementById("gamedesc").value = result.data.desc;
        document.getElementById("sys").value = result.data.systemcfg;
    })
}

function savegameupdate() {
    var gid = document.getElementById("gameid").value;
    var gamename = document.getElementById("gamename").value;
    var gameprice = document.getElementById("gameprice").value;
    var gamedesc = document.getElementById("gamedesc").value;
    var gamecreater = document.getElementById("gamecreater").value;
    var sys = document.getElementById("sys").value;
    $.post("/admin/updategameinfo", {
        id: gid,
        creater: gamecreater,
        name: gamename,
        desc: gamedesc,
        systemcfg: sys,
        price: gameprice
    }, function (result) {
        if (result.success) {
            alert("success modify！");
        }
        else {
            alert(result.msg);
        }
    })
    document.getElementById("gname_" + gid).innerHTML = gamename;
    document.getElementById("gprice_" + gid).innerHTML = gameprice;
    $('#myModal').modal('hide');
}

function setImagePreviews(avalue) {
    var docObj = document.getElementById("doc");
    var dd = document.getElementById("dd");
    dd.innerHTML = "";
    var fileList = docObj.files;
    for (var i = 0; i < fileList.length; i++) {
        dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
        var imgObjPreview = document.getElementById("img" + i);

        if (docObj.files && docObj.files[i]) {
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '200px';
            imgObjPreview.style.height = '180px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
        }
        else {
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            //alert(imgSrc)
            var localImagId = document.getElementById("img" + i);
            localImagId.style.width = "200px";
            localImagId.style.height = "180px";
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("The image you uploaded is not in the correct format, please select again\n");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }
    return true;
}
function setImagePreviews_0(avalue) {
    var docObj = document.getElementById("doc_0");
    var dd = document.getElementById("dd_0");
    dd.innerHTML = "";
    var fileList = docObj.files;
    for (var i = 0; i < fileList.length; i++) {
        dd.innerHTML += "<div style='float:left' > <img id='img_" + i + "'  /> </div>";
        var imgObjPreview = document.getElementById("img_" + i);

        if (docObj.files && docObj.files[i]) {
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '200px';
            imgObjPreview.style.height = '180px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
        }
        else {
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            //alert(imgSrc)
            var localImagId = document.getElementById("img_" + i);
            localImagId.style.width = "200px";
            localImagId.style.height = "180px";
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("The image you uploaded is not in the correct format, please select again\n!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }
    return true;
}
var xhr = new XMLHttpRequest();
function addgame() {
    var form = new FormData();
    var creater = document.getElementById("addgamecreater").value;
    var name = document.getElementById("addgamename").value;
    var desc = document.getElementById("addgamedesc").value;
    var syscfg = document.getElementById("addgamesyscfg").value;
    var price = document.getElementById("addgameprice").value;
    var discount = document.getElementById("addgamediscount").value;
    var cover = document.getElementById("doc_0").files[0];
    var i = 0;
    while (document.getElementById("doc").files[i]) {
        form.append("pics", document.getElementById("doc").files[i]);
        i++;
    }

    form.append("creater", creater);
    form.append("name", name);
    form.append("desc", desc);
    form.append("systemcfg", syscfg);
    form.append("price", price);
    form.append("discount", discount);
    form.append("kinds", '1');
    form.append("header", cover);
    xhr.open("POST", "/admin/addgame", true);
    xhr.onreadystatechange = call;
    xhr.send(form);
}
function call() {
    //alert(xmlh.status);
    if (xhr.readyState == 4) {
        if (xhr.status == 200) { //success
            var result = xhr.responseText;
            var s = result.substring(result.indexOf(':') + 1, result.indexOf(','));
            if (s == "true") {
                alert("Add success！")
            }
            else {
                var s1 = result.substring(result.indexOf(':"') + 2, result.indexOf('",'));
                alert(s1);
            }
        }
    }
}

function cleartext() {
    var creater = document.getElementById("addgamecreater");
    var name = document.getElementById("addgamename");
    var desc = document.getElementById("addgamedesc");
    var syscfg = document.getElementById("addgamesyscfg");
    var price = document.getElementById("addgameprice");
    var discount = document.getElementById("addgamediscount");
    creater.value = "";
    name.value = "";
    desc.value = "";
    syscfg.value = "";
    price.value = "";
    discount.value = "";
}

function addkind() {
    var kind = document.getElementById("addkindinput").value;
    $.post("/admin/addkind", {kind: kind}, function (result) {
        if (result.success) {
            alert("Add success！");
        }
        else {
            alert(result.msg);
        }
    })
}