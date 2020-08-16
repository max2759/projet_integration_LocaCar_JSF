<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Commande : ${ordersEntity.id}</h2>

<c:if test="${not empty ordersForm.errors }">
    <div class="alert alert-danger" role="alert">
        <c:out value="${ordersForm.errors.idOrders}"/>
    </div>
</c:if>

<p class='font-weight-bold'><c:out value="Numéro de commande : ${ordersEntity.id}"/></p>
<p><c:out value="Numéro de client : ${ordersEntity.usersByIdUsers.id}"/></p>
<p><c:out value="Nom d'utilisateur du client : ${ordersEntity.usersByIdUsers.username}"/></p>
<p class='font-weight-bold'><c:out value="Nom du client : ${ordersEntity.usersByIdUsers.username}"/></p>
<p class='font-weight-bold'><c:out value="Prénom du client: ${ordersEntity.usersByIdUsers.username}"/></p>
<p class='font-weight-bold'><c:out value="Date de commande : "/><fmt:formatDate pattern="dd-MM-yyyy" value="${ordersEntity.orderDate}"/></p>

<c:choose>
    <c:when test="${ordersEntity.orderStatut eq 'VALIDATED'}"><p class='font-weight-bold'>Staut : Validé</p></c:when>
    <c:when test="${ordersEntity.orderStatut eq 'CANCELED'}"><p class='font-weight-bold'>Statut :Annulé</p></c:when>
</c:choose>
<br \><br \>
<p><c:out value="Détails de la commande : "/></p>

<table class='table table-hover'>
    <thead>
    <tr>
        <th>Marque</th>
        <th>Modèle</th>
        <th>Année</th>
        <th>Kilométrage</th>
        <th>Couleur</th>
        <th>Puissance</th>
        <th>Carburant</th>
        <th>Carrosserie</th>
        <th>Date de début de réservation</th>
        <th>Date de fin de réservation</th>
        <th>Prix</th>
    </tr>
    </thead>

    <tbody>
    <c:set var="totalPrice" value="${0}"/>
    <c:forEach var="entry" items="${contractsEntityList}">

        <tr>
            <td> ${entry.carsByIdCars.modelsByIdModels.brandsByIdBrands.label} </td>
            <td> ${entry.carsByIdCars.modelsByIdModels.label} </td>
            <td><fmt:formatDate pattern="MM-yyyy" value="${entry.carsByIdCars.releaseYear}"/></td>
            <td> ${entry.carsByIdCars.kilometer} km</td>
            <td> ${entry.carsByIdCars.color} </td>
            <td> ${entry.carsByIdCars.horsePower} cv</td>
            <td> ${entry.carsByIdCars.enumFuel} </td>
            <td> ${entry.carsByIdCars.carTypesByIdCarTypes.label} </td>
            <td class='font-weight-bold'><fmt:formatDate pattern="dd-MM-yyyy" value="${entry.dateStart}"/></td>
            <td class='font-weight-bold'><fmt:formatDate pattern="dd-MM-yyyy" value="${entry.dateEnd}"/></td>
            <td class='font-weight-bold'><fmt:formatNumber type="number" maxFractionDigits="2" value="${entry.finalPrice}"/>€</td>
            <c:set var="totalPrice" value="${totalPrice + entry.finalPrice}"/>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p class='font-weight-bold'><c:out value="Total : "/><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalPrice}"/>€</td></p>


<c:if test="${ordersEntity.orderStatut eq 'VALIDATED'}">
    <form name="forDelete" id="forDelete" action="deleteOrders" method="post" class="">
        <input name="idOrders" id="${ordersEntity.id}" type="hidden" value="${ordersEntity.id}"/>
        <input name="send" id="boutonSubmit" type="submit" value="Annuler la commande"
               class="btn btn-danger"/>
    </form>
</c:if>

<jsp:include page="footer.jsp"/>