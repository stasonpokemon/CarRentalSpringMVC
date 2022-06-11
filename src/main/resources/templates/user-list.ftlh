<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>User list</h2>

    <a href="/home">Home page</a>
    <br>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/user" method="get" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?if_exists}"
                       placeholder="Search by username"/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Roles</th>
            <th scope="col">Edit</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role> ${role} <#sep>, </#list></td>
                <td><a href="/user/${user.id}">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>