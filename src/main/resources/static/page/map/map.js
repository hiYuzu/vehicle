layui.config({
    base: "js/"
}).use(['layer', 'jquery'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    $.ajax({
        url:"../../VehicleController/getAuthorityVehicle",
        type:"post",
        dataType:"json",
        success:function (json) {
            for (var i = 0; i < json.data.length; i++) {
                if (json.data[i].vehicleId !== undefined) {
                    $("#vehicle").append("<option value=\"" + json.data[i].vehicleId + "\">" + json.data[i].vehicleName + "</option>");
                }
            }
        }
    });
    // 百度地图API功能
    var map = new BMap.Map("map");
    var point = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(point, 15);

    function add_overlay(marker){
        map.addOverlay(marker);
    }

    $(".search_btn").click(function () {
        map.clearOverlays();
        $.ajax({
            url:"../../Gps/getGpsInfo",
            type:"post",
            dataType:"json",
            async:false,
            success:function (json) {
                if (json != null) {
                    var pointX = json.pointX;
                    var pointY = json.pointY;
                    var point = new BMap.Point(pointX,pointY);
                    map.centerAndZoom(point, 15);
                    add_overlay(new BMap.Marker(point));
                }
            }
        });
    });
});