<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container">
        <h2>Edit user (${user.username})</h2>

        <form action="/user" method="post">


            <div class="col-md-3 mt-3 mb-3">
                <label for="userNameId" class="form-label">User name: </label>
                <input type="text"
                       class="form-control ${(userError??)?string('is-invalid','')}"
                       id="userNameId"
                       name="username" placeholder="Username" value="<#if user??>${user.username}</#if>"
                       aria-describedby="validationUsernameFeedback"/>
                <#if userError??>
                    <div id="validationUsernameFeedback" class="invalid-feedback">
                        ${userError}
                    </div>
                </#if>
            </div>

            <div class="list-group mx-0 w-auto">
                <#list roles as role>
                    <label class="list-group-item d-flex gap-2">
                        <input class="form-check-input flex-shrink-0" type="checkbox"
                               name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>
                        <span>
                       ${role}
                    </span>
                    </label>
                </#list>
            </div>
            <input type="hidden" value="${user.id}" name="id">
            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <button type="submit" class="btn btn-primary mt-3">Save</button>
        </form>
    </div>

</@c.page>