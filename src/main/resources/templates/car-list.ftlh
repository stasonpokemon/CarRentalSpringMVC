<#import "parts/common.ftlh" as c>
<@c.page>
<h2>Car list</h2>

<a href="/home">Home page</a>

<br>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Producer</th>
        <th scope="col">Model</th>
        <th scope="col">Release date</th>
    </tr>
    </thead>
    <tbody>
    <#list cars as car>
        <tr>
            <td>${car.producer}</td>
            <td>${car.model}</td>
            <td>${car.releaseDate}</td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
