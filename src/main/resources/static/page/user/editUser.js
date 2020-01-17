var row;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    form.on("submit(editUser)", function (data) {
        var dataPara = data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../UserController/updateUser",
            type: "post",
            dataType: "json",
            data: dataPara,
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("用户更新失败！");
            },
            success: function (json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("用户更新成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg("用户更新失败,失败信息为:" + json.detail);
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
            $("#userId").val(row.userId);
            $("#userCode").val(row.userCode);
            $("#userName").val(row.userName);
            $("#userTel").val(row.userTel);
            $("#userEmail").val(row.userEmail);
            var userDeleteValue = row.userDelete;
            if (userDeleteValue) {
                userDeleteValue = "1";
            } else {
                userDeleteValue = "0";
            }
            $("input[name='userDelete'][value=\'" + userDeleteValue + "\']").prop("checked", true);
            $("#userRemark").val(row.userRemark);
            form.render();
        }
    }
});

function showFormData(data) {
    row = data;
    initFormData();
}

