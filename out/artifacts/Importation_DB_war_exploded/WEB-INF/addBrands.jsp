<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <h1>Ajouter un constructeur</h1>

    <form method="post" action="ajouter-constructeur">
        <div class="form-group">
            <input type="text" class="form-control" id="constructeur" name="constructeur" placeholder="Ajouter un constructeur" required>
        </div>
        <button type="submit" class="btn btn-warning">Ajouter</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>