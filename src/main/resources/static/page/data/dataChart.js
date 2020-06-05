/**
 * @author plr
 * @version V1.0
 * @date 2020/3/27 14:43
 */
layui.config({
    base: "js/"
}).use(['layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    var chart1 = echarts.init(document.getElementById('chart1'));
    var chartData = null;
    var roRecord;
    drawChart();
    $(".search_btn").click(function () {
        roRecord = $("#roRecord").val();
        searchData($("#timeRange").val(), $("#vehicleCodeValue").val());
    });
    function searchData(timeRange, vehicleCode) {
        if (vehicleCode == "") {
            layer.msg("请输入车辆编号！");
            return;
        }
        var url = "../../VehicleController/getRepairRecord";
        if (roRecord == 1){
            url = "../../VehicleController/getOilRecord";
        }
        else if (roRecord == 2) {
            url = "../../VehicleController/getRentRecord";
        }
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data:{timeRange: timeRange, vehicleCode: vehicleCode},
            error: function (json) {
                chartData = null;
                layer.msg("查询失败！");
            },
            success: function (json) {
                if (json.count > 0) {
                    chartData = json.data;
                    drawChart();
                } else {
                    chartData = null;
                    layer.msg("无数据");
                }
            }
        })
    }
    function drawChart() {
        if (chartData != null) {
            var xAxisData = [];
            var seriesData = [];
            for (var i = 0; i < Object.keys(chartData).length; i++) {
                if (roRecord == 0) {
                    xAxisData.push(chartData[i].beginTime);
                    seriesData.push(chartData[i].repairCost);
                }
                else if (roRecord == 1) {
                    xAxisData.push(chartData[i].optTime);
                    //seriesData.push(chartData[i].oilCost);
                    seriesData = chartData.map(function (item) {
                        return {
                            value: item.oilCost,
                            optName: item.oilManufacturer
                        }
                    })
                }
                else if (roRecord == 2) {
                    xAxisData.push(chartData[i].beginTime);
                    seriesData.push(chartData[i].rentCost);
                    seriesData = chartData.map(function (item) {
                        return {
                            value: item.rentCost,
                            optName: "驾驶员: " + item.driverName
                        }
                    })
                }
            }
            //指定图表配置项和数据
            var chartTitle = $("#roRecord option:selected").text();
            var option = {
                title: {
                    text: chartTitle
                },
                tooltip: {
                    trigger:"axis",
                    formatter: function (params) {
                        return params[0].data.optName;
                    }
                },
                legend: {
                },
                xAxis: {
                    data: xAxisData
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    name: chartData[0].vehicleName,
                    type: 'bar', //柱状
                    data: seriesData,
                    itemStyle: {
                        normal: { //柱子颜色
                            color: 'red'
                        }
                    }
                }]
            };
            chart1.setOption(option, true);
        }
    }
    //出租
    $(".vehicleGo").click(function () {
        var index = layui.layer.open({
            title: "车辆出租",
            type: 2,
            content: "vehicleGo.html",
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
    //加油
    $(".vehicleOil").click(function () {
        var index = layui.layer.open({
            title: "车辆加油",
            type: 2,
            content: "vehicleOil.html",
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
    //维修
    $(".vehicleRepair").click(function () {
        var index = layui.layer.open({
            title: "车辆维修",
            type: 2,
            content: "vehicleRepair.html",
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
});