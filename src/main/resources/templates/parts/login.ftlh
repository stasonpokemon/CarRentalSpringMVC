<#macro login path isRegisterForm>

    <form action="${path}" method="post">
        <label>User name:</label>
        <input type="text" name="username" placeholder="User name">
        <label>Password:</label>
        <input type="text" name="password" placeholder="Password">

        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">
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
        <button type="submit" class="btn btn-primary">Sign out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>