<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Panier :</h2>
<%--<p>${adsForm.errors.idAds}</p>--%>
<%--<p>${adsForm.result}</p>--%>
<%--Il faut supprimer le message d'erreur--%>
<%--<c:remove var="adsForm" scope="request"/>--%>
<%--<p>${carsForm.result}</p>--%>

<%--<p>${carsForm.errors}</p>--%>
<%--<p>${cars}</p>--%>
<%--<p>${sessionScope.basket}</p>--%>


<table class='table table-hover'>
    <thead>
    <tr>
        <th>Model</th>
        <th>Car Type</th>
        <th>Color</th>
        <th>Year</th>
        <th>Km</th>
        <th>HorsePower</th>
        <th>Fuel</th>
        <th>Price</th>
        <th>ID Car</th>
        <th>Supprimer</th>
    </tr>
    </thead>

    <tbody><%--
    <c:forEach var="entry" items="${sessionScope.basket}">

        <tr>
            <td> ${entry.value.carsByIdCars.modelsByIdModels.label} </td>
            <td> ${entry.value.carsByIdCars.carTypesByIdCarTypes.label} </td>
            <td> ${entry.value.carsByIdCars.color} </td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${entry.value.carsByIdCars.releaseYear}"/></td>
            <td> ${entry.value.carsByIdCars.kilometer} </td>
            <td> ${entry.value.carsByIdCars.horsePower} </td>
            <td> ${entry.value.carsByIdCars.enumFuel} </td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${entry.value.price}"/>€</td>
--%>
    <c:forEach var="entry" items="${basket}">

        <tr>
            <td> ${entry.carsByIdCars.modelsByIdModels.label} </td>
            <td> ${entry.carsByIdCars.carTypesByIdCarTypes.label} </td>
            <td> ${entry.carsByIdCars.color} </td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${entry.carsByIdCars.releaseYear}"/></td>
            <td> ${entry.carsByIdCars.kilometer} </td>
            <td> ${entry.carsByIdCars.horsePower} </td>
            <td> ${entry.carsByIdCars.enumFuel} </td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${entry.finalPrice}"/>€</td>
            <td> ${entry.carsByIdCars.id} </td>

        <%--
                    Key: <c:out value="${entry.key}"/>
                    Value: <c:out value="${entry.value.carsByIdCars.id}"/><br \>
                --%>

            <td>
                <form name="forRemove" id="forRemove" action="removeBasket" method="post" class="">
                    <input name="idCar" id="${entry.carsByIdCars.id}" type="hidden" value="${entry.carsByIdCars.id}"/>
                    <input name="idUser" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
                    <input name="send" id="boutonSubmit" type="submit" value="supprimer du panier"
                           class="btn btn-info"/>
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<%--

<c:if test="${(not empty ads) && (not empty cars)}">

    <table class='table table-hover'>
        <thead>
        <tr>
            <th>Model</th>
            <th>Car Type</th>
            <th>Color</th>
            <th>Year</th>
            <th>Km</th>
            <th>HorsePower</th>
            <th>Fuel</th>
            <th>Price</th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td> ${cars.modelsByIdModels.label} </td>
            <td> ${cars.carTypesByIdCarTypes.label} </td>
            <td> ${cars.color} </td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${cars.releaseYear}"/></td>
            <td> ${cars.kilometer} </td>
            <td> ${cars.horsePower} </td>
            <td> ${cars.enumFuel} </td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ads.price}"/>€</td>
        </tr>

        </tbody>
    </table>
</c:if>
--%>

</div>

</body>
</html>