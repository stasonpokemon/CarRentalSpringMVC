<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container">
        <h2>Add car</h2>
        <form action="/cars/add" method="post">

            <div class="col-md-4">
                <label for="producerId" class="form-label">Producer</label>
                <input type="text"
                       class="form-control <#if car??>${(producerError??)?string('is-invalid','is-valid')}</#if>"
                       id="producerId"
                       name="producer" placeholder="Producer" value="<#if car??>${car.producer}</#if>"
                       aria-describedby="validationProducerFeedback"/>
                <#if producerError??>
                    <div id="validationProducerFeedback" class="invalid-feedback">
                        ${producerError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="modelId" class="form-label">Model: </label>
                <input type="text"
                       class="form-control <#if car??>${(modelError??)?string('is-invalid','is-valid')}</#if>"
                       id="modelId"
                       name="model" placeholder="Model" value="<#if car??>${car.model}</#if>"
                       aria-describedby="validationModelFeedback"/>
                <#if modelError??>
                    <div id="validationModelFeedback" class="invalid-feedback">
                        ${modelError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="releaseDateId" class="form-label">Release date: </label>
                <input type="text"
                       class="form-control <#if car??>${(releaseDateError??)?string('is-invalid','is-valid')}</#if>"
                       id="releaseDateId"
                       name="releaseDate" placeholder="Release date" value="<#if car??>${car.releaseDate}</#if>"
                       aria-describedby="validationReleaseDateFeedback">
                <#if releaseDateError??>
                    <div id="validationReleaseDateFeedback" class="invalid-feedback">
                        ${releaseDateError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="pricePerDayId" class="form-label">Price per Day($): </label>
                <input type="number"
                       class="form-control <#if car??>${(pricePerDayError??)?string('is-invalid','is-valid')}</#if>"
                       step="0.01"
                       min="0" id="pricePerDayId"
                       name="pricePerDay" placeholder="0,00"
                       value="<#if car?? && car.pricePerDay??>${car.pricePerDay}</#if>"
                       aria-describedby="validationPricePerDayFeedback">
                <#if pricePerDayError??>
                    <div id="validationPricePerDayFeedback" class="invalid-feedback">
                        ${pricePerDayError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4">
                <label for="employmentStatusId" class="form-label">Employment status: </label>
                <select class="form-select <#if car??>${(employmentStatusError??)?string('is-invalid','is-valid')}</#if>"
                        id="employmentStatusId" name="employmentStatus"
                        aria-label="Default select example" aria-describedby="validationEmploymentStatusFeedback">
                    <option <#if car?? && car.employmentStatus = true>selected</#if> value="true">FREE</option>
                    <option <#if car?? && car.employmentStatus = false>selected</#if> value="false">BUSY</option>
                </select>
                <#if employmentStatusError??>
                    <div id="validationEmploymentStatusFeedback" class="invalid-feedback">
                        ${employmentStatusError}
                    </div>
                </#if>

            </div>


            <div class="col-md-4">
                <label for="imageLinkId" class="form-label">Link to car photo:</label>
                <input type="img" name="imageLink"
                       class="form-control <#if car??>${(imageLinkError??)?string("is-invalid","is-valid")}</#if>"
                       id="imageLinkId"
                       placeholder="http://localhost:8080/img/img-1.jpg" value="<#if car??>${car.imageLink}</#if>"
                       aria-describedby="validationImageLinkFeedback">
                <#if imageLinkError??>
                    <div id="validationImageLinkFeedback" class="invalid-feedback">
                        ${imageLinkError}
                    </div>
                </#if>
            </div>

            <div class="col-md-4 mb-3">
                <label for="DamageStatusId" class="form-label">Damage status:</label>
                <textarea class="form-control <#if car??>${(damageStatusError??)?string('is-invalid','is-valid')}</#if>"
                          name="damageStatus"
                          id="DamageStatusId" rows="3"
                          placeholder="Damage status"
                          aria-describedby="validationDamageStatusFeedback"><#if car??>${car.damageStatus}</#if>
                </textarea>
            </div>
            <#if damageStatusError??>
                <div id="validationDamageStatusFeedback" class="invalid-feedback">${damageStatusError}
                </div>
            </#if>

            <input type="hidden" value="-1" name="carId">
            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
</@c.page>