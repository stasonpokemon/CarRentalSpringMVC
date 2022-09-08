<#import "parts/common.ftlh" as c>
<@c.page>
    <style>
        .my-carousel {
            padding: 0;
        }

        .my-carousel .carousel-item {
            display: flex;
            max-height: 500px;
        }

        .my-carousel .carousel-item img{
            object-fit: cover;
        }
    </style>

    <div class="container-fluid my-carousel">
        <div id="carouselExampleControls" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div class="carousel-inner">
                <#list cars as car>

                <div class="carousel-item <#if car_index = 0>active</#if>">
                    <img src="${car.imageLink}"
                         class="d-block w-100" alt="...">
                </div>
                </#list>

            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
</@c.page>

