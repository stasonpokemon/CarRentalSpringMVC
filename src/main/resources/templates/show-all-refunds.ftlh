<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container">
        <h2>All refunds:</h2>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">User</th>
                <th scope="col">Damage status</th>
                <th scope="col">Damage description</th>
                <th scope="col">Repair cost</th>
                <th scope="col">Refund date</th>
            </tr>
            </thead>
            <tbody>
            <#list refunds as refund>
                <tr>
                    <td>${refund.order.user.username}</td>
                    <td><#if refund.damageStatus == true> Without damage <#else> With damage</#if></td>
                    <td>${refund.damageDescription}</td>
                    <td>${refund.price}$</td>
                    <td>${refund.refundDate}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>