<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Panier :</h2>
<p>${adsForm.errors.idAds}</p>
<p>${adsForm.result}</p>
<%--Il faut supprimer le message d'erreur--%>
<c:remove var="adsForm" scope="request"/>
<p>${carsForm.result}</p>

<p>${carsForm.errors}</p>
<%--<p>${cars}</p>--%>
<%--<p>${sessionScope.basket}</p>--%>


<c:if test="${not empty basket}">
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
            <th>Prix</th>
            <th>Supprimer</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="entry" items="${basket}">

            <tr>
                <td><c:out value="${entry.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}"/></td>
                <td><c:out value="${entry.carsByIdCars.modelsByIdModels.label}"/></td>
                <td><fmt:formatDate pattern="MM-yyyy" value="${entry.carsByIdCars.releaseYear}"/></td>
                <td><c:out value="${entry.carsByIdCars.kilometer} km"/></td>
                <td><c:out value="${entry.carsByIdCars.color}"/></td>
                <td><c:out value="${entry.carsByIdCars.horsePower} cv"/></td>
                <td><c:out value="${entry.carsByIdCars.enumFuel} "/></td>
                <td><c:out value="${entry.carsByIdCars.carTypesByIdCarTypes.label} "/></td>
                <td class='font-weight-bold'><fmt:formatNumber type="number" maxFractionDigits="2"
                                                               value="${entry.finalPrice}"/>€
                </td>
                <td>
                    <form name="forRemove" id="forRemove" action="removeBasket" method="post" class="">
                        <input name="idCar" id="${entry.carsByIdCars.id}" type="hidden"
                               value="${entry.carsByIdCars.id}"/>
                        <input name="idUser" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
                        <input name="send" id="boutonSubmit" type="submit" value="supprimer du panier"
                               class="btn btn-info"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p class='font-weight-bold'><c:out value="Total : "/><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                           value="${totalPrice}"/>€</td></p>


    <form name="forValidate" id="forValidate" action="validateOrder" method="post" class="">
        <div class="form-group">
            <input name="idUser" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
            <input name="send" id="boutonSubmit2" type="submit" value="Valider"
                   class="btn btn-primary btn-lg"/>
        </div>
    </form>
</c:if>

<c:if test="${empty basket}">
    <p> Votre panier est vide.</p>
</c:if>
<p></p>
<jsp:include page="footer.jsp"/>