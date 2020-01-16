/**
 * @author hiYuzu
 * @version V1.0
 * @date 2019/8/5 10:10
 */
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        table = layui.table,
        $ = layui.jquery;


    initAuthorityPage();

    function initAuthorityPage() {
        var authorityName = $("#searchName").val();
        searchAuthority(authorityName);
    }

    function searchAuthority(authorityName) {
        table.render({
            id: 'authorityTable'
            , elem: '#authorityListTable'
            , url: '../../AuthorityController/getAuthority'
            , method: 'post'
            , where: {authorityName: authorityName} //传参
            , page: true //开启分页
            , cols: [[
                  {field: 'authorityCode', title: '编号', minWidth: '100'}
                , {field: 'authorityName', title: '名称', minWidth: '120'}
                , {field: 'authorityRemark', title: '备注', minWidth: '120'}
                , {field: 'optUserName', title: '操作者', minWidth: '120'}
                , {field: 'optTime', title: '操作时间', minWidth: '180'}
                , {title: '操作', minWidth: 120, align: 'center', toolbar: '#barAuthorityList'}
            ]]
        });
    }

    //监听工具条
    table.on('tool(authorityListFilter)', function (obj) {
        var data = obj.data;
        if (data == null || data == "") {
            layer.msg("请选择需要操作的权限组！");
            return;
        }
        var index;
        if (obj.event === 'editDevice') {
            index = layui.layer.open({
                title: "设置车辆操作权限",
                type: 2,
                content: "editVehiclePower.html",
                success: function (layero, index) {
                    var frameWin = window[layero.find('iframe')[0]['name']];
                    frameWin.showFormData(data);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layui.layer.full(index);
            });
            layui.layer.full(index);
        } else if (obj.event === 'editThing') {
            index = layui.layer.open({
                title: "设置油卡操作权限",
                type: 2,
                content: "editOilPower.html",
                success: function (layero, index) {
                    var frameWin = window[layero.find('iframe')[0]['name']];
                    frameWin.showFormData(data);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layui.layer.full(index);
            });
            layui.layer.full(index);
        } else if (obj.event === 'editPage') {
            index = layui.layer.open({
                title: "设置页面权限",
                type: 2,
                content: "editPagePower.html",
                success: function (layero, index) {
                    var frameWin = window[layero.find('iframe')[0]['name']];
                    frameWin.showFormData(data);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layui.layer.full(index);
            });
            layui.layer.full(index);
        } else if (obj.event === 'editUser') {
            index = layui.layer.open({
                title: "设置用户权限组",
                type: 2,
                content: "editUserPower.html",
                success: function (layero, index) {
                    var frameWin = window[layero.find('iframe')[0]['name']];
                    frameWin.showFormData(data);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layui.layer.full(index);
            });
            layui.layer.full(index);
        }
    });
    //添加查询
    $(".search_btn").click(function () {
        initAuthorityPage();
    });

});