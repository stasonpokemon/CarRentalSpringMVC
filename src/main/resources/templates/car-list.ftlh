<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>

    <style>
        .search-container {
            padding: 0;
        }
    </style>

    <div class="container mt-3">
        <h2>Car list</h2>

        <br>

        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/cars" method="get" class="form-inline">
                    <div class="container search-container">
                        <div class="row align-items-start">
                            <div class="col">
                                <input type="text" name="filter" class="form-control" list="datalistOptions"
                                       value="${filter?if_exists}"
                                       placeholder="Search by producer"/>
                                <datalist id="datalistOptions">
                                    <#list cars as car>
                                        <option value="${car.producer}"/>
                                    </#list>
                                </datalist>
                            </div>
                            <div class="col">
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <#if isAdmin>
            <div class="col-12 mt-3 mb-3">
                <button type="submit" class="btn btn-primary" onclick="window.location ='/cars/add'">Add car</button>
            </div>
        </#if>

        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle mb-3" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                Sort
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/cars">All cars</a></li>
                <li><a class="dropdown-item" href="/cars?sort=free">Only free cars</a></li>
                <li><a class="dropdown-item" href="/cars?sort=price_min">By price from min</a></li>
                <li><a class="dropdown-item" href="/cars?sort=price_max">By price from max</a></li>
                <#--            <a class="dropdown-item" href="#">By name</a>-->
            </ul>
        </div>


        <div class="row row-cols-1 row-cols-md-3 g-4">
            <#list cars as car>
                <div class="col">
                    <div class="card my-car">
                        <img width="200" height="235" src="${car.imageLink}" class="card-img-top" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${car.producer} ${car.model}</h5>
                            <p class="card-text"><#if car.employmentStatus == true>Free<#else>Busy</#if></p>
                            <p class="card-text"><small class="text-muted">Price per day: ${car.pricePerDay}$</small>
                            </p>
                            <div class="hstack gap-3">
                                <#if car.employmentStatus == true>
                                    <button type="button" class="btn btn-primary mb-2"
                                            onclick="window.location='/orders/${currentUserId}/${car.id}'">Order
                                    </button>
                                </#if>
                                <#if isAdmin>
                                    <button type="button" class="btn btn-primary mb-2"
                                            onclick="window.location='/cars/${car.id}'">Edit
                                    </button>
                                    <form method="post" action="/cars/delete/${car.id}">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button type="submit" class="btn btn-primary mb-2">Delete</button>
                                    </form>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>


    </div>
</@c.page>
