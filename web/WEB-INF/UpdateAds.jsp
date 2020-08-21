<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="return-button">
        <a href="<c:url value="/annonces"/>">
            <button type="button" class="btn btn-warning"><i class="fa fa-chevron-left"></i> Retour
            </button>
        </a>
    </div>

    <h1>Modifier l'annonce</h1>


    <form method="post" action="update">
        <div class="form-group">
            <label>Titre de l'annonce</label>
            <input type="text" class="form-control" id="labelAd" name="labelAd" placeholder="Titre de l'annonce"
                   value="${ads.label}"
                   required>

        </div>
        <div class="form-group">
            <label>Type d'annonce</label>
            <select id="adType" name="adType" class="form-control" required>
                <c:forEach var="enumTypesAds" items="${enumTypesAds}">
                    <option value="${enumTypesAds}" ${enumTypesAds == ads.typesAds ? 'selected="selected"' : ''}>${enumTypesAds}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Prix fixe ou prix par jour</label>
            <input type="number" class="form-control" min="100" step="any" name="adPrice" id="adPrice"
                   value="${ads.price}"
                   placeholder="1000€"
                   required="required">
        </div>
        <div class="form-group">
            <label>Couleur</label>
            <input type="text" class="form-control" id="color" name="color" value="${ads.carsByIdCars.color}"
                   placeholder="Couleur du véhicule"
                   required>
        </div>
        <div class="form-group">
            <label>Année de fabrication</label>
            <input type="date" class="form-control" id="ReleaseYear"
                   value="<fmt:formatDate pattern="yyyy-MM-dd" value="${ads.carsByIdCars.releaseYear}"/>"
                   name="ReleaseYear"
                   required>
        </div>
        <div class="form-group">
            <label>Kilométrage</label>
            <input type="number" class="form-control" value="${ads.carsByIdCars.kilometer}" step="any" id="kilometer"
                   name="kilometer"
                   placeholder="230000km"
                   required>
        </div>
        <div class="form-group">
            <label>Chevaux</label>
            <input type="number" class="form-control" value="${ads.carsByIdCars.horsePower}" min="10" step="any"
                   id="horsePower" name="horsePower"
                   placeholder="150 CV"
                   required>
        </div>


        <div class="form-group">
            <label>Type de voiture</label>
            <select id="carTypes" name="carTypes" class="form-control">
                <c:forEach var="categoryType" items="${category}">
                    <option value="${categoryType.id}" ${categoryType.id == ads.carsByIdCars.carTypesByIdCarTypes.id ? 'selected="selected"' : ''}>${categoryType.label}</option>
                </c:forEach>
            </select>

        </div>

        <div class="form-group">
            <label>Marques</label>
            <select id="brands" name="brands" class="form-control">
                <c:forEach var="brands" items="${brands}">
                    <option value="${brands.id}" ${brands.id == ads.carsByIdCars.modelsByIdModels.brandsByIdBrands.id ? 'selected="selected"' : ''}>${brands.label}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Modèle</label>
            <select id="models" name="models" class="form-control">
                <c:forEach var="models" items="${models}">
                    <option value="${models.id}" ${models.id == ads.carsByIdCars.modelsByIdModels.id ? 'selected="selected"' : ''}>${models.label}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>Carburant</label>
            <select id="fuel" name="fuel" class="form-control">
                <c:forEach var="enumFuel" items="${enumFuel}">
                    <option value="${enumFuel}" ${enumFuel == ads.carsByIdCars.enumFuel ? 'selected="selected"' : ''}>${enumFuel}</option>
                </c:forEach>
            </select>
        </div>

        <input type="hidden" name="idAdToUpdate" id="idAdToUpdate" value="${ads.id}">
        <input type="hidden" name="idCarToUpdate" id="idCarToUpdate" value="${ads.carsByIdCars.id}">

        <button type="submit" class="btn btn-primary">Ajouter</button>

    </form>

</div>

<jsp:include page="footer.jsp"/>