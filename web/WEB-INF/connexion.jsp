<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <h2>Connexion</h2>

    <p>Vous êtes connecté avec <c:out value="${sessionScope.User}" />
    </p>

    <form method="post" action="connexion">

        <fieldset>
            <p>Selectionnez l'utilisateur avec lequel vous voulez vous connecter :<br />
                <input type="radio" name="User" value="2" id="User1"/>
                <label for="User1">User1</label><br />
                <input type="radio" name="User" value="3" id="User2"/>
                <label for="User2">User2</label><br />
                <input type="radio" name="User" value="1" id="Admin"/>
                <label for="Admin">Admin</label><br />
            </p>
        </fieldset>

        <p class="envoyer">
            <input type="submit" value="Envoyer" />
        </p>
    </form>
    </table>
</div>
</div>
</body>
</html>
