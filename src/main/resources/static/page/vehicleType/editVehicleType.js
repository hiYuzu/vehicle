var row;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    initAllData();

    function initAllData() {
        //初始化组织数据
        $.get("../../DeviceTypeController/getTypeIdAndTypeName",
            function (data) {
                addOption(data);
            }
        );
    }

    function addOption(data) {
        var options = "<option value='0'>无</option>";
        for (var i = 0; i < data.length; i++) {
            if (data[i].vtId == row.vtId)
                continue;
            options += "<option value='" + data[i].vtId + "'>"
                + data[i].vtName
                + '</option>';
        }
        $("#vtType").html(options);
        renderForm();
    }

    function renderForm() {
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
            url: "../../VehicleTypeController/updateVehicleType",
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

    window.initFormData = function () {
        if (row != null && row != '') {
            $("#vtId").val(row.vtId);
            $("#vtCode").val(row.vtCode);
            $("#vtName").val(row.vtName);
            $("#vtType").val(row.vtType);
            form.render();
        }
    }
});

function showFormData(data) {
    row = data;
    initFormData();
}

