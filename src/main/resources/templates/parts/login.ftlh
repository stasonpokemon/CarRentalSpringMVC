<#include "security.ftlh">
<#macro login path isRegisterForm>

    <form action="${path}" method="post">

        <div class="form-group">
            <label class="col=sm-2 col-form-label">User name: </label>
            <div class="col-sm-6">
                <input type="text" name="username" placeholder="User name">
            </div>
        </div>

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Password: </label>
            <div class="col-sm-6">
                <input type="text" name="password" placeholder="Password">
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <button type="submit" class="btn btn-primary">
            <#if isRegisterForm>Create<#else>Sign in</#if>
        </button>

        <br>

        <#if !isRegisterForm>
            <a href="/registration">Registration</a>
        </#if>
    </form>

</#macro>

<#macro loigout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-primary"><#if user??>Sign out<#else>Sign in</#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>