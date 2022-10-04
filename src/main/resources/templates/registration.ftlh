<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="container mt-3">
        <div class="text-center">
            <div class="row justify-content-md-center">
                <div class="col-md-auto">
                    <@l.login "/registration" true/>
                </div>
            </div>
        </div>
    </div>
</@c.page>