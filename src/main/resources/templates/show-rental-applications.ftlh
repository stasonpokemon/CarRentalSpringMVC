<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container">
        <h2>Rental applications:</h2>

        <#if orders?size = 0>
            No rental applications
        <#else>
            <div class="list-group">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Car</th>
                        <th scope="col">User</th>
                        <th scope="col">Cost</th>
                        <th scope="col">Order status</th>
                        <th scope="col">Order date</th>
                        <th scope="col">Rental period</th>
                        <th scope="col">Operation</th>
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
                            <td>
                                <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                                    <form method="post" action="/orders/rental-applications/cancel">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="submit" class="btn btn-danger me-2">Refuse</button>
                                    </form>
                                    <form method="post" action="/orders/rental-applications/accept">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="submit" class="btn btn-success">Accept</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </#if>
    </div>
</@c.page>