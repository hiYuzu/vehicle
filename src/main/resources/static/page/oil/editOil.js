/**
 * @author plr
 * @version V1.0
 * @date 2019/7/31 14:32
 */
var row;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    initFormData();

    form.on("submit(editOil)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../OilController/updateOil",
            type: "post",
            dataType: "json",
            data: data.field,
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("油卡更新失败！");
            },
            success: function (json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("油卡更新成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg("油卡更新失败,失败信息为:" + json.detail);
                }
            }
        });
        return false;
    });

    //重置页面内同容
    $("#reset").click(function () {
        initFormData();
    });
    // window.initFormData = function () {
    function initFormData() {
        if (row != null && row !== undefined) {
            console.log(row);
            $("#oilId").val(row.oilId);
            $("#oilCode").val(row.oilCode);
            $("#oilManufacturer").val(row.oilManufacturer);
            $("#oilBalance").val(row.oilBalance);
            form.render();
        }
    };
});

function showFormData(data) {
    row = data;
    // initFormData();
}