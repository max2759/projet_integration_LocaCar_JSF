<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Commande :</h2>

<c:if test="${not empty ordersForm.errors }">

    <div class="alert alert-danger" role="alert">
        <c:out value="${ordersForm.errors.idAds}"/>
    </div>

    <div class="alert alert-danger" role="alert">
        <c:out value="${ordersForm.errors.car}"/>
    </div>
</c:if>


<p><c:out value="${ordersForm.result}"/></p>
<%--Il faut supprimer le message d'erreur--%>
<c:remove var="ordersForm" scope="request"/>

<jsp:include page="footer.jsp"/>