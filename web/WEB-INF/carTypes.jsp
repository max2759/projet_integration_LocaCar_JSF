<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container">
    <h1>Les catégories</h1>

    <table class='table table-hover'>
        <thead>
        <tr>
            <th>Nom de la catégorie</th>
            <th>Modifier</th>
            <th>Supprimer</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var='DisplayAllCategory' items='${category}'>
            <tr>
                <td><c:out value="${DisplayAllCategory.label} "/></td>
                <td>
                    <form action="modification-categorie" method="post">
                        <input type="number" hidden id="idCategory" name="idCategory" value="${DisplayAllCategory.id}">
                        <input type="text" class="form-control col-md-7" id="updateCat" name="updateCat" placeholder="Modifier la catégorie">
                        <button type="submit" class="btn btn-outline-info"><i class="far fa-edit"></i></button>
                    </form>
                </td>
                <td>
                    <form action="categories" method="post">
                        <input type="hidden" id="categoryDelete" name="categoryDelete" value="${DisplayAllCategory.id}">
                        <button type="submit" class="btn btn-outline-danger"><i class="fa fa-trash"></i></button>
                    </form>
                </td>
            </tr>


        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>

