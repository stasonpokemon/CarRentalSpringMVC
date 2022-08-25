<#include "security.ftlh">
<#macro login path isRegisterForm>

    <form action="${path}" method="post">

        <div class="mb-3">
            <label for="usernameId" class="form-label">User name</label>
            <input type="text" class="form-control" id="usernameId" name="username" placeholder="User name">
        </div>

        <div class="form-group">
            <label for="passwordId" class="form-label">Password</label>
            <input type="text" class="form-control" id="passwordId" name="password" placeholder="Password">
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <div class="mt-3">
            <button type="submit" class="btn btn-primary">
                <#if isRegisterForm>Create<#else>Sign in</#if>
            </button>
        </div>

        <br>

        <#if !isRegisterForm>
            <a href="/registration">Registration</a>
        </#if>
    </form>

</#macro>

<#macro loigout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-primary me-3"><#if user??>Sign out<#else>Sign in</#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>