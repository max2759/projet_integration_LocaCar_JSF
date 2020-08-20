<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <table class='table table-hover'>
        <thead>
        <tr>
            <th>Titre de l'annonce</th>
            <th>Marque</th>
            <th>Modèle</th>
            <th>Types de voiture</th>
            <th>Couleur</th>
            <th>Prix</th>
            <th>Année de fabrication</th>
            <th>Kilométrage</th>
            <th>Chevaux</th>
            <th>Carburant</th>
            <th>Type d'annonce</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${ads.label}</td>
            <td>${ads.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
            <td>${ads.carsByIdCars.modelsByIdModels.label}</td>
            <td>${ads.carsByIdCars.carTypesByIdCarTypes.label}</td>
            <td> ${ads.carsByIdCars.color}</td>
            <td><fmt:formatNumber value="${ads.price}" type="currency"/></td>
            <td><fmt:formatDate pattern="dd-MM-YYYY" value="${ads.carsByIdCars.releaseYear}"/></td>
            <td>${ads.carsByIdCars.kilometer} KM</td>
            <td><fmt:formatNumber type="number" value="${ads.carsByIdCars.horsePower}" maxFractionDigits="3"/> CV</td>
            <td>${ads.carsByIdCars.enumFuel}</td>
            <td>${ads.typesAds}</td>
        </tr>
        </tbody>
    </table>
    <c:if test="${not empty sessionScope.UserEntity}">
        <form name="formCar" id="formCar" action="basket" method="post" class="">
            <input name="idUsers" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
            <input name="idAds" id="${ads.id}" type="hidden" value="${ads.id}"/>
            <input name="locationDays" id="1" type="hidden" value="1"/>
            <br \><br \>
            <input name="send" id="boutonSubmit" type="submit" value="Ajouter au panier" class="btn btn-info"/>
        </form>
    </c:if>
</div>

<jsp:include page="footer.jsp"/>