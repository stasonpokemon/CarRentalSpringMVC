<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <#if passportIsAvailable == true>
        <dl class="row">
            <dt class="col-sm-3">Name</dt>
            <dd class="col-sm-9">${passport.name}</dd>
            <dt class="col-sm-3">Surname</dt>
            <dd class="col-sm-9">${passport.surname}</dd>
            <dt class="col-sm-3">Patronymic</dt>
            <dd class="col-sm-9">${passport.patronymic}</dd>
            <dt class="col-sm-3">Birthday</dt>
            <dd class="col-sm-9">${passport.birthday}</dd>
            <dt class="col-sm-3">Address</dt>
            <dd class="col-sm-9">${passport.address}</dd>
        </dl>
        <div class="col-12 m-2 p-2">
            <button type="submit" class="btn btn-primary" onclick="window.location ='/user/${currentUserId}/passport/edit'">Edit passport</button>
        </div>
    <#else>
        You need to add your passport for order
        <div class="col-12 m-2 p-2">
            <button type="submit" class="btn btn-primary"
                    onclick="window.location ='/user/${currentUserId}/passport/add'">Add passport
            </button>
        </div>
    </#if>

</@c.page>