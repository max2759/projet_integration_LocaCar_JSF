<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<!----->
<h2>Commande :</h2>
<p>${ordersForm.errors.idAds}</p>
<p>${ordersForm.errors.car}</p>

<p>${ordersForm.result}</p>
<%--Il faut supprimer le message d'erreur--%>
<c:remove var="ordersForm" scope="request"/>


</div>

</body>
</html>