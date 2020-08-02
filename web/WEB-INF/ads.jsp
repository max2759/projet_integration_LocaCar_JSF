<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Ajout de véhicule</title>
    <link type="text/css" rel="stylesheet" href="<c:url value=""/>" />
</head>
<body>
<!----->
<h2>Annonce</h2>
<table class = 'table table-hover'>
    <thead>
    <tr>
        <th>Label Cars</th>
        <th>Price</th>
        <th>Date Start</th>
        <th>Date End</th>
        <th>Type ADS</th>
        <th>Label</th>
    </tr>
    </thead>

    <tbody>
    <!-- On parcours la db pour afficher les utilisateurs. -->

    <c:forEach var='listeAds' items='${ads}'>
        <tr>
                        <td> ${listeAds.carsByIdCars.modelsByIdModels.label} </td>
                        <td> ${listeAds.price} </td>
                        <td> ${listeAds.dateStart} </td>
                        <td> ${listeAds.dateEnd} </td>
                        <td> ${listeAds.typesAds} </td>
                        <td> ${listeAds.label} </td>
                     </tr>

    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>