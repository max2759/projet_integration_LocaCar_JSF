<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<div class="container">
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
            <th>Supprimer</th>
        </tr>
        </thead>

        <tbody>
        <!-- On parcours la db pour afficher les utilisateurs. -->

        <c:forEach var='listeAds' items='${ads}'>
            <c:if test="${listeAds.isActive == 1}"/>
            <tr>
                <td> ${listeAds.label} </td>
                <td> ${listeAds.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
                <td> ${listeAds.carsByIdCars.modelsByIdModels.label}</td>
                <td> ${listeAds.carsByIdCars.carTypesByIdCarTypes.label}</td>
                <td> ${listeAds.carsByIdCars.color}</td>
                <td> ${listeAds.price} </td>
                <td> ${listeAds.dateStart} </td>
                <td> ${listeAds.dateEnd} </td>
                <td> ${listeAds.typesAds} </td>
                <td>
                    <form action="supprimer-annonce" method="post">
                        <input type="hidden" id="adsDelete" name="adsDelete" value="${listeAds.id}">
                        <button type="submit" class="btn btn-outline-danger"><i class="fa fa-trash"></i></button>
                    </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
</html>

