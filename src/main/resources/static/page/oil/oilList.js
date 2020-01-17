/**
 * @author plr
 * @version V1.0
 * @date 2019/7/31 11:47
 */
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        table = layui.table,
        $ = layui.jquery;


    initOilPage();

    function initOilPage() {
        var oilManufacturer = $("#searchManufacturer").val();
        searchOil(oilManufacturer);
    }

    function searchOil(oilName) {
        table.render({
            id: 'oilTable'
            , elem: '#oilListTable'
            , url: '../../OilController/getAuthorityOil'
            , method: 'post'
            , where: {oilName: oilName} //传参*/
            , page: true //开启分页
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'oilCode', title: '编码', minWidth: '100', sort: true}
                , {field: 'oilManufacturer', title: '厂商', minWidth: '120'}
                , {field: 'oilBalance', title: '余额', minWidth: '100'}
                , {field: 'optUserName', title: '操作者', minWidth: '100'}
                , {field: 'optTime', title: '操作时间', minWidth: '180'}
                , {title: '操作', fixed: 'right', minWidth: 120, align: 'center', toolbar: '#barOilList'}
            ]]
        });
    }

    //监听工具条
    table.on('tool(oilListFilter)', function (obj) {
        var data = obj.data;
        if (data == null || data == "") {
            layer.msg("请选择需要操作的监测物！");
            return;
        }
        if (obj.event === 'del') {
            layer.confirm('确认删除编号为\'' + data.oilCode + '\'的油卡吗', function (index) {
                var idArray = [];
                idArray.push(data.oilId);
                deleteOil(idArray);
            });
        } else if (obj.event === 'edit') {
            var index = layui.layer.open({
                title: "修改油卡",
                type: 2,
                content: "editOil.html",
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
    //查询
    $(".search_btn").click(function () {
        initOilPage();
    });

    //添加油卡
    $(".oilAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加油卡",
            type: 2,
            content: "addOil.html",
            success: function (layero, index) {
                // layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                // 	tips: 3
                // });
            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    //批量删除
    $(".batchDel").click(function () {
        layer.confirm('确认删除选择的油卡吗?', function (index) {
            var idArray = [];
            var checkStatus = table.checkStatus('oilTable')
                , data = checkStatus.data;
            if (data == null || data.length == 0) {
                layer.alert("请选择需要删除油卡！");
                return;
            }
            layui.each(data, function (index, item) {
                idArray.push(item.oilId);
            });
            deleteOil(idArray)
        });
    });

    function deleteOil(idArray) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../OilController/deleteOil",
            type: "post",
            dataType: "json",
            data: {"list": idArray},
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("油卡删除失败！");
            },
            success: function (json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("油卡删除成功！");
                    initOilPage();
                } else {
                    top.layer.msg(json.detail);
                }
            }
        });
    }
});