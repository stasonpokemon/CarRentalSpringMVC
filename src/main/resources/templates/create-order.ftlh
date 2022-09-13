<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>

    <div class="container mt-3">
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

            <form method="post" action="/orders/create/${currentUserId}/${car.id}">

                <div class="col-md-4">
                    <label for="rentalPeriodId" class="form-label">Rental period</label>
                    <input type="number"
                           class="form-control ${(rentalPeriodError??)?string('is-invalid','')}"
                           step="1"
                           min="1"
                           id="rentalPeriodId"
                           name="rentalPeriod" placeholder="0"
                           aria-describedby="validationRentalPeriodFeedback"/>
                    <#if rentalPeriodError??>
                        <div id="Rental" class="invalid-feedback">
                            ${rentalPeriodError}
                        </div>
                    </#if>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary mt-3">Order</button>
            </form>

        </div>

    </#if>
    </div>

</@c.page>