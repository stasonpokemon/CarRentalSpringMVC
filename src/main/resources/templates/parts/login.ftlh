<#include "security.ftlh">
<#macro login path isRegisterForm>
    <div class="container mt-3">
        <div class="text-center">
            <div class="row justify-content-md-center">
                <div class="col-md-auto">
                    <form action="${path}" method="post">

                        <h1 class="h3 mb-3 fw-normal"><#if !isRegisterForm>Please sign in<#else>Registration</#if></h1>


                        <div class="form-floating mb-3">
                            <input type="text"
                                   class="form-control <#if currentUser??>${(usernameError??)?string('is-invalid','is-valid')}</#if>"
                                   id="usernameId" name="username" placeholder="User name"
                                   value="<#if currentUser??>${currentUser.username}</#if>"
                                   aria-describedby="validationUsernameFeedback">
                            <label class="floatingInput">User name</label>
                            <#if usernameError??>
                                <div id="validationUsernameFeedback" class="invalid-feedback">
                                    ${usernameError}
                                </div>
                            </#if>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="text"
                                   class="form-control ${(passwordError??)?string('is-invalid',' ')}"
                                   id="passwordId" name="password" placeholder="Password"
                                   aria-describedby="validationPasswordFeedback">
                            <label class="form-label">Password</label>
                            <#if passwordError??>
                                <div id="validationPasswordFeedback" class="invalid-feedback">
                                    ${passwordError}
                                </div>
                            </#if>
                        </div>


                        <#if isRegisterForm>
                            <div class="form-floating mb-3">
                                <input type="text"
                                       class="form-control ${(passwordConfirmationError??)?string('is-invalid','')}"
                                       id="passwordConfirmationId"
                                       name="passwordConfirmation" placeholder="Password confirmation"
                                       aria-describedby="validationPasswordConfirmationFeedback">
                                <label class="form-label">Password confirmation</label>
                                <#if passwordConfirmationError??>
                                    <div id="validationPasswordConfirmationFeedback" class="invalid-feedback">
                                        ${passwordConfirmationError}
                                    </div>
                                </#if>
                            </div>


                            <div class="form-floating mb-3">
                                <input type="email"
                                       class="form-control <#if currentUser??>${(emailError??)?string('is-invalid','is-valid')}</#if>"
                                       id="emailId"
                                       name="email" placeholder="Email" aria-describedby="validationEmailFeedback"
                                       value="<#if currentUser??>${currentUser.email}</#if>">
                                <label class="form-label">Email</label>
                                <#if emailError??>
                                    <div id="validationEmailFeedback" class="invalid-feedback">
                                        ${emailError}
                                    </div>
                                </#if>
                            </div>

                        </#if>

                        <input type="hidden" name="_csrf" value="${_csrf.token}">

                        <div class="mt-3">
                            <button class="w-100 btn btn-lg btn-primary" type="submit">
                                <#if isRegisterForm>Create<#else>Sign in</#if>
                            </button>
                        </div>

                        <br>

                        <#if !isRegisterForm>
                            <a href="/registration">Registration</a>
                        </#if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro loigout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-primary me-3"><#if currentUser??>Sign out<#else>Sign in</#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>