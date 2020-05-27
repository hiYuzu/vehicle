/**
 * @author hiYuzu
 * @version V1.0
 * @date 2019/8/5 11:44
 */
var row;
var count;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    initFormData();

    form.on("submit(editPagePower)", function (data) {
        var dataPara = data.field;
        var dataArray = [];
        for (var key in dataPara) {
            if (key !== "authorityId") {
                dataArray.push(dataPara[key]);
            }
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../AuthoritySetController/updatePagePower",
            type: "post",
            dataType: "json",
            data: {authorityId: dataPara.authorityId, dataArray: dataArray},
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

    // window.initFormData = function () {
    function initFormData() {
        $.ajax({
            url: "../../PageController/getAllPage",
            type: "post",
            dataType: "json",
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("权限组用户更新失败！");
            },
            success: function (json) {
                if (json.result) {
                    $(".pageList").html(pageList(json.data));
                    makeChecked();
                } else {
                    top.layer.msg(json.msg);
                }
            }
        });
    }

    function makeChecked() {
        $.ajax({
            url: "../../PageController/getAuthorityPage",
            type: "post",
            dataType: "json",
            data: {authorityId: row.authorityId},
            error: function (json) {
                top.layer.close(index);
                top.layer.msg("权限组用户更新失败！");
            },
            success: function (json) {
                check(json.data);
            }
        });
    }

    function pageList(data) {
        count = data.length;
        var inputHtml = '<input type="checkbox" name="n0" value="' + data[0].pageId + '" id="' + data[0].pageId + '" title="' + data[0].title + '">';
        for (var i = 1; i < data.length; i++) {
            inputHtml += '<input type="checkbox" name="n' + i + '" value="' + data[i].pageId + '" id="' + data[i].pageId + '" title="' + data[i].title + '">'
        }
        return inputHtml;
    }

    function check(data) {
        for (var i = 0; i < data.length; i++) {
            var cheId = "#" + data[i].pageId;
            $(cheId).prop("checked", true);
        }
        if (row != null && row !== '') {
            $("#authorityId").val(row.authorityId);
            form.render();
        }
    }


    function getJsonLength(jsonData) {
        var length;
        for (var ever in jsonData) {
            length++;
        }
        return length;
    }
});

function showFormData(data) {
    row = data;
    // initFormData();
}