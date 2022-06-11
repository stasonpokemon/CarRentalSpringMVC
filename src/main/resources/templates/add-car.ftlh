<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>Add car</h2>

    <form action="/cars/add" method="post">
        <div class="form-group">
            <label class="col=sm-2 col-form-label">Producer: </label>
            <div class="col-sm-6">
                <input type="text" name="carProducer" placeholder="Producer">
            </div>
        </div>

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Model: </label>
            <div class="col-sm-6">
                <input type="text" name="carModel" placeholder="Model">
            </div>
        </div>

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Release date: </label>
            <div class="col-sm-6">
                <input type="text" name="carReleaseDate" placeholder="Release date">
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <button type="submit" class="btn btn-primary">Add</button>
    </form>
</@c.page>