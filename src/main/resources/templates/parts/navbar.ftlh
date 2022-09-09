<#include "security.ftlh">
<#import "login.ftlh" as l>


<nav class="navbar navbar-expand-lg bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand ms-3" href="<#if name != "unknown">/home<#else>/</#if>">Car rental</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <#if name != "unknown">
                        <a class="nav-link" href="/home">Home</a>
                    <#else>
                        <a class="nav-link" href="/">Home</a>
                    </#if>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cars">Car list</a>
                </li>

                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/user">User list</a>
                    </li>
                </#if>
                <#if name != "unknown">
                    <li class="nav-item">
                        <a class="nav-link" href="/user/${currentUserId}/passport">My passport</a>
                    </li>


                    <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Orders
                                </a>
                                <ul class="dropdown-menu dropdown-menu">
                                    <#if isAdmin>
                                        <li><a class="dropdown-item" href="/orders/list">User orders</a></li>
                                        <li>
                                                <a class="dropdown-item" href="/orders/rental-applications">Rental
                                                    applications
                                                </a>
                                        </li>
                                        <li><a class="dropdown-item" href="/orders/return-processing">Return
                                                processing</a></li>
                                        <li><a class="dropdown-item" href="/orders/refunds/list">All returns</a></li>
                                    </#if>
                                    <li><a class="dropdown-item" href="/user/${currentUserId}/orders/list">My orders</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </#if>


            </ul>


            <#if name != "unknown">
                <div class="navbar-text me-3">${name}</div>
            </#if>
            <@l.loigout/>

        </div>
    </div>
</nav>
