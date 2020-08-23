<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Listes des commandes :</h2>

<p>
<form name="listOrders" id="listOrders" action="listOrders" method="post" class="form-inline">

    <div class="form-group mx-sm-3 mb-2">
        <label>Entrez un numéro de commande, un numéro ou un nom de client : &nbsp;</label>
        <input class="form-control" name="idSearch" id="idSearch" type="text" value="">
    </div>
    <input class="btn btn-warning mb-2" name="send" id="search" type="submit" value="Rechercher"/>
</form>
</p>
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
            <td class='font-weight-bold'>
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
                               class="btn btn-danger"/>
                    </form>
                </c:if>
            </td>
        </tr>

    </c:forEach>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>