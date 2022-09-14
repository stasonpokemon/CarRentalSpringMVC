<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-3">
        <h2>User list</h2>

        <br>

        <div class="form-row">
            <div class="form-group col-md-6">
                <form action="/user" method="get" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter?if_exists}"
                           placeholder="Search by username"/>
                    <button type="submit" class="btn btn-primary mt-3 mb-3">Search</button>
                </form>
            </div>
        </div>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Roles</th>
                <th scope="col">Edit</th>
                <th scope="col">Activity</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role> ${role} <#sep>, </#list></td>
                    <td><a href="/user/${user.id}">Edit</a></td>
                    <td>
<#--                        <div class="btn-group" role="group" aria-label="Basic mixed styles example">-->
                            <#if user.active == true>
                                <form method="post" action="/user/${user.id}/block">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-danger">Block</button>
                                </form>
                            <#else>
                                <form method="post" action="/user/${user.id}/unlock">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-success">Unlock</button>
                                </form>
                            </#if>
<#--                        </div>-->
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>