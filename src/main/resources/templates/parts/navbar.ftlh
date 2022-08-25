<#include "security.ftlh">
<#import "login.ftlh" as l>


<nav class="navbar navbar-expand-lg navbar-light bg-light" xmlns="http://www.w3.org/1999/html">
    <#if name != "unknown">
        <a class="navbar-brand" href="/home">Car rental</a>
    <#else>
        <a class="navbar-brand" href="/">Car rental</a>
    </#if>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
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

                <li class="nav-item">
                    <a class="nav-link" href="/user/orders/list">User orders</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/user/${currentUserId}/orders/list">My Orders</a>
                </li>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/orders/list">All Orders</a>
                    </li>
                </#if>


            <#--                <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">-->
            <#--                    <ul class="navbar-nav">-->
            <#--                        <li class="nav-item dropdown">-->
            <#--                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"-->
            <#--                               aria-expanded="false">-->
            <#--                                Orders-->
            <#--                            </a>-->
            <#--                            <ul class="dropdown-menu dropdown-menu-dark">-->
            <#--                                <#if isAdmin>-->
            <#--                                    <li><a class="dropdown-item" href="#">User orders</a></li>-->
            <#--                                    <li><a class="dropdown-item" href="#">Applications to order</a></li>-->
            <#--                                    <li><a class="dropdown-item" href="#">Return processing</a></li>-->
            <#--                                    <li><a class="dropdown-item" href="#">All returns</a></li>-->
            <#--                                </#if>-->
            <#--                                <li><a class="dropdown-item" href="#">My orders</a></li>-->
            <#--                            </ul>-->
            <#--                        </li>-->
            <#--                    </ul>-->
            <#--                </div>-->
            </#if>


        </ul>


        <#if name != "unknown">
            <div class="navbar-text mr-3">${name}</div>
        </#if>
        <@l.loigout/>


    </div>
</nav>
