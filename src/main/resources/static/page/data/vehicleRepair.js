/**
 * @author plr
 * @version V1.0
 * @date 2020/4/23 9:50
 */
layui.config({
    base : "js/"
}).use(['form','layer','jquery','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laydate = layui.laydate,
        $ = layui.jquery;

    //初始化时间
    laydate.render({
        elem: '#beginTime'
    });
    laydate.render({
        elem: '#endTime'
    });

    $.ajax({
        url : "../../RecordController/getInfo",
        type : "post",
        dataType : "json",
        error : function (json) {
            top.layer.close(index);
            top.layer.msg("相关信息获取失败");
        },
        success : function (json) {
            if (json !== undefined) {
                for (var i = 0; i < json.vehicle.length; i++) {
                    if (json.vehicle[i].vehicleCode !== undefined) {
                        $("#vehicleCode").append("<option value=\""+json.vehicle[i].vehicleCode+"\">"+json.vehicle[i].vehicleName+"</option>");
                    }
                }
            }
            form.render();
        }
    });

    form.on("submit(addRecord)",function(data){
        var dataPara = data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : "../../RecordController/insertRepairRecord",
            type : "post",
            dataType : "json",
            data : dataPara,
            error : function(json) {
                top.layer.close(index);
                top.layer.msg("添加失败！");
            },
            success : function(json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg("添加失败！");
                }
            }
        });
        return false;
    });

});
