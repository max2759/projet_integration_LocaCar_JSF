<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Commande :</h2>

<c:if test="${not empty ordersForm.errors }">
    <div class="alert alert-danger" role="alert">
        <c:out value="${ordersForm.errors.idOrders}"/>
    </div>
</c:if>

<p><c:out value="${ordersForm.result}"/></p>

<jsp:include page="footer.jsp"/>