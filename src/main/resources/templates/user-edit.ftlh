<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>Edit user ${user.username}</h2>

    <form action="/user" method="post">
        <input type="text" value="${user.username}" name="userName">
        <#list roles as role>
            <label>
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}
            </label>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Save</button>
    </form>

</@c.page>