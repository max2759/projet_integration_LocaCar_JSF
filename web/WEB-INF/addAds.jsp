<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container">
    <h1>Ajouter une annonce</h1>

    <form method="post" action="ajouter-annonce">
        <div class="form-group">
            <label>Titre de l'annonce</label>
            <input type="text" class="form-control" id="labelAd" name="labelAd" placeholder="Titre de l'annonce"
                   required>
        </div>
        <div class="form-group">
            <label>Type d'annonce</label>
            <select id="adType" name="adType" class="form-control" required>
                <option value="VENTE">Vente</option>
                <option value="LOCATIONLD">Location longue durée</option>
                <option value="LOCATIONCD">Location courte durée</option>
                <option value="LEASING">Leasing</option>
            </select>
        </div>
        <div class="form-group">
            <label>Prix</label>
            <input type="number" class="form-control" min="100" step="any" name="adPrice" id="adPrice"
                   placeholder="1000€"
                   required="required">
        </div>
        <div class="form-group">
            <label>Couleur</label>
            <input type="text" class="form-control" id="color" name="color" placeholder="Couleur du véhicule"
                   required>
        </div>
        <div class="form-group">
            <label>Année de fabrication</label>
            <input type="date" class="form-control" id="ReleaseYear" name="ReleaseYear"
                   required>
        </div>
        <div class="form-group">
            <label>Kilométrage</label>
            <input type="number" class="form-control" min="100" step="any" id="kilometer" name="kilometer"
                   placeholder="230000km"
                   required>
        </div>
        <div class="form-group">
            <label>Chevaux</label>
            <input type="number" class="form-control" min="10" step="any" id="horsePower" name="horsePower"
                   placeholder="150 CV"
                   required>
        </div>


        <div class="form-group">
            <label>Type de voiture</label>
            <select id="carTypes" name="carTypes" class="form-control">
                <c:forEach var="categoryType" items="${category}">
                    <option value="${categoryType.id}">${categoryType.label}</option>
                </c:forEach>
            </select>

        </div>


        <div class="form-group">
            <label>Marques</label>
            <select id="brands" name="brands" class="form-control">
                <c:forEach var="brands" items="${brands}">
                    <option value="${brands.id}">${brands.label}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Modèle</label>
            <select id="models" name="models" class="form-control">
                <c:forEach var="models" items="${models}">
                    <option value="${models.id}">${models.label}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Carburant</label>
            <select id="fuel" name="fuel" class="form-control">
                <option value="ESSENCE">Essence</option>
                <option value="DIESEL">Diesel</option>
                <option value="HYBRID">Hybrid</option>
                <option value="PIHYBRID-in">Plug-in hybrid</option>
                <option value="GAZ">Gaz</option>
                <option value="ELECTRIQUE">Électrique</option>
            </select>
        </div>

        <div class="form-group">
            <input type="file" name="adsPicture" id="adsPicture">
        </div>

        <input name="idUser" id="idUser" type="hidden" value="${sessionScope.User}"/>

        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>

</body>
</html>