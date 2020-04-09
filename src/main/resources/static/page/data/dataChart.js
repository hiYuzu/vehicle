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
                else {
                    xAxisData.push(chartData[i].optTime);
                    seriesData.push(chartData[i].oilCost);
                }
            }
            //指定图表配置项和数据
            var chartTitle = $("#roRecord option:selected").text();
            var option = {
                title: {
                    text: chartTitle
                },
                tooltip: {},
                legend: {
                    data: [chartData[0].vehicleName]
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
});