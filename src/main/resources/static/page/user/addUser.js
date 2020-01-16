layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;

 	var addUserArray = [],addUser;
 	form.on("submit(addUser)",function(data){
        var dataPara=data.field;
        //弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url : "../../UserController/insertUser",
            type : "post",
            dataType : "json",
            data : dataPara,
            error : function(json) {
                top.layer.close(index);
                top.layer.msg("用户添加失败！");
            },
            success : function(json) {
                top.layer.close(index);
                if (json.result) {
                    top.layer.msg("用户添加成功！");
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