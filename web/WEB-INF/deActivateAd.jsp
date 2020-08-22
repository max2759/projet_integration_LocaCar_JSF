<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<h2>Annonce</h2>
<table class='table table-hover table-responsive'>
    <thead>
    <tr>
        <th>Image</th>
        <th>Titre de l'annonce</th>
        <th>Marque</th>
        <th>Modèle</th>
        <th>Types de voiture</th>
        <th>Couleur</th>
        <th>Prix</th>
        <th>Année de fabrication</th>
        <th>Kilométrage</th>
        <th>Chevaux</th>
        <th>Date de début</th>
        <th>Date de fin</th>
        <th>Type d'annonce</th>
        <th>Réactiver l'annonce</th>
        <th>Modifier l'annonce</th>
        <th>Supprimer</th>
    </tr>
    </thead>

    <tbody>


    <c:forEach var="listAds" items="${usersAdsEntities}">
        <c:choose>
            <c:when test="${sessionScope.UserEntity.id == listAds.usersByIdUsers.id}">
                <tr class="${listAds.adsByIdAds.active ? 'hide' : 'show'}">
                    <td><img src="<c:url value="/resources/img/${listAds.adsByIdAds.carsByIdCars.picture}"/>" height="30" width="25"></td>
                    <td>${listAds.adsByIdAds.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.modelsByIdModels.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.carTypesByIdCarTypes.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.color}</td>
                    <td><fmt:formatNumber value="${listAds.adsByIdAds.price}" type="currency"/></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.carsByIdCars.releaseYear}"/></td>
                    <td>${listAds.adsByIdAds.carsByIdCars.kilometer} Km</td>
                    <td><fmt:formatNumber type="number" value="${listAds.adsByIdAds.carsByIdCars.horsePower}" maxFractionDigits="3"/> CV</td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.dateStart}"/></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.dateEnd}"/></td>
                    <td> ${listAds.adsByIdAds.typesAds} </td>
                    <td>
                        <form action="" method="post">
                            <input type="hidden" id="idAdsToActivate" name="idAdsToActivate" value="${listAds.adsByIdAds.id}">
                            <input type="hidden" id="idCarsToActivate" name="idCarsToActivate" value="${listAds.adsByIdAds.carsByIdCars.id}">
                            <button type="submit" class="btn btn-outline-info"><i class="fa fa-repeat"></i></button>
                        </form>
                    </td>
                    <td>
                        <form action="modifier-annonce" method="post">
                            <input type="hidden" id="idAds" name="idAds" value="${listAds.adsByIdAds.id}">
                            <input type="hidden" id="idCars" name="idCars" value="${listAds.adsByIdAds.carsByIdCars.id}">
                            <button type="submit" class="btn btn-outline-info"><i class="far fa-edit"></i></button>
                        </form>
                    </td>
                    <td>
                        <form action="supprimer-annonce" method="post">
                            <input type="hidden" id="adsDelete" name="adsDelete" value="${listAds.adsByIdAds.id}">
                            <button type="submit" class="btn btn-outline-danger"><i class="fa fa-trash"></i></button>
                        </form>
                    </td>
                </tr>
            </c:when>
        </c:choose>

    </c:forEach>
    </tbody>
</table>
<jsp:include page="footer.jsp"/>