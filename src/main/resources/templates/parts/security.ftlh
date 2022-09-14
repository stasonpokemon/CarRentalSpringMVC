<#--Определение переменных внутри шаблона-->
<#assign
<#--    Проверяем, существует ли в контексте необхадимый нам объект-->
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    <#--        Это значение будет содержать нашего пользователя и позволит использовать любые методы из него-->
    currentUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = currentUser.getUsername()
    isAdmin = currentUser.isAdmin()
    currentUserId = currentUser.getId()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    currentUserId = -1
    >
</#if>
