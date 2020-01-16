/**
 * @author hiYuzu
 * @version V1.0
 * @date 2019/7/31 11:47
 */
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        table = layui.table,
        $ = layui.jquery;


    initAuthorityPage();

    function initAuthorityPage() {
        var authorityName = $("#searchName").val();
        searchAuthority(authorityName);
    }

    function searchAuthority(authorityName) {
        table.render({
            id: 'authorityTable'
            , elem: '#authorityListTable'
            , url: '../../AuthorityController/getAuthority'
            , method: 'post'
            , where: {authorityName: authorityName} //传参
            , page: true //开启分页
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'authorityCode', title: '编号', minWidth: '100'}
                , {field: 'authorityName', title: '名称', minWidth: '120'}
                , {field: 'authorityRemark', title: '备注', minWidth: '120'}
                , {field: 'optUserName', title: '操作者', minWidth: '120'}
                , {field: 'optTime', title: '操作时间', minWidth: '180'}
                , {title: '操作', fixed: 'right', minWidth: 120, align: 'center', toolbar: '#barAuthorityList'}
            ]]
        });
    }

    //监听工具条
    table.on('tool(authorityListFilter)', function (obj) {
        var data = obj.data;
        if (data == null || data == "") {
            layer.msg("请选择需要操作的权限组！");
            return;
        }
        if (obj.event === 'del') {
            layer.confirm('确认删除\'' + data.authorityName + '\'吗', function (index) {
                var idArray = [];
                idArray.push(data.authorityId);
                //弹出loading
                var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                $.ajax({
                    url: "../../AuthorityController/deleteAuthority",
                    type: "post",
                    dataType: "json",
                    data: {"list": idArray},
                    error: function (json) {
                        top.layer.close(index);
                        top.layer.msg("权限组删除失败！");
                    },
                    success: function (json) {
                        top.layer.close(index);
                        if (json.result) {
                            top.layer.msg("权限组删除成功！");
                            initAuthorityPage();
                        } else {
                            top.layer.msg(json.detail);
                        }
                    }
                });
            });
        } else if (obj.event === 'edit') {
            var index = layui.layer.open({
                title: "修改权限组",
                type: 2,
                content: "editAuthority.html",
                success: function (layero, index) {
                    var frameWin = window[layero.find('iframe')[0]['name']];
                    frameWin.showFormData(data);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layui.layer.full(index);
            });
            layui.layer.full(index);
        }
    });
    //添加查询
    $(".search_btn").click(function () {
        initAuthorityPage();
    });

    //添加权限组
    $(".authorityAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加权限组",
            type: 2,
            content: "addAuthority.html",
            success: function (layero, index) {
                // layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                // 	tips: 3
                // });
            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    //批量删除
    $(".batchDel").click(function () {
        layer.confirm('确认删除选择的权限组吗?', function (index) {
            var idArray = [];
            var checkStatus = table.checkStatus('authorityTable')
                , data = checkStatus.data;
            if (data == null || data.length == 0) {
                layer.alert("请选择需要删除权限组！");
                return;
            }
            layui.each(data, function (index, item) {
                idArray.push(item.authorityId);
            });
            //弹出loading
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            $.ajax({
                url: "../../AuthorityController/deleteAuthority",
                type: "post",
                dataType: "json",
                data: {"list": idArray},
                error: function (json) {
                    top.layer.close(index);
                    top.layer.msg("权限组删除失败！");
                },
                success: function (json) {
                    top.layer.close(index);
                    if (json.result) {
                        top.layer.msg("权限组删除成功！");
                        //重新查询
                        initAuthorityPage();
                    } else {
                        top.layer.msg(json.detail);
                    }
                }
            });
        });
    });

});