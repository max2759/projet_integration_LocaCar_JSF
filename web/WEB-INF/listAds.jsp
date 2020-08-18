<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<h2>Annonce</h2>
<table class='table table-hover'>
    <thead>
    <tr>
        <th>Titre de l'annonce</th>
        <th>Marque</th>
        <th>Modèle</th>
        <th>Types de voiture</th>
        <th>Couleur</th>
        <th>Prix</th>
        <th>Date de début</th>
        <th>Date de fin</th>
        <th>Type d'annonce</th>
        <th>Voir l'annonce</th>
        <th>Supprimer</th>
    </tr>
    </thead>

    <tbody>


    <c:forEach var="listAds" items="${usersAdsEntities}">
        <c:choose>
            <c:when test="${sessionScope.UserEntity.id == listAds.usersByIdUsers.id}">
                <tr class="${listAds.adsByIdAds.active ? 'show' : 'hide'}">
                    <td>${listAds.adsByIdAds.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.modelsByIdModels.label}</td>
                    <td>${listAds.adsByIdAds.carsByIdCars.carTypesByIdCarTypes.label}</td>
                    <td> ${listAds.adsByIdAds.carsByIdCars.color}</td>
                    <td><fmt:formatNumber value="${listAds.adsByIdAds.price}" type="currency"/></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.dateStart}"/></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.dateEnd}"/></td>
                    <td> ${listAds.adsByIdAds.typesAds} </td>
                    <td>
                        <form action="annonce" method="post">
                            <input type="hidden" id="idAds" name="idAds" value="${listAds.adsByIdAds.id}">
                            <input name="idUser" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
                            <button type="submit" class="btn btn-info"><i class="fa fa-eye"></i></button>
                        </form>

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