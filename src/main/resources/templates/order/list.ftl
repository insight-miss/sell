<html>

<#include "../common/header.ftl">
    <body>
    <div id="wrapper" class="toggled">
        <#--边栏-->
        <#include "../common/nav.ftl">
        <#--主要内容区域-->
        <div id="page-content-wrapper">
            <div class="container-Fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum().message}</td>
                        <td>${orderDTO.getPayStatusEnum().message}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                            </tbody>
                        </table>
                    </div>

                <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/List?page=${currentPage-1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..orderDTOPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/List?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/List?page=${currentPage+1}&size=${size}">下一页</a></li>
                    </#if>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        var websocket = null;
        if ('webSocket' in window) {
            websocket = new WebSocket('ws://http://127.0.0.1:8080/sell/webSocket');
        } else {
            alert('该浏览器不支持websocket');
        }

        websocket.onopen = function (ev) {
            consle.log('建立连接');
        }

        websocket.onclose = function (ev) {
            console.log('连接关闭');
        }

        websocket.onmessage = function (ev) {
            console.log('收到消息:' + ev.data);
        }

        websocket.onerror = function (ev) {
            alert('webSocket通信发生错误！');
        }

        window.onbeforeunload = function (ev) {
            websocket.close();
        }

    </script>
    </body>
</html>
