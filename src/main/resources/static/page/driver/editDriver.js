var row;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    renderForm();

    // window.renderForm = function () {
    function renderForm() {
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#driverDeadline'
            });
        });
        layui.use('form', function () {
            var form = layui.form;
            form.render();
        });
        initFormData();
    }


    form.on("submit(editSubmit)", function (data) {
        var dataPara = data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../DriverController/updateDriver", //------ 跟新接口
            type: "post",
            dataType: "json",
            data: dataPara,
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("更新失败！");
            },
            success: function (json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("更新成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg("更新失败,失败信息为:" + json.detail);
                }
            }
        });
        return false;
    });

    //重置页面内同容
    $("#reset").click(function () {
        initFormData();
    });

    function initFormData() {
        if (row != null && row != '') {
            $("#driverId").val(row.driverId);
            $("#driverName").val(row.driverName);
            $("#driverPhone").val(row.driverPhone);
            $("#driverDeadline").val(row.driverDeadline);
            var usableFlag = row.usable;
            if (usableFlag) {
                usableFlag = "1";
            } else {
                usableFlag = "0";
            }
            $("input[name='isUsable'][value=\'" + usableFlag + "\']").prop("checked", true);
            $("#driverRemark").val(row.driverRemark);
            form.render();
        }
    }
});

function showFormData(data) {
    row = data;
    // renderForm();
}

