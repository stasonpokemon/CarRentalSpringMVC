<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>

    <div class="container mt-3" xmlns="http://www.w3.org/1999/html">
        <h2>Edit password</h2>

        <form action="/user/${currentUserId}/profile/edit_password" method="post">

            <div class="col-md-4">
                <div class="form-floating mb-3">
                    <input type="text"
                           class="form-control ${(currentPasswordError??)?string('is-invalid','')}"
                           id="currentPasswordId"
                           name="currentPassword" placeholder="Enter current password"
                           aria-describedby="validationCurrentPasswordFeedback"/>
                    <label class="floatingInput">Enter current password</label>
                    <#if currentPasswordError??>
                        <div id="validationCurrentPasswordFeedback" class="invalid-feedback">
                            ${currentPasswordError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-floating mb-3">
                    <input type="text"
                           class="form-control ${(passwordError??)?string('is-invalid','')}"
                           id="newPasswordId"
                           name="newPassword" placeholder="Enter new password"
                           aria-describedby="validationNewPasswordFeedback"/>
                    <label class="floatingInput">Enter new password</label>
                    <#if passwordError??>
                        <div id="validationNewPasswordFeedback" class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-floating mb-3">
                    <input type="text"
                           class="form-control ${(passwordError??)?string('is-invalid','')}"
                           id="passwordConfirmationId"
                           name="passwordConfirmation" placeholder="Enter password confirmation"
                           aria-describedby="validationPasswordConfirmationFeedback"/>
                    <label class="floatingInput">Enter password confirmation</label>
                    <#if passwordError??>
                        <div id="validationPasswordConfirmationFeedback" class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary mb-2">Edit password</button>
        </form>

    </div>

</@c.page>