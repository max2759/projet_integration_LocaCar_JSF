<%@ page pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/header.jsp"/>

<h1>Accueil</h1>

<p>test</p>

<c:forEach var="listAds" items="${adsEntities}">
    <tr class="${listAds.adsByIdAds.active ? 'show' : 'hide'}">
        <td>${listAds.label}</td>
        <td>${listAds.adsByIdAds.carsByIdCars.modelsByIdModels.brandsByIdBrands.label}</td>
        <td>${listAds.adsByIdAds.carsByIdCars.modelsByIdModels.label}</td>
        <td>${listAds.adsByIdAds.carsByIdCars.carTypesByIdCarTypes.label}</td>
        <td> ${listAds.adsByIdAds.carsByIdCars.color}</td>
        <td><fmt:formatNumber value="${listAds.adsByIdAds.price}" type="currency"/></td>
        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.dateStart}"/></td>
        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listAds.adsByIdAds.dateEnd}"/></td>
        <td> ${listAds.adsByIdAds.typesAds} </td>
        <td>
            <form action="annonce" method="post">
                <input type="hidden" id="idAds" name="idAds" value="${listAds.adsByIdAds.id}">
                <input name="idUser" id="${sessionScope.User}" type="hidden" value="${sessionScope.User}"/>
                <button type="submit" class="btn btn-info"><i class="fa fa-eye"></i></button>
            </form>
    </tr>

</c:forEach>



