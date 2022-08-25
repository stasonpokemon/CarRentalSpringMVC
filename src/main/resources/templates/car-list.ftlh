<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>

    <h2>Car list</h2>


    <br>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/cars" method="get" class="form-inline">
                <input type="text" name="filter" class="form-control" list="datalistOptions" value="${filter?if_exists}"
                       placeholder="Search by producer"/>
                <datalist id="datalistOptions">
                    <#list cars as car>
                    <option value="${car.producer}">
                        </#list>
                </datalist>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
            Sort
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/cars">All cars</a>
            <a class="dropdown-item" href="/cars?sort=free">Only free cars</a>
            <a class="dropdown-item" href="/cars?sort=price">By price</a>
            <#--            <a class="dropdown-item" href="#">By name</a>-->
        </div>
    </div>

    <div class="card-columns">
        <#list cars as car>
            <div class="col">
                <div class="card my-3">
                    <div class="card-body">
                        <h5 class="card-title">${car.producer} ${car.model}</h5>
                        <p class="card-text"><#if car.employmentStatus == true>Free<#else>Busy</#if></p>
                        <p class="card-text"><small class="text-muted">Price per day: ${car.pricePerDay}$</small></p>
                        <div class="col-12 m-2 p-2">
                            <#if car.employmentStatus == true>
                                <a href="/orders/${currentUserId}/${car.id}" class="btn btn-primary">Order</a>
                            </#if>
                            <#if isAdmin>
                            <a href="/cars/${car.id}" class="btn btn-primary">Edit</a>
                        </div>
                        <div class="col-12 m-2 p-2">
                            <form method="post" action="/cars/delete/${car.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit" class="btn btn-primary mb-2">Delete</button>
                            </form>
                        </div>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <#if isAdmin>
        <div class="col-12 m-2 p-2">
            <button type="submit" class="btn btn-primary" onclick="window.location ='/cars/add'">Add car</button>
        </div>
    </#if>
</@c.page>
