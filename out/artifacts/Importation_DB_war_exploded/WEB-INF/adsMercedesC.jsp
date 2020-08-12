<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Annonce Mercedes :</h2>
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

<form name="formCar" id="formCar" action="basket" method="post" class="">
    <input name="idUsers" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
    <input name="idAds" id="${ads.id}" type="hidden" value="${ads.id}"/>
    <br \><br \>
    <input name="send" id="boutonSubmit" type="submit" value="Ajouter au panier" class="btn btn-info"/>
</form>
</div>

</body>
</html>