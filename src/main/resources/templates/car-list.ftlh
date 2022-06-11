<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <h2>Car list</h2>

    <a href="/home">Home page</a>

    <br>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/cars" method="get" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?if_exists}"
                       placeholder="Search by producer"/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Producer</th>
            <th scope="col">Model</th>
            <th scope="col">Release date</th>
            <#if isAdmin>
                <th scope="col">Edit</th></#if>
        </tr>
        </thead>
        <tbody>
        <#list cars as car>
            <tr>
                <td>${car.producer}</td>
                <td>${car.model}</td>
                <td>${car.releaseDate}</td>
                <#if isAdmin>
                    <td><a href="/cars/${car.id}">Edit</a></td></#if>
            </tr>
        </#list>
        </tbody>
    </table>

    <#if isAdmin>
        <button type="submit" class="btn btn-primary" onclick="window.location ='/cars/add'">Add car</button></#if>
</@c.page>
