<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-3">
        <div class="text-center">
            <div class="row justify-content-md-center">
                <div class="col-md-auto">

                    <div class="container mt-3">
                        <h2>Profile</h2>
                        <dl class="row">
                            <dt class="col-sm-3">Username</dt>
                            <dd class="col-sm-9">${user.username}</dd>
                            <dt class="col-sm-3">Email</dt>
                            <dd class="col-sm-9">${user.email}</dd>
                        </dl>
                        <button type="button" class="btn btn-primary mb-2"
                                onclick="window.location ='/user/${user.id}/passport'">Show passport
                        </button>
                        <#if user.id == currentUserId>
                            <button type="button" class="btn btn-primary mb-2 ms-3"
                                    onclick="window.location ='/user/${currentUser.id}/profile/edit_password'">Edit
                                password
                            </button>
                            <button type="button" class="btn btn-primary mb-2 ms-3"
                                    onclick="window.location='/user/${currentUser.id}/profile/edit_username'">Edit
                                username
                            </button>
                        </#if>

                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>