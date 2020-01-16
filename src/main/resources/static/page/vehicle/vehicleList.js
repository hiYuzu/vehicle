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


    initVehiclePage();

    function initVehiclePage() {
        var vehicleName = $("#searchName").val();
        searchVehicle(vehicleName);
    }

    function searchVehicle(vehicleName) {
        table.render({
            id: 'vehicleTable'
            , elem: '#vehicleListTable'
            , url: '../../VehicleController/getAuthorityVehicle'
            , method: 'post'
            , where: {vehicleName: vehicleName} //传参*/
            , page: true //开启分页
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'vehicleCode', title: '车辆编号', minWidth: '100'}
                , {field: 'vehicleName', title: '车辆名称', minWidth: '100'}
                , {field: 'vtName', title: '车辆类型', minWidth: '100'}
                , {field: 'mfrName', title: '所属厂商', minWidth: '100'}
                , {field: 'vehicleBrand', title: '具体型号', minWidth: '100'}
                , {field: 'isUsableString', title: '车辆状态', minWidth: '100'}
                , {field: 'vehiclePrice', title: '租金/日', minWidth: '100'}
                , {field: 'optUserName', title: '操作者', minWidth: '100'}
                , {field: 'optTime', title: '操作时间', minWidth: '180'}
                , {title: '操作', fixed: 'right', minWidth: 120, align: 'center', toolbar: '#barVehicleList'}
            ]]
        });
    }

    //监听工具条
    table.on('tool(vehicleListFilter)', function (obj) {
        var data = obj.data;
        if (data == null || data == "") {
            layer.msg("请选择需要操作的车辆！");
            return;
        }
        if (obj.event === 'del') {
            layer.confirm('确认删除\'' + data.vehicleName + '\'吗', function (index) {
                var idArray = [];
                idArray.push(data.vehicleId);
                deleteVehicle(idArray);
            });
        } else if (obj.event === 'edit') {
            var index = layui.layer.open({
                title: "修改车辆",
                type: 2,
                content: "editVehicle.html",
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
        initVehiclePage();
    });

    //添加车辆
    $(".vehicleAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加车辆",
            type: 2,
            content: "addVehicle.html",
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
        layer.confirm('确认删除选择的车辆吗?', function (index) {
            var idArray = [];
            var checkStatus = table.checkStatus('vehicleTable')
                , data = checkStatus.data;
            if (data == null || data.length == 0) {
                layer.alert("请选择需要删除车辆！");
                return;
            }
            layui.each(data, function (index, item) {
                idArray.push(item.vehicleId);
            });
            deleteVehicle(idArray);
        });
    });

    function deleteVehicle(idArray) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../VehicleController/deleteVehicle",
            type: "post",
            dataType: "json",
            data: {"list": idArray},
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("车辆删除失败！");
            },
            success: function (json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg(json.detail);
                    initVehiclePage();
                } else {
                    top.layer.msg(json.detail);
                }
            }
        });
    }

});