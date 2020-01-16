/**
 * @author plr
 * @version V1.0
 * @date 2019/8/6 10:20
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
        var oilName = $("#searchName").val();
        searchOil(oilName);
    }

    function searchOil(oilName) {
        table = $.extend(table, {config: {checkName: 'checked'}});
        table.render({
            id: 'oilTable'
            , elem: '#oilListTable'
            , url: '../../OilController/getAccessOil'
            , method: 'post'
            , where: {oilName: oilName, authorityId: row.authorityId} //传参*/
            , page: false //分页
            , limit: 0
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'oilCode', title: '编码', minWidth: '100', sort: true}
                , {field: 'oilManufacturer', title: '厂商', minWidth: '120'}
                , {field: 'oilBalance', title: '余额', minWidth: '100'}
            ]]
        });
        if (row != null && row !== '') {
            $("#authorityId").val(row.authorityId);
            form.render();
        }
    }

    //添加查询
    $(".search_btn").click(function () {
        initOilPage();
    });

    form.on("submit(editOilPower)", function (data) {
        var dataPara = data.field;
        var checkStatus = table.checkStatus('oilTable')
            , tableData = checkStatus.data;
        var dataArray = [];
        for (var i = 0; i < tableData.length; i++) {
            for (var key in tableData[i]) {
                if (key === "oilId") {
                    dataArray.push(tableData[i][key]);
                }
            }
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            url: "../../AuthoritySetController/updateOilPower",
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
        initOilPage();
    });

});

function showFormData(data) {
    row = data;
}