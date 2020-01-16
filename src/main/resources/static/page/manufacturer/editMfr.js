var row;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    initFormData();

    form.on("submit(editSubmit)", function (data) {
        var dataPara = data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../ManufacturerController/updateManufacturer",
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
                    top.layer.msg("更新失败,失败信息为:"+json.detail);
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
            $("#mfrId").val(row.mfrId);
            $("#mfrCode").val(row.mfrCode);
            $("#mfrName").val(row.mfrName);
            $("#mfrAddress").val(row.mfrAddress);
            $("#mfrRemark").val(row.mfrRemark);
            form.render();
        }
    }
});

function showFormData(data) {
    row = data;
}

