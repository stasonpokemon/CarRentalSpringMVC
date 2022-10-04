<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div class="container mt-3">
        <div class="text-center">
            <div class="row justify-content-md-center">
                <div class="col-md-auto">
                    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
                        <#if RequestParameters.error??>
                            <div class="alert alert-danger" role="alert">
<#--                                Плохое решение проблемы!!! Нужно как-то по-другому заменить стандартные сообщения ошибок Session.SPRING_SECURITY_LAST_EXCEPTION.message-->
                                <#if Session.SPRING_SECURITY_LAST_EXCEPTION.message == "User is disabled">
                                    User is not found
                                </#if>
                                <#if Session.SPRING_SECURITY_LAST_EXCEPTION.message == "Bad credentials">
                                    Invalid password
                                </#if>
<#--                                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}-->
                            </div>
                        </#if>
                    </#if>

                    <#if message??>
                        <div class="alert alert-${messageType}" role="alert">
                            ${message}
                        </div>
                    </#if>

                    <@l.login "/login" false/>
                </div>
            </div>
        </div>
    </div>
</@c.page>