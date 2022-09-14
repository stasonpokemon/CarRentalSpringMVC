<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <form method="post" action="/user/${user.id}/passport/add">

            <div class="col-md-4">
                <label for="nameId" class="form-label">Name</label>
                <input type="text"
                       class="form-control <#if passport??>${(nameError??)?string('is-invalid','is-valid')}</#if>"
                       id="nameId"
                       name="name" placeholder="Name" value="<#if passport?? >${passport.name}</#if>"
                       aria-describedby="validationNameFeedback"/>
                <#if nameError??>
                    <div id="validationNameFeedback" class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="surnameId" class="form-label">Surname</label>
                <input type="text"
                       class="form-control <#if passport??>${(surnameError??)?string('is-invalid','is-valid')}</#if>"
                       id="surnameId"
                       name="surname" placeholder="Surame" value="<#if passport?? >${passport.surname}</#if>"
                       aria-describedby="validationSurnameFeedback"/>
                <#if surnameError??>
                    <div id="validationSurnameFeedback" class="invalid-feedback">
                        ${surnameError}
                    </div>
                </#if>
            </div>


            <div class="col-md-4">
                <label for="patronymicId" class="form-label">Patronymic</label>
                <input type="text"
                       class="form-control <#if passport??>${(patronymicError??)?string('is-invalid','is-valid')}</#if>"
                       id="patronymicId"
                       name="patronymic" placeholder="Patronymic" value="<#if passport?? >${passport.patronymic}</#if>"
                       aria-describedby="validationPatronymicFeedback"/>
                <#if patronymicError??>
                    <div id="validationPatronymicFeedback" class="invalid-feedback">
                        ${patronymicError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="birthdayId" class="form-label">Birthday</label>
                <input type="date"
                       class="form-control <#if passport??>${(birthdayError??)?string('is-invalid','is-valid')}</#if>"
                       id="birthdayId"
                       name="birthday" placeholder="Birthday" value="<#if passport?? >${passport.birthday}</#if>"
                       aria-describedby="validationBirthdayFeedback"/>
                <#if birthdayError??>
                    <div id="validationBirthdayFeedback" class="invalid-feedback">
                        ${birthdayError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="addressId" class="form-label">Address</label>
                <input type="text"
                       class="form-control <#if passport??>${(addressError??)?string('is-invalid','is-valid')}</#if>"
                       id="addressId"
                       name="address" placeholder="Address" value="<#if passport?? >${passport.address}</#if>"
                       aria-describedby="validationAddressFeedback"/>
                <#if addressError??>
                    <div id="validationAddressFeedback" class="invalid-feedback">
                        ${addressError}
                    </div>
                </#if>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary mt-3">Add</button>
        </form>
    </div>
</@c.page>