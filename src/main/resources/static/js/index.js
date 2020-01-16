var $, tab, skyconsWeather;
layui.config({
    base: "js/"
}).use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element;
    $ = layui.jquery;
    tab = layui.bodyTab();

    $.ajax({
        url: "UserController/getLoginUser",
        type: "post",
        dataType: "json",
        error: function (json) {
            layer.msg("获取登录用户信息失败！");
        },
        success: function (json) {
            if (json != null) {
                $("#citeLoginName").html(json.userName);
                $("#spanUserName").html(json.userName);
                $("#lockUserName").html(json.userName);
                form.render();
            } else {
                layer.msg("获取登录用户信息失败！");
            }
        }
    });

    //隐藏左侧导航
    $(".hideMenu").click(function () {
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        // tab.move();
    });
    //渲染左侧菜单
    // tab.render();
    //锁屏
    function lockPage() {
        layer.open({
            title: false,
            type: 1,
            content: $("#lock-box"),
            closeBtn: 0,
            shade: 0.9
        });
    }

    $(".lockcms").on("click", function () {
        window.sessionStorage.setItem("lockcms", true);
        lockPage();
        $("#unlock").siblings(".admin-header-lock-input").val('');
    });
    // 判断是否显示锁屏
    if (window.sessionStorage.getItem("lockcms") == "true") {
        lockPage();
    }
    // 解锁
    $("#unlock").on("click", function () {
        if ($(this).siblings(".admin-header-lock-input").val() == '') {
            layer.msg("请输入登录密码！");
        } else {
            $.ajax({
                url: "UserController/unlockScreen",
                type: "post",
                dataType: "json",
                data: {passWord: $(this).siblings(".admin-header-lock-input").val()},
                error: function (json) {
                    layer.msg("解锁失败，请重新输入！");
                },
                success: function (json) {
                    if (json.result) {
                        window.sessionStorage.setItem("lockcms", false);
                        $(this).siblings(".admin-header-lock-input").val('');
                        layer.closeAll("page");
                    } else {
                        layer.msg(json.detail);
                    }
                }
            });
        }
    });
    $(document).on('keydown', function () {
        if (event.keyCode == 13) {
            $("#unlock").click();
        }
    });

    //手机设备的简单适配
    var treeMobile = $('.site-tree-mobile'),
        shadeMobile = $('.site-mobile-shade');

    treeMobile.on('click', function () {
        $('body').addClass('site-mobile');
    });

    shadeMobile.on('click', function () {
        $('body').removeClass('site-mobile');
    });

    // 添加新窗口
    // $(".navBar .layui-nav .layui-nav-item a").on("click",function(){
    $(document).on("click", "#navOfa", function () {
        addTab($(this));
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    });
    $(document).on("click", "#changePwd", function () {
        addTab($(this));
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    });

    //公告层
    function showNotice() {
        layer.open({
            type: 1,
            title: "系统公告", //不显示标题栏
            closeBtn: false,
            area: '310px',
            shade: 0.8,
            id: 'LAY_layuipro', //设定一个id，防止重复弹出
            btn: ['知道了'],
            moveType: 1, //拖拽模式，0或者1
            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p>系统流程：组装->测试->老化->库存->发货->商务->设备->维修</p><p>已正式运行，如有疑问，请联系开发所王垒，邮箱：wanglei@712.cn。</p></div>',
            success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click", function () {
                    window.sessionStorage.setItem("showNotice", "true");
                });
                if ($(window).width() > 432) {  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
                    btn.on("click", function () {
                        layer.tips('系统公告躲在了这里', '#showNotice', {
                            tips: 3
                        });
                    })
                }
            }
        });
    }

    //刷新后还原打开的窗口
    if (window.sessionStorage.getItem("menu") != null) {
        menu = JSON.parse(window.sessionStorage.getItem("menu"));
        curmenu = window.sessionStorage.getItem("curmenu");
        var openTitle = '';
        for (var i = 0; i < menu.length; i++) {
            openTitle = '';
            if (menu[i].icon.split("-")[0] == 'icon') {
                openTitle += '<i class="iconfont ' + menu[i].icon + '"></i>';
            } else {
                openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
            }
            openTitle += '<cite>' + menu[i].title + '</cite>';
            openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
            element.tabAdd("bodyTab", {
                title: openTitle,
                content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                id: menu[i].layId
            });
            //定位到刷新前的窗口
            if (curmenu != "undefined") {
                if (curmenu == '' || curmenu == "null") {  //定位到后台首页
                    element.tabChange("bodyTab", '');
                } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
                    element.tabChange("bodyTab", menu[i].layId);
                }
            } else {
                element.tabChange("bodyTab", menu[menu.length - 1].layId);
            }
        }
    }

});

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}

//退出
function logout() {
    if (confirm("确认退出系统吗？")) {
        $.ajax({
            url: "logout",
            type: "post",
            dataType: "json",
            error: function (json) {
                if (confirm("系统无反应，强制退出吗？")) {
                    window.location.href = "login";
                }
            },
            success: function (json) {
                if (json.result) {
                    window.location.href = "login";
                } else {
                    alert(json.detail);
                }
            }
        });
    }
    window.sessionStorage.clear();
}