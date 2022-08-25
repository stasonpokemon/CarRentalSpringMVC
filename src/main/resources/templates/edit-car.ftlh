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

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Price per Day: </label>
            <div class="col-sm-6">
                <input type="number" value="${car.pricePerDay}" step="0.01" min="0" name="carPricePerDay"
                       placeholder="0,00"> $
            </div>
        </div>
        <div class="form-group">
            <label class="col=sm-2 col-form-label">Employment status: </label>
            <div class="input-group mb-3">
                <select class="custom-select" id="employmentStatusGroup" name="carEmploymentStatus">
                    <option value="<#if car.employmentStatus == true>FREE<#else>BUSY</#if>">
                        <#if car.employmentStatus == true>FREE<#else>BUSY</#if>
                    </option>
                    <option value="<#if car.employmentStatus == true>BUSY<#else>FREE</#if>">
                        <#if car.employmentStatus == true>BUSY<#else>FREE</#if>
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="carDamageStatusTextarea">Damage status:</label>
            <textarea class="form-control" name="carDamageStatus" id="carDamageStatusTextarea" rows="3" placeholder="Damage status">${car.damageStatus?if_exists}</textarea>
        </div>

        <input type="hidden" value="${car.id}" name="carId">
        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>