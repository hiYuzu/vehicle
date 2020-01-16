/**
 * @author plr
 * @version V1.0
 * @date 2019/7/31 16:17
 */
layui.config({
    base : "js/"
}).use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    $.ajax({
       url : "../../VehicleController/getForeignInfo",
        type : "post",
        dataType : "json",
        error : function (json) {
            top.layer.close(index);
            top.layer.msg("相关信息获取失败");
        },
        success : function (json) {
            if (json !== undefined) {
                for (var i = 0; i < json.length; i++) {
                    if (json[i].vtId !== undefined) {
                        $("#vtId").append("<option value=\""+json[i].vtId+"\">"+json[i].vtName+"</option>");
                    }
                    if (json[i].mfrId !== undefined) {
                        $("#mfrId").append("<option value=\""+json[i].mfrId+"\">"+json[i].mfrName+"</option>");
                    }
                }
            }
            form.render();
        }
    });
    form.on("submit(addVehicle)",function(data){
        var dataPara=data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : "../../VehicleController/insertVehicle",
            type : "post",
            dataType : "json",
            data : dataPara,
            error : function(json) {
                top.layer.close(index);
                top.layer.msg("车辆添加失败！");
            },
            success : function(json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("车辆添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg(json.detail);
                }
            }
        });
        return false;
    })
});