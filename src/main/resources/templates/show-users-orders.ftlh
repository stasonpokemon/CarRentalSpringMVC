<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-3">
        <h2>My orders:</h2>

        <#if orders?size = 0>
            There is no orders
        <#else>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Car</th>
                    <th scope="col">User</th>
                    <th scope="col">Cost</th>
                    <th scope="col">Order status</th>
                    <th scope="col">Order date</th>
                    <th scope="col">Rental period</th>
                </tr>
                </thead>
                <tbody>
                <#list orders as order>
                    <tr>
                        <td>${order.car.producer} ${order.car.model}</td>
                        <td>${order.user.username}</td>
                        <td>${order.price}</td>
                        <td>${order.orderStatus}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.rentalPeriod}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#if>
    </div>

</@c.page>