<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Annonce de la location :</h2>
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
        <th>Price / day</th>
        <th>Du 25/08/2020 au 30/08/2020</th>
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
        <c:set var="finalPrice"> ${ads.price * 6} </c:set>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${finalPrice}"/>€</td>
    </tr>

    </tbody>
</table>
<c:if test="${not empty sessionScope.User}">
    <form name="formCar" id="formCar" action="basket" method="post" class="">
        <input name="idUsers" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
        <input name="idAds" id="${ads.id}" type="hidden" value="${ads.id}"/>
        <input name="locationDays" id="6" type="hidden" value="6"/>
        <input name="dateStart" id="dateStart" type="hidden" value="25/08/2020"/>
        <input name="dateEnd" id="dateEnd" type="hidden" value="30/08/2020"/>
        <br \><br \>
        <input name="send" id="boutonSubmit" type="submit" value="Ajouter au panier" class="btn btn-info"/>
    </form>
</c:if>
<c:if test="${empty sessionScope.User}">
    <p>Vous devez vous connecter pour l'ajouter au panier.</p>
    <p><a href="connexion">Cliquez ici pour aller à la page de connexion</a> </p>
</c:if>

<jsp:include page="footer.jsp"/>