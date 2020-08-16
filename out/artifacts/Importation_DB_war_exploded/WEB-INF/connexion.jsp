<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <h2>Connexion</h2>

    <p>Vous êtes connecté avec <c:out value="${sessionScope.User}"/>
    </p>

    <form method="post" action="connexion">

        <fieldset class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="User" value="1" id="Admin">
                <label class="form-check-label">
                    Admin
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="User" value="2" id="User1">
                <label class="form-check-label">
                    User 1
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="User" value="3" id="User2">
                <label class="form-check-label">
                    User 2
                </label>
            </div>
        </fieldset>

        <button type="submit" class="btn btn-primary">Se connecter</button>

    </form>
    </table>
</div>
</div>
</body>
</html>
