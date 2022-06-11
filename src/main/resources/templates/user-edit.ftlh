<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>Edit user ${user.username}</h2>

    <form action="/user" method="post">
        <div class="form-group">
            <label class="col=sm-2 col-form-label">User name: </label>
            <div class="col-sm-6">
                <input type="text" value="${user.username}" name="userName">
            </div>
        </div>


        <#list roles as role>
            <label>
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}
            </label>
        </#list>

        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <button type="submit" class="btn btn-primary">Save</button>
    </form>

</@c.page>