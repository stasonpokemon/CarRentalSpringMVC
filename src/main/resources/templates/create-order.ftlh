<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>


    <#if passportIsAvailable != true>
        You need to add your passport for order
        <div class="col-12 m-2 p-2">
            <button type="submit" class="btn btn-primary"
                    onclick="window.location ='/user/${currentUserId}/passport/add'">Add passport
            </button>
        </div>
    </#if>

    <#if passportIsAvailable == true>

        <div class="mb-3">
            <dl class="row">
                <dt class="col-sm-3">Producer</dt>
                <dd class="col-sm-9">${car.producer}</dd>
                <dt class="col-sm-3">Model</dt>
                <dd class="col-sm-9">${car.model}</dd>
                <dt class="col-sm-3">Price per day</dt>
                <dd class="col-sm-9">${car.pricePerDay}$</dd>
            </dl>
            <form method="post" action="/orders/${currentUserId}/${car.id}">
                <label for="rental period" class="form-label">Rental period</label>
                <input type="text" class="form-control" name="rentalPeriod" id="rentalPeriod"
                       placeholder="Rental period">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Order</button>
            </form>

        </div>

    </#if>

</@c.page>