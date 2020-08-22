<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <h1>Ajouter un modèle en fonction du constructeur</h1>
    <form method="post" action="ajouter-modeles">
        <div class="form-group">
            <label>Constructeur</label>
            <select id="brandsModels" name="brandsModels" class="form-control col-sm-2" required>
                <c:forEach var="brands" items="${brands}">
                    <option value="${brands.id}">${brands.label}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Ajouter un modèle</label>
            <input type="text" class="form-control col-sm-2" id="modelsAdd" name="modelsAdd"
                   placeholder="Ajouter un modèle" required>
        </div>
        <button type="submit" class="btn btn-warning">Ajouter</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>