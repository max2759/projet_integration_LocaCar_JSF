<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <h2>Annonce</h2>
    <table class='table table-hover'>
        <thead>
        <tr>
            <th>Marque</th>
            <th>Modèle</th>
            <th>Types de voiture</th>
            <th>Couleur</th>
            <th>Prix</th>
            <th>Date de début</th>
            <th>Date de fin</th>
            <th>Type d'annonce</th>
        </tr>
        </thead>

        <tbody>
        <!-- On parcours la db pour afficher les utilisateurs. -->

        <c:forEach var='listeAds' items='${ads}'>
            <tr>
                <td> ${listeAds.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
                <td> ${listeAds.carsByIdCars.modelsByIdModels.label}</td>
                <td> ${listeAds.carsByIdCars.carTypesByIdCarTypes.label}</td>
                <td> ${listeAds.carsByIdCars.color}</td>
                <td> ${listeAds.price} </td>
                <td> ${listeAds.dateStart} </td>
                <td> ${listeAds.dateEnd} </td>
                <td> ${listeAds.label} </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
</html>

