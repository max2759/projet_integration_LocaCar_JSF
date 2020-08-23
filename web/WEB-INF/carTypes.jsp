<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

    <h1>Les catégories</h1>

    <table class='table table-hover'>
        <thead>
        <tr>
            <th>Nom de la catégorie</th>
            <th>Modifier</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var='DisplayAllCategory' items='${category}'>
            <tr>
                <td><c:out value="${DisplayAllCategory.label} "/></td>
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