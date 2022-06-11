<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>Edit car(${car.producer} ${car.model})</h2>

    <form action="/cars" method="post">
        <div class="form-group">
            <label class="col=sm-2 col-form-label">Producer: </label>
            <div class="col-sm-6">
                <input type="text" value="${car.producer}" name="carProducer" placeholder="Producer">
            </div>
        </div>

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Model: </label>
            <div class="col-sm-6">
                <input type="text" value="${car.model}" name="carModel" placeholder="Model">
            </div>
        </div>

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Release date: </label>
            <div class="col-sm-6">
                <input type="text" value="${car.releaseDate}" name="carReleaseDate" placeholder="Release date">
            </div>
        </div>

        <input type="hidden" value="${car.id}" name="carId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>