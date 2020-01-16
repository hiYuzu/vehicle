layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        table = layui.table,
        $ = layui.jquery;

    startSearchParam();

    function startSearchParam() {
        var paramType = $("#searchType").val();
        var paramValue = $("#mfrSearchValue").val();
        searchParam(paramType, paramValue);
    }

    function searchParam(searchType, searchValue) {
        table.render({
            id: 'listTable'
            , elem: '#listTable'
            , url: '../../ManufacturerController/getManufacturer' //------数据查询接口------
            , method: 'post'
            , where: {"searchType": searchType, "searchValue": searchValue} //传参*/
            , page: true //开启分页
            , cols: [[
                {field: 'mfrCode', title: '厂商编码', minWidth: '120', sort: true}
                , {field: 'mfrName', title: '厂商名称', minWidth: '120'}
                , {field: 'mfrAddress', title: '厂商地址', minWidth: '120'}
                , {field: 'mfrRemark', title: '厂商备注', minWidth: '180'}
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
            layer.msg("请选择需要操作的参数！");
            return;
        }
        if (obj.event === 'delete') {
            layer.confirm('确认删除\'' + data.mfrName + '\'吗', function (index) {
                //弹出loading
                var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                $.ajax({
                    url: "../../ManufacturerController/delManufacturer",//------数据删除接口------
                    type: "post",
                    dataType: "json",
                    data: {"delMfrId": data.mfrId},
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
                title: "修改厂商信息",
                type: 2,
                content: "editMfr.html",
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
            title: "添加厂商",
            type: 2,
            content: "addMfr.html"
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

});