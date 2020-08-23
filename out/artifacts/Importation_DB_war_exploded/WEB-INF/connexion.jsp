<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<h2>Connexion</h2>

<c:if test="${not empty sessionScope.User}">
    <p>Vous êtes connecté avec <strong><c:out value="${sessionScope.UserEntity.username}"/></strong></p>
</c:if>


<form method="post" action="connexion">

    <fieldset class="form-group">
        <div class="form-check">
            <input class="form-check-input" type="radio" name="User" value="1" id="Admin">
            <label for="Admin" class="form-check-label">
                Admin
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="User" value="2" id="User1">
            <label for="User1" class="form-check-label">
                User 1
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="User" value="3" id="User2">
            <label for="User2" class="form-check-label">
                User 2
            </label>
        </div>
    </fieldset>

    <button type="submit" class="btn btn-warning">Se connecter</button>

</form>
<jsp:include page="footer.jsp"/>