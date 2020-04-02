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
    initChart();
    $(".search_btn").click(function () {
        searchData($("#roRecord").val(), $("#timeRange").val(), $("#vehicleCodeValue").val());
    });
    function searchData(roRecord, timeRange, vehicleCode) {
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
                if (json.result) {
                    chartData = json.data;
                    initChart();
                } else {
                    chartData = null;
                    layer.msg(json.detail);
                }
            }
        })
    }
    function initChart() {
        chart1.clear();
        if (chartData != null) {
            //TODO..parse chartData
            drawChart();
        }
    }
    function drawChart(){

        //指定图表配置项和数据
        var chartTitle = $("#roRecord option:selected").text();
        var option = {
            title: {
                text: chartTitle
            },
            tooltip: {},
            legend: {
                data: ['销量', '产量']
            },
            xAxis: {
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '销量',
                type: 'bar', //柱状
                data: [100,200,300,400,500,600,700],
                itemStyle: {
                    normal: { //柱子颜色
                        color: 'red'
                    }
                }
            },{
                name:'产量',
                type:'bar',
                data:[120,210,340,430,550,680,720],
                itemStyle:{
                    normal:{
                        color:'blue'
                    }
                }
            }]
        };
        chart1.setOption(option, true);
    }
});