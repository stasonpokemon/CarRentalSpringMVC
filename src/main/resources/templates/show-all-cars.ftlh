<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>

    <style>
        .search-container {
            padding: 0;
        }

        .card-shadow {
            background: rgb(99, 227, 222); /* Цвет фона */
            padding: 10px;
            border-radius: 1em;
        }

        .card-shadow img {
            border-radius: 1em;
        }

        .card-shadow:hover {
            background-color: #00fff6;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* Параметры тени */
            transition-duration: revert;
        }
    </style>

    <div class="container mt-3 mb-3">
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
                                    <#list allCars as car>
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
            <div class="col-12 mt-3">
                <button type="submit" class="btn btn-primary" onclick="window.location ='/cars/add'">Add car</button>
            </div>
        </#if>

        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle mb-3 mt-3" type="button" data-bs-toggle="dropdown"
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


        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <#list cars as car>
                <div class="col">
                    <div class="card my-car card-shadow">
                        <img width="200" height="235" src="${car.imageLink}" class="card-img-top" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${car.producer} ${car.model}</h5>
                            <p class="card-text"><#if car.employmentStatus == true>Free<#else>Busy</#if></p>
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <#if car.employmentStatus == true>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-sm btn-outline-secondary"
                                                <#if name != 'unknown'>onclick="window.location='/orders/create/${car.id}'"
                                                <#else>data-bs-toggle="modal"
                                                data-bs-target="#orderModal${car.id}"</#if>>
                                            Order
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="orderModal${car.id}" tabindex="-1"
                                             aria-labelledby="orderModalLabel${car.id}" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="orderModalLabel${car.id}">Order</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        You must be logged in to order
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Close
                                                        </button>
                                                        <button type="button" class="btn btn-primary"
                                                                onclick="window.location='/orders/create/${car.id}'">
                                                            Sign in
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </#if>

                                    <#if isAdmin>
                                        <button type="button" class="btn btn-sm btn-outline-secondary"
                                                onclick="window.location='/cars/${car.id}'">Edit
                                        </button>

                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-sm btn-outline-secondary"
                                                data-bs-toggle="modal" data-bs-target="#deleteModal${car.id}">
                                            Delete
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="deleteModal${car.id}" tabindex="-1"
                                             aria-labelledby="deleteModalLabel${car.id}" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="deleteModalLabel${car.id}">
                                                            Delete</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">Are you sure you want to delete the
                                                        car ${car.producer} ${car.model}?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Close
                                                        </button>
                                                        <form method="post" action="/cars/delete/${car.id}">
                                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                            <button type="submit" class="btn btn-primary">
                                                                Delete
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </#if>
                                </div>
                                <small class="text-muted">Price per day: ${car.pricePerDay}$</small>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

    </div>
</@c.page>
