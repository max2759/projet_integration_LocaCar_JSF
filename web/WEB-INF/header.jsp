<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Locacar</title>
    <link rel="icon" href="<c:url value="/resources/img/locacar.png"/>"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"/>

    <script src="<c:url value="https://kit.fontawesome.com/9b9e4fe389.js"/>"></script>
    <!-- JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<c:url value="/accueil"/>"><img
            src="<c:url value="/resources/img/logoLocacar.png"/>" alt="locacar"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Annonces
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <c:if test="${not empty sessionScope.UserEntity}">
                        <a class="dropdown-item" href="<c:url value="/annonces"/>">Mes annonces</a>
                        <a class="dropdown-item" href="<c:url value="/ajouter-annonce"/>">Ajouter annonce</a>
                    </c:if>
                </div>
            </li>
            <c:if test="${not empty sessionScope.UserEntity}">
                <c:if test="${sessionScope.UserEntity.rolesByIdRoles.label eq 'Admin'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Catégorie
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="<c:url value="/categories"/>">Voir les catégories</a>
                            <a class="dropdown-item" href="<c:url value="/ajouter-categorie"/>">Ajouter catégorie</a>
                        </div>
                    </li>
                </c:if>
            </c:if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Panier
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/basket"/>">Voir le panier</a>
                </div>
            </li>
            <c:if test="${not empty sessionScope.UserEntity}">
                <c:if test="${sessionScope.UserEntity.rolesByIdRoles.label eq 'Admin'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="Gerer" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Gerer
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="<c:url value="/listOrders"/>">Liste des commande</a>
                        </div>
                    </li>
                </c:if>
            </c:if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="connexion" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Connexion
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/connexion"/>">Se connecter</a>
                </div>
            </li>
        </ul>
        <div class="user-group">
            <div class="user-conn">
                <c:if test="${not empty sessionScope.User}">
                    <i class="fa fa-user"> <c:out value="${sessionScope.UserEntity.username}"/> </i>
                </c:if>

            </div>
            <div class="form-logout">
                <form method="post" action="deconnexion">
                    <button type="submit" name="Deconnexion" class="btn-logout">
                        <i class="fa fa-sign-out"></i>
                    </button>
                </form>
            </div>

        </div>
    </div>
</nav>
<div class="container-fluid shadow-sm p-3 mb-5 bg-white border rounded">
    <section>
        <article>
            <div class='container-fluid border rounded'>

