<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>


<table class='table table-hover'>
    <thead>
    <tr>
        <th>Constructeur</th>
        <th>Modifier</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var='brands' items='${brands}'>
        <tr>
            <td><c:out value="${brands.label} "/></td>
            <td>
                <form action="constructeurs" method="post">
                    <input type="text" id="brandsLabel" name="brandsLabel" required>
                    <input type="hidden" id="idBrands" name="idBrands" value="${brands.id}">
                    <button type="submit" class="btn btn-outline-info"><i class="far fa-edit"></i></button>
                </form>
            </td>
        </tr>


    </c:forEach>
    </tbody>
</table>

<jsp:include page="footer.jsp"/>