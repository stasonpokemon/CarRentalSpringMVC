<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-3">
        <h2>Edit user (${user.username})</h2>

        <form action="/user" method="post">


            <div class="col-md-3 mt-3 mb-3">
                <label for="userNameId" class="form-label">User name: </label>
                <input type="text"
                       class="form-control ${(usernameError??)?string('is-invalid','')}"
                       id="userNameId"
                       name="username" placeholder="Username" value="<#if username??>${username}<#else>${user.username}</#if>"
                       aria-describedby="validationUsernameFeedback"/>
                <#if usernameError??>
                    <div id="validationUsernameFeedback" class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>

            <div class="form-check">
                <#list roles as role>

                    <input class="form-check-input ${(rolesError??)?string('is-invalid','')}" type="checkbox"
                           name="${role}" value="" id="${role}Id"
                           aria-describedby="invalidCheck${role}Feedback" <#if rolesError??> <#else>${user.roles?seq_contains(role)?string("checked","")}</#if>>
                    <label class="form-check-label" for="${role}Id">
                        ${role}
                    </label>
                    <br>

                    <#if rolesError??>
                        <div id="invalidCheck${role}Feedback" class="invalid-feedback">
                            ${rolesError}
                        </div>
                    </#if>
                </#list>
            </div>

            <#--            <div class="list-group mx-0 w-auto">-->
            <#--                <#list roles as role>-->
            <#--                    <label class="list-group-item d-flex gap-2">-->
            <#--                        <input class="form-check-input flex-shrink-0 " type="checkbox"-->
            <#--                               name="${role}" <#if rolesError??> <#else>${user.roles?seq_contains(role)?string("checked","")}</#if>>-->
            <#--                        <span>-->
            <#--                       ${role}-->
            <#--                    </span>-->
            <#--                    </label>-->
            <#--                </#list>-->
            <#--            </div>-->

            <input type="hidden" value="${user.id}" name="id">
            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <button type="submit" class="btn btn-primary mt-3">Save</button>
        </form>
    </div>

</@c.page>