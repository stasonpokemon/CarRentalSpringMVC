<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>All orders:</h2>

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

</@c.page>