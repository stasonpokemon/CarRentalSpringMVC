<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container">
        <h2>Return processing:</h2>

        <div class="list-group">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Car</th>
                    <th scope="col">User</th>
                    <th scope="col">Cost</th>
                    <th scope="col">Order status</th>
                    <th scope="col">Order date</th>
                    <th scope="col">Rental period</th>
                    <th scope="col">Damage</th>
                </tr>
                </thead>
                <tbody>
                <#list orders as order>
                    <tr>
                        <td>${order.car.producer} ${order.car.model}</td>
                        <td>${order.user.username}</td>
                        <td>${order.price}</td>
                        <td>${order.orderStatus}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.rentalPeriod}</td>
                        <td>
                            <div class="btn-group" role="group" aria-label="Basic mixed styles example">

                                <#--                                <button type="submit" class="btn btn-danger me-2">With</button>-->

                                <button type="button" class="btn btn-primary btn-danger me-2" data-bs-toggle="modal"
                                        data-bs-target="#WithDamageModal" data-bs-whatever="@mdo">With
                                </button>
                                <form method="post" action="/orders/return-processing/with">
                                    <div class="modal fade" id="WithDamageModal" tabindex="-1"
                                         aria-labelledby="WithDamageModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="WithDamageModalLabel">Refund with
                                                        damage</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form>
                                                        <div class="mb-3">
                                                            <label for="damage-description" class="col-form-label">Damage
                                                                description:</label>
                                                            <textarea class="form-control" name="damageDescription"
                                                                      id="damage-description"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="repair-cost" class="col-form-label">Repair
                                                                cost:</label>
                                                            <input type="text" class="form-control" name="repairCost"
                                                                   id="repair-cost">
                                                        </div>

                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">
                                                        Cancel
                                                    </button>
                                                    <input type="hidden" name="orderId" value="${order.id}">
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                    <button type="submit" class="btn btn-primary">Confirm</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form method="post" action="/orders/return-processing/without">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-success">Without</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>