<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="return-button">
        <a href="<c:url value="/categories"/>">
            <button type="button" class="btn btn-warning"><i class="fa fa-chevron-left"></i> Retour
            </button>
        </a>
    </div>


    <form action="validate-edit" method="post">
        <div class="form-group">
            <input type="hidden" name="idCategoryToUpdate" id="idCategoryToUpdate" value="${category.id}">
            <input type="text" class="form-control col-md-7" id="updateCat" name="updateCat" value="${category.label}"
                   placeholder="Modifier la catégorie">
        </div>
        <button type="submit" class="btn btn-warning">Modifier</button>
    </form>

</div>

<jsp:include page="footer.jsp"/>