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

    form.on("submit(addOil)",function(data){
        var dataPara=data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : "../../OilController/insertOil",
            type : "post",
            dataType : "json",
            data : dataPara,
            error : function(json) {
                top.layer.close(index);
                top.layer.msg("油卡添加失败！");
            },
            success : function(json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("油卡添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    top.layer.msg(json.detail);
                }
            }
        });
    })
});