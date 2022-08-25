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

        <div class="form-group">
            <label class="col=sm-2 col-form-label">Price per Day: </label>
            <div class="col-sm-6">
                <input type="number" step="0.01" min="0" name="carPricePerDay"
                       placeholder="0,00"> $
            </div>
        </div>
        <div class="form-group">
            <label class="col=sm-2 col-form-label">Employment status: </label>
            <div class="input-group mb-3">
                <select class="custom-select" id="employmentStatusGroup" name="carEmploymentStatus">
                        <option value="FREE" selected>FREE</option>
                        <option value="BUSY">BUSY</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="carDamageStatusTextarea">Damage status:</label>
            <textarea class="form-control" name="carDamageStatus" id="carDamageStatusTextarea" rows="3" placeholder="Damage status"></textarea>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}">

        <button type="submit" class="btn btn-primary">Add</button>
    </form>
</@c.page>