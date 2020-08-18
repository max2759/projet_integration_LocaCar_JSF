<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

    <h1>Ajouter une catégorie</h1>

    <form method="post" action="ajouter-categorie">
        <div class="form-group">
            <label>Catégorie</label>
            <input type="text" class="form-control" id="category" name="category" placeholder="Ajouter une catégorie" required>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
        <div class="msg_info_field">
            <c:set var="testErr" value="${carTypesForm.erreurs['category']}"/>
            <c:if test="${testErr!=null}">
                <span class='alert alert-danger' role='alert'>${testErr}</span>
            </c:if>
        </div>
        <div class="msg_info_field">
            <span class="${empty carTypesForm.erreurs ? 'success' : 'alert alert-danger'}">${carTypesForm.resultat}</span>
        </div>
    </form>
<jsp:include page="footer.jsp"/>