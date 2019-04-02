layui.use(['element','jquery'], function () {
    var element = layui.element;
    var $ = layui.jquery;
    $(function () {
        var html="";
        //获取菜单
        $.ajax({
            url: "/roleMenu/menuInfo",
            type: "get",
            dataType: "json",
            data: {},
            success: function (data) {
                $("#nav").find('span.layui-nav-bar').remove()
                var navs =data.data;
                /*html += '<ul class="layui-nav layui-nav-tree"  lay-filter="test">';*/
                $.each(navs, function (i, item) {
                    html = html + '<li class="layui-nav-item">';
                    html += '<a href="javascript:;" th:href="@{' + item.href + '} " nav-id="' + item.menuId + '">' +
                        '<cite>' + item.content + '</cite></a>';
                    if (item.children != null && item.children.length > 0) {
                        html += '<dl class="layui-nav-child">';
                        $.each(item.children, function (j, item2) {
                            html += '<dd>'
                            html += '<a href="javascript:;" th:href="@{' + item2.href + '} " ' +
                                'nav-id="' + item2.menuId + '">' +
                                '<cite>' + item2.content + '</cite></a></dd>';
                            if (item2.children != null && item2.children.length > 0) {
                                html += '<ol class="layui-nav-child">';
                                $.each(item2.children, function (k, item3) {
                                    html += '<li>' +
                                        '<a href="javascript:;" th:href="@{' + item3.href + '} " nav-id="' + item3.menuId + '">' +
                                        '<cite>' + item3.content + '</cite>' +
                                        '</a>' +
                                        '</li>';
                                });
                                html += '</ol>';
                            }
                        });
                        html += '</dl>';
                    }
                    html += '</li>';
                });
                /* html += '</ul>';*/
                $('#nav').html(html);

                element.render('nav','test');
            }
        })
    })

});