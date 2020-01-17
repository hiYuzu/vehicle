layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        table = layui.table,
        $ = layui.jquery;

    startSearchParam();

    function startSearchParam() {
        var searchNameValue = $("#driverNameValue").val();
        var searchUsableValue = $("#isUsable").val();
        searchParam(searchNameValue, searchUsableValue);
    }

    function searchParam(searchNameValue, searchUsableValue) {
        table.render({
            elem: '#listTable'
            , url: '../../DriverController/getDriver' //------数据查询接口------
            , method: 'post'
            , where: {driverName: searchNameValue, isUsable: searchUsableValue} //传参*/
            , page: true //开启分页
            , cols: [[
                {field: 'driverName', title: '驾驶员姓名', minWidth: '120', sort: true}
                , {field: 'driverPhone', title: '联系电话', minWidth: '120'}
                , {field: 'driverDeadline', title: '驾照到期时间', minWidth: '180'}
                , {field: 'driverRemark', title: '备注信息', minWidth: '180'}
                , {field: 'usable', title: '是否可用', minWidth: '100', templet: function (d) {
                        return d.usable ? "可用" : "停用";
                    }
                }
                , {field: 'optUserName', title: '操作员', minWidth: '120'}
                , {field: 'optTime', title: '操作时间', minWidth: '120'}
                , {title: '操作', fixed: 'right', minWidth: 120, align: 'center', toolbar: '#barOperateList'}
            ]]
        });
    }

    //监听工具条
    table.on('tool(listTableFilter)', function (obj) {
        var data = obj.data;
        if (data == null || data == "") {
            layer.msg("请选择需要操作的列！");
            return;
        }
        if (obj.event === 'delete') {
            layer.confirm('确认删除\'' + data.driverName + '\'吗', function (index) {
                //弹出loading
                var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                $.ajax({
                    url: "../../DriverController/delDriver",//------数据删除接口------
                    type: "post",
                    dataType: "json",
                    data: {"driverId": data.driverId},
                    error: function (json) {
                        top.layer.close(index);
                        top.layer.msg("删除失败！");
                    },
                    success: function (json) {
                        top.layer.close(index);
                        if (json.result) {
                            top.layer.msg("删除成功！");
                            startSearchParam();
                        } else {
                            top.layer.msg(json.detail);
                        }
                    }
                });
            });
        } else if (obj.event === 'edit') {
            var index = layui.layer.open({
                title: "修改驾驶员信息",
                type: 2,
                content: "editDriver.html",
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
        startSearchParam();
    });

    //添加参数
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "添加驾驶员",
            type: 2,
            content: "addDriver.html"
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

});