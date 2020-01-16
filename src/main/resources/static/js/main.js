layui.config({
    base: "js/"
}).use(['form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        $ = layui.jquery;

    $(".panel a").on("click", function () {
        var thisDiv = $(this);
        var pageId = thisDiv.attr("id");
        $.ajax({
            url: "AuthorityController/isAuthorityPage",
            type: "post",
            dataType: "json",
            data: {pageId: pageId},
            success: function (json) {
                if (json.detail !== "") {
                    thisDiv.attr("data-url", json.detail);
                    window.parent.addTab(thisDiv);
                } else {
                    top.layer.msg("您没有查看此页面的权限！")
                }
            }
        });
    });

    //获取公告列表
    $.ajax({
        url: "NewsController/showNews",
        type: "post",
        dataType: "json",
        error: function (json) {
            top.layer.msg("获取公告信息失败！");
        },
        success: function (json) {
            if (json != null) {
                if (json.count > 0) {
                    initNews(json.data);
                } else {
                    top.layer.msg(json.detail);
                }
            } else {
                top.layer.msg("获取公告信息失败！");
            }
        }
    });

    function initNews(data) {
        //加载最新文章
        var hotNewsHtml = '';
        for (var i = 0; i < data.length; i++) {
            hotNewsHtml += '<tr>'
                + '<td align="left">' + data[i].newsTitle + '</td>'
                + '<td align="left">' + data[i].newsContent + '</td>'
                + '<td align="left">' + data[i].optUserName + '</td>'
                + '<td>' + data[i].showTime + '</td>'
                + '</tr>';
        }
        $(".hot_news").html(hotNewsHtml);
    }

    $.get("MainController/getCount",
        function (data) {
            $(".userCount span").text(data.user);
            $(".authorityCount span").text(data.authority);
            $(".driverCount span").text(data.driver);
            $(".vehicleCount span").text(data.vehicle);
            $(".mfrCount span").text(data.mfr);
            $(".oilCount span").text(data.oil);
        }
    );

    //数字格式化
    $(".panel span").each(function () {
        $(this).html($(this).text() > 9999 ? ($(this).text() / 10000).toFixed(2) + "<em>万</em>" : $(this).text());
    });

});
