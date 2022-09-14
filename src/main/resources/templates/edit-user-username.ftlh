<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>

    <div class="container mt-3" xmlns="http://www.w3.org/1999/html">
        <h2>Edit username</h2>

        <form action="/user/${currentUserId}/profile/edit_username" method="post">

            <div class="col-md-4">
                <div class="form-floating mb-3">
                    <input type="text"
                           class="form-control ${(usernameError??)?string('is-invalid','')}"
                           id="usernameId"
                           name="username" value="${currentUser.username}" placeholder="Enter new username"
                           aria-describedby="validationUsernameFeedback"/>
                    <label class="floatingInput">Enter new username</label>
                    <#if usernameError??>
                        <div id="validationUsernameFeedback" class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary mb-2">Edit username</button>
        </form>

    </div>

</@c.page>