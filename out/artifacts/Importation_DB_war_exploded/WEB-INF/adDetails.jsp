<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="return-button">
        <a href="<c:url value="/accueil"/>">
            <button type="button" class="btn btn-warning"><i class="fa fa-chevron-left"></i> Retour
            </button>
        </a>
    </div>
    <table class='table table-hover table-responsive'>
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
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${ads.carsByIdCars.releaseYear}"/></td>
            <td>${ads.carsByIdCars.kilometer} KM</td>
            <td><fmt:formatNumber type="number" value="${ads.carsByIdCars.horsePower}" maxFractionDigits="3"/> CV</td>
            <td>${ads.carsByIdCars.enumFuel}</td>
            <td>${ads.typesAds}
                <c:if test="${ads.typesAds == 'LOCATIONCD'}"><br \>Du 25/08/2020 au 30/08/2020</c:if>
            </td>
        </tr>
        </tbody>
    </table>
    <c:if test="${not empty sessionScope.UserEntity}">
        <form name="formCar" id="formCar" action="basket" method="post" class="">
            <input name="idUsers" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
            <input name="idAds" id="${ads.id}" type="hidden" value="${ads.id}"/>

            <c:choose>
                <c:when test="${ads.typesAds == 'LOCATIONCD'}">
                    <input name="locationDays" id="6" type="hidden" value="6"/>
                    <input name="dateStart" id="dateStart" type="hidden" value="25/08/2020"/>
                    <input name="dateEnd" id="dateEnd" type="hidden" value="30/08/2020"/>
                </c:when>
                <c:when test="${ads.typesAds == 'VENTE'}">
                    <input name="locationDays" id="1" type="hidden" value="1"/>
                </c:when>
            </c:choose>
            <br \><br \>
            <input name="send" id="boutonSubmit" type="submit" value="Ajouter au panier" class="btn btn-info"/>
        </form>
    </c:if>
</div>


<jsp:include page="footer.jsp"/>