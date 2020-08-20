<%@ page pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/header.jsp"/>

<h1>Accueil</h1>

<table class='table table-hover'>
    <thead>
    <tr>
        <th>Titre de l'annonce</th>
        <th>Marque</th>
        <th>Modèle</th>
        <th>Types de voiture</th>
        <th>Couleur</th>
        <th>Prix</th>
        <th>Type d'annonce</th>
        <th>Voir annonce en détail</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listAds" items="${adsEntities}">
        <tr class="${listAds.active ? 'show' : 'hide'}">
            <td>${listAds.label}</td>
            <td>${listAds.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
            <td>${listAds.carsByIdCars.modelsByIdModels.label}</td>
            <td>${listAds.carsByIdCars.carTypesByIdCarTypes.label}</td>
            <td> ${listAds.carsByIdCars.color}</td>
            <td><fmt:formatNumber value="${listAds.price}" type="currency"/></td>
            <td> ${listAds.typesAds}</td>
            <td>
                <form action="#" method="post">
                    <input type="hidden" id="idAds" name="idAds" value="${listAds.id}">
                    <input type="hidden" id="idCars" name="idCars" value="${listAds.carsByIdCars.id}">
                    <button type="submit" class="btn btn-outline-info"><i class="far fa-eye"></i></button>
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>





