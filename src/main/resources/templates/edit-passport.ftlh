<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <form method="post" action="/user/${currentUserId}/passport/edit">
        <div class="mb-3">
            <label for="Name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="Name" placeholder="Name" value="${passport.name}">
        </div>
        <div class="mb-3">
            <label for="Surname" class="form-label">Surname</label>
            <input type="text" class="form-control" name="surname" id="Surname" placeholder="Surname" value="${passport.surname}">
        </div>
        <div class="mb-3">
            <label for="Patronymic" class="form-label">Patronymic</label>
            <input type="text" class="form-control" name="patronymic" id="Patronymic" placeholder="Patronymic" value="${passport.patronymic}">
        </div>
        <div class="mb-3">
            <label for="Birthday" class="form-label">Birthday</label>
            <input type="text" class="form-control" name="birthday" id="Birthday" placeholder="Birthday" value="${passport.patronymic}">
        </div>
        <div class="mb-3">
            <label for="Address" class="form-label">Address</label>
            <input type="text" class="form-control" name="address" id="Address" placeholder="Address" value="${passport.address}">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Edit</button>
    </form>
</@c.page>