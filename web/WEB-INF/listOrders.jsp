<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Listes des commandes :</h2>

<div class="container" style="width:350px">
    <p>
    <form name="listOrders" id="listOrders" action="listOrders" method="post" class="">

        <div class="form-group">
            <label>Entrez un numéro de commande, un numéro de client ou un nom de client : </label>
            <input class="form-control" name="idSearch" id="idSearch" type="text" value=""/>
        </div>
        <input class="btn btn-lg btn-primary btn-block" name="send" id="search" type="submit" value="Rechercher"/>
    </form>
    </p>
</div>
<table class='table table-hover'>
    <thead>
    <tr>
        <th>Numéro de commande</th>
        <th>Numéro d'utilisateur</th>
        <th>Nom d'utilisateur</th>
        <th>Date de commande</th>
        <th>Statut</th>
        <th>Annuler</th>
    </tr>
    </thead>

    <tbody>

    <c:if test="${not empty listOrdersForm.errors }">
        <div class="alert alert-danger" role="alert">
            <c:out value="${listOrdersForm.errors.idSearch}"/>
            <c:out value="${listOrdersForm.errors.FIELD_ID_SEARCH}"/>
        </div>
    </c:if>
    <c:forEach var='listeOrders' items='${ordersEntities}'>
        <tr>
            <td>
                <form name="myform${listeOrders.id}" id="myform${listeOrders.id}" action="order" method="post">
                    <input name="idOrder" id="${listeOrders.id}" type="hidden" value="${listeOrders.id}"/>
                </form>
                <a href='#' onclick='document.getElementById("myform${listeOrders.id}").submit()'>${listeOrders.id}</a>
            </td>
            <td> ${listeOrders.usersByIdUsers.id} </td>
            <td> ${listeOrders.usersByIdUsers.username} </td>
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${listeOrders.orderDate}"/></td>
            <td>
                <c:choose>
                    <c:when test="${listeOrders.orderStatut eq 'VALIDATED'}">Validé</c:when>
                    <c:when test="${listeOrders.orderStatut eq 'CANCELED'}">Annulé</c:when>
                </c:choose>
            </td>
            <td>

                <c:if test="${listeOrders.orderStatut eq 'VALIDATED'}">
                    <form name="forDelete" id="forDelete" action="deleteOrders" method="post" class="">
                        <input name="idOrders" id="${listeOrders.id}" type="hidden" value="${listeOrders.id}"/>
                        <input name="send" id="boutonSubmit" type="submit" value="Annuler la commande"
                               class="btn btn-info"/>
                    </form>
                </c:if>
            </td>
        </tr>

    </c:forEach>

    </tbody>
</table>
</div>

</body>
</html>