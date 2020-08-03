<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Annonce</h2>
<p>${adsForm.result}</p>
<p>${carsForm.result}</p>

<p>${carsForm.errors}</p>



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

</div>

</body>
</html>