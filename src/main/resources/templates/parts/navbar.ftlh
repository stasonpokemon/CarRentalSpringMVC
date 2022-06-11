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

        </ul>


        <#if name != "unknown">
            <div class="navbar-text mr-3">${name}</div></#if>
        <@l.loigout/>


    </div>
</nav>
