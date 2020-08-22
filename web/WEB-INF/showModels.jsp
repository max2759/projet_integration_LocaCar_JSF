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
                <form action="modeles" method="post">
                    <input type="text" id="modelLabelToUpdate" name="modelLabelToUpdate" class="form-group" required>
                    <input type="hidden" id="idModelsToUpdate" name="idModelsToUpdate" value="${models.id}">
                    <button type="submit" class="btn btn-outline-info"><i class="far fa-edit"></i></button>
                </form>
            </td>
        </tr>


    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>