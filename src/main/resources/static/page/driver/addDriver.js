layui.config({
    base : "js/"
}).use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;

    renderForm();
    function renderForm(){
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            laydate.render({
                elem: '#driverDeadline'
            });
        });
        layui.use('form', function(){
            var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
            form.render();
        });
    }

    form.on("submit(addSubmit)",function(data){
        var dataPara=data.field;
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : "../../DriverController/addDriver",
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
                    top.layer.msg("添加失败，信息为:"+json.detail);
                }
            }
        });
        return false;
    })
});