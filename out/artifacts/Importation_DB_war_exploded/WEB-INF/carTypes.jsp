<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container">
    <h1>Ajouter une catégorie</h1>

    <form method="post" action="categorie">
        <div class="form-group">
            <label>Catégorie</label>
            <input type="text" class="form-control" id="category" name="category" placeholder="Ajouter une catégorie">
            <!--<span class="alert alert-danger" role="alert">${carTypesForm.erreurs['category']}</span>-->
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
        <p class="${empty carTypesForm.erreurs ? 'succes' : 'erreur'}">${carTypesForm.resultat}</p>
    </form>
</div>

</body>
</html>