<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/locale.tld" prefix="lng" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>BizPage Bootstrap Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicons -->
    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700"
          rel="stylesheet">

    <!-- Bootstrap CSS File -->
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Libraries CSS Files -->
    <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Main Stylesheet File -->
    <link href="css/style.css" rel="stylesheet">

    <!-- =======================================================
      Theme Name: BizPage
      Theme URL: https://bootstrapmade.com/bizpage-bootstrap-business-template/
      Author: BootstrapMade.com
      License: https://bootstrapmade.com/license/
    ======================================================= -->
</head>

<body>


<!--==========================
  Header
============================-->
<%@ include file="jspf/navbar.jspf" %><!-- #header -->
<br>
<div class="lang">
    <button class="btn btn-info"><a href="/changeLang">UA</a></button>
    <button class="btn btn-info"><a href="/changeLang?lang=eng">ENG</a></button>
</div>

<!-- Main -->

<c:set var="role" value="${sessionScope.ses_role}"></c:set>
<c:set var="page" value="${page}"></c:set>
<c:set var="sort" value="${sessionScope.sort_content}"></c:set>
<c:set var="all" value="${all_film}"></c:set>
<c:set var="last" value="${sessionScope.last_page}"></c:set>
<br>
<br>
<!-- Boxes -->
<section id="team">
    <div class="container">
        <div class="section-header wow fadeInUp">
            <h3><lng:tr text="Sessions" locale="${lang}"></lng:tr></h3>
            <p><a href="?sort_content=film">|<lng:tr text="Sort_by_film_name" locale="${lang}"></lng:tr>|</a></p>
            <p><a href="?sort_content=">|<lng:tr text="Sort_by_session_date" locale="${lang}"></lng:tr>|</a></p>
            <c:if test="${sort eq 'film'}">
                <p><a href="?sort_content=film&all_film=true">|<lng:tr text="All_films" locale="${lang}"></lng:tr>|</a>
                </p>
            </c:if>
        </div>
        <c:choose>
            <c:when test="${sort eq 'film'}">
                <div class="row">
                    <c:forEach var="film" items="${films}">
                        <div class="col-sm-4 wow fadeInUp">
                            <div class="member">
                                <img src="/images/${film.image}" class="img-fluid" alt="">
                                <div class="member-info">
                                    <div class="member-info-content">
                                        <h3>${film.langName(lang)}</h3>
                                        <p>${film.langDescription(lang)}</p>
                                        <a href="/session_for_film?id_film=${film.id}" class="button fit"><lng:tr
                                                text="Watch" locale="${lang}"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="center-block">
                        <c:if test="${all eq 'true'}">
                            <c:if test="${page != 0}">
                                <a href="?sort_content=film&all_film=true&page=${page-1}"
                                   class="icon ion-android-arrow-back"></a>
                            </c:if>
                            <c:if test="${page != last}">
                                <a href="?sort_content=film&all_film=true&page=${page+1}"
                                   class="icon ion-android-arrow-forward"></a>
                            </c:if>
                        </c:if>
                    </div>
                </div>
                <c:if test="${role eq 'ADMIN'}">
                    <a href="/create_film">Create film</a>
                </c:if>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach var="date" items="${dates}">
                        <div class="col wow fadeInUp">
                            <div class="member-info">
                                <div class="member-info-content">
                                    <h4>${date}</h4>
                                    <a href="/sessions_for_day?day_of_week=${date}" class="button fit"
                                       data-poptrox="youtube,800x400"><lng:tr text="Watch" locale="${lang}"/></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</section>
<footer>
</footer>

<!-- JavaScript Libraries -->
<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/jquery/jquery-migrate.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/superfish/hoverIntent.js"></script>
<script src="lib/superfish/superfish.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/counterup/counterup.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/isotope/isotope.pkgd.min.js"></script>
<script src="lib/lightbox/js/lightbox.min.js"></script>
<script src="lib/touchSwipe/jquery.touchSwipe.min.js"></script>
<!-- Contact Form JavaScript File -->
<script src="contactform/contactform.js"></script>

<!-- Template Main Javascript File -->
<script src="js/main.js"></script>

</body>
</html>
