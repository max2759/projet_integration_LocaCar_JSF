<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>


<table class='table table-hover'>
    <thead>
    <tr>
        <th>Modèles</th>
        <th>Constructeurs</th>
        <th>Modifier</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var='models' items='${models}'>
        <tr>
            <td><c:out value="${models.label} "/></td>
            <td><c:out value="${models.brandsByIdBrands.label}"/></td>
            <td>
                <form action="modification-categorie" method="post">
                    <input type="number" hidden id="idCategory" name="idCategory" value="${DisplayAllCategory.id}">
                    <button type="submit" class="btn btn-outline-info"><i class="far fa-edit"></i></button>
                </form>
            </td>
        </tr>


    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>