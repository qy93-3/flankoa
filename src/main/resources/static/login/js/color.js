//颜色编辑模块
layui.use('colorpicker', function () {
    var $ = layui.$ , colorpicker = layui.colorpicker;
    //开启全功能
    colorpicker.render({
        elem: '#test-all'
        ,color: 'rgba(57, 61, 73, 1)'
        ,size: 'xs'
        ,format: 'rgb'
        ,predefine: true
        ,alpha: true
        ,change: function(color){
            //给当前页面头部和左侧设置主题色
            $('.layui-header,.layui-side-scroll,.layui-nav').css('background-color', color);
        }
    });

});